package com.nitesh.MvsProject.dao.scylla;


import com.datastax.driver.mapping.Mapper;
import com.nitesh.MvsProject.dao.resource.ScyllaResource;
import com.nitesh.MvsProject.dao.scylla.accessor.JukeBoxSongAccessor;
import com.nitesh.MvsProject.models.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongDao {

    @Autowired
    ScyllaResource scyllaResource;

    public List<Song> get(final List<String> songIds) throws DataAccessException {
        JukeBoxSongAccessor accessor = scyllaResource.getAccessor(JukeBoxSongAccessor.class);
        return accessor.getSongs(songIds).all();
    }

    public Song createOrUpdate(final Song song) throws DataAccessException {
        Mapper<Song> movieMapper = scyllaResource.getMapper(Song.class);
        movieMapper.save(song);
        return song;
    }

    public void delete(final String songId) throws DataAccessException {
        Mapper<Song> artistMapper = scyllaResource.getMapper(Song.class);
        artistMapper.delete(songId);
    }
}
