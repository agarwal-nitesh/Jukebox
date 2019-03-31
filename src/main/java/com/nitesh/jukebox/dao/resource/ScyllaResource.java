package com.nitesh.jukebox.dao.resource;

import com.nitesh.jukebox.config.ScyllaConfig;
import com.nitesh.jukebox.dao.scylla.accessor.*;
import com.nitesh.jukebox.models.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ScyllaResource {
    /**
     * Add classes to classList to add them to mappers.
     */
    private static final List<Class> mapperClassList = Arrays.asList(
            Artist.class,
            Movie.class,
            Playlist.class,
            Song.class,
            SongByArtistId.class
    );

    private static final List<Class> accessorClassList = Arrays.asList(
            JukeBoxSearchIndexAccessor.class,
            JukeBoxArtistAccessor.class,
            JukeBoxSongAccessor.class,
            JukeBoxMovieAccessor.class
    );

    private Cluster cluster;
    private Session session;
    private MappingManager mappingManager;
    private Map<Class, Mapper> mappers = new HashMap<>();
    private Map<Class, Object> accessors = new HashMap<>();

    @Autowired
    private ScyllaConfig scyllaConfig;

    public ScyllaResource(ScyllaConfig config) {
        final List<InetSocketAddress> socketAddresses = config.getHosts().stream()
                .map(e -> new InetSocketAddress(e, config.getPort()))
                .collect(Collectors.toList());
        Cluster.Builder builder = Cluster.builder()
                .withClusterName(config.getCluster())
                .addContactPointsWithPorts(socketAddresses);
        if(StringUtils.hasText(config.getPwd()) && StringUtils.hasText(config.getUser()))
            builder.withCredentials(config.getUser(), config.getPwd());
        this.cluster = builder.build();
        this.session = cluster.connect(config.getKeyspace());
        this.mappingManager = new MappingManager(session);
        this.addMappers(mapperClassList.stream().toArray(Class[]::new));
        this.addAccessors(accessorClassList.stream().toArray(Class[]::new));
    }

    private void addMappers(Class ...classList) {
        for(Class c : classList){
            this.mappers.put(c, this.mappingManager.mapper(c));
        }
    }

    public <R> Mapper<R> getMapper(Class<R> c) throws DataAccessException {
        Mapper<R> mapper = mappers.get(c);
        if (mapper == null) {
            throw new DataSourceLookupFailureException(c.getName() + " Mapper not present");
        }
        return mapper;
    }

    private void addAccessors(Class ...classList) {
        for(Class c : classList){
            this.accessors.put(c, this.mappingManager.createAccessor(c));
        }
    }

    public <R> R getAccessor(Class<R> c) throws DataAccessException {
        R accessor = (R) accessors.get(c);
        if (accessor == null) {
            throw new DataSourceLookupFailureException(c.getName() + " Accessor not present");
        }
        return accessor;
    }
}
