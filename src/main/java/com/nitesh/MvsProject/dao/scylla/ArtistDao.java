package com.nitesh.MvsProject.dao.scylla;


import com.datastax.driver.mapping.Mapper;
import com.nitesh.MvsProject.dao.resource.ScyllaResource;
import com.nitesh.MvsProject.dao.scylla.accessor.JukeBoxArtistAccessor;
import com.nitesh.MvsProject.models.entity.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArtistDao {

    @Autowired
    ScyllaResource scyllaResource;

    public List<Artist> get(final List<String> artistIds) throws DataAccessException {
        JukeBoxArtistAccessor accessor = scyllaResource.getAccessor(JukeBoxArtistAccessor.class);
        return accessor.getArtists(artistIds).all();
    }

    public Artist createOrUpdate(final Artist artist) throws DataAccessException {
        Mapper<Artist> artistMapper = scyllaResource.getMapper(Artist.class);
        artistMapper.save(artist);
        return artist;
    }

    public void delete(final String artistId) throws DataAccessException {
        Mapper<Artist> artistMapper = scyllaResource.getMapper(Artist.class);
        artistMapper.delete(artistId);
    }
}
