package com.nitesh.jukebox.dao.scylla;


import com.datastax.driver.mapping.Mapper;
import com.nitesh.jukebox.dao.resource.ScyllaResource;
import com.nitesh.jukebox.models.entity.SongByArtistId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class SongByArtistIdDao {

    @Autowired
    ScyllaResource scyllaResource;

    public SongByArtistId get(final String artistId) throws DataAccessException {
        Mapper<SongByArtistId> songByArtistIdMapper = scyllaResource.getMapper(SongByArtistId.class);
        return songByArtistIdMapper.get(artistId);
    }
}
