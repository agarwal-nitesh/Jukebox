package com.nitesh.jukebox.dao.scylla;


import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.Result;
import com.nitesh.jukebox.dao.resource.ScyllaResource;
import com.nitesh.jukebox.dao.scylla.accessor.JukeBoxPlaylistAccessor;
import com.nitesh.jukebox.models.entity.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaylistDao {

    @Autowired
    ScyllaResource scyllaResource;

    public Playlist get(final String playlistId) throws DataAccessException {
        JukeBoxPlaylistAccessor playlistAccessor = scyllaResource.getAccessor(JukeBoxPlaylistAccessor.class);
        return playlistAccessor.getPlaylist(playlistId);
    }

    public List<Playlist> get(final List<String> ids) throws DataAccessException {
        JukeBoxPlaylistAccessor playlistAccessor = scyllaResource.getAccessor(JukeBoxPlaylistAccessor.class);
        return playlistAccessor.getPlaylists(ids).all();
    }

    public Playlist createOrUpdate(final Playlist playlist) throws DataAccessException {
        Mapper<Playlist> playlistMapper = scyllaResource.getMapper(Playlist.class);
        playlistMapper.save(playlist);
        return playlist;
    }

    public void addSongToPlaylist(final String playlistId, final List<String> songs) throws DataAccessException {
        JukeBoxPlaylistAccessor accessor = scyllaResource.getAccessor(JukeBoxPlaylistAccessor.class);
        Result result =  accessor.updateSongToPlaylist(songs, playlistId);
        System.out.println(result.toString());
    }

    public void addMoviesToPlaylist(final String playlistId, final List<String> movies) throws DataAccessException {
        JukeBoxPlaylistAccessor accessor = scyllaResource.getAccessor(JukeBoxPlaylistAccessor.class);
        Result result =  accessor.updateMovieToPlaylist(movies, playlistId);
        System.out.println(result.toString());
    }

    public void delete(final String playlistId) throws DataAccessException {
        Mapper<Playlist> playlistMapper = scyllaResource.getMapper(Playlist.class);
        playlistMapper.delete(playlistId);
    }
}
