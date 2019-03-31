package com.nitesh.MvsProject.dao.scylla;


import com.datastax.driver.mapping.Mapper;
import com.nitesh.MvsProject.dao.resource.ScyllaResource;
import com.nitesh.MvsProject.models.entity.SongByArtistId;
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
