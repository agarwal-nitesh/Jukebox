package com.nitesh.jukebox.dao.scylla;


import com.datastax.driver.mapping.Mapper;
import com.nitesh.jukebox.dao.resource.ScyllaResource;
import com.nitesh.jukebox.dao.scylla.accessor.JukeBoxSongAccessor;
import com.nitesh.jukebox.models.entity.Song;
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
        return accessor.getSongsByIds(songIds).all();
    }

    public List<Song> getSongsByName(final String name) throws DataAccessException {
        JukeBoxSongAccessor accessor = scyllaResource.getAccessor(JukeBoxSongAccessor.class);
        return accessor.getSongsByName(name).all();
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

    public List<Song> getSongsByArtistIds(final List<String> ids) throws DataAccessException {
        JukeBoxSongAccessor accessor = scyllaResource.getAccessor(JukeBoxSongAccessor.class);
        return accessor.getSongsByArtistIds(ids).all();
    }

    public List<Song> getSongsByRating(final Double low, final Double high) {
        JukeBoxSongAccessor accessor = scyllaResource.getAccessor(JukeBoxSongAccessor.class);
        return accessor.getSongsByRating(low, high).all();
    }
}
