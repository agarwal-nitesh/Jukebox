package com.nitesh.jukebox.dao.scylla;


import com.datastax.driver.mapping.Mapper;
import com.nitesh.jukebox.dao.resource.ScyllaResource;
import com.nitesh.jukebox.dao.scylla.accessor.JukeBoxPlaylistAccessor;
import com.nitesh.jukebox.models.entity.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlaylistDao {

    @Autowired
    ScyllaResource scyllaResource;

    public List<Playlist> get(final String playlistId) throws DataAccessException {
        JukeBoxPlaylistAccessor playlistAccessor = scyllaResource.getAccessor(JukeBoxPlaylistAccessor.class);
        List<Playlist> playlists = playlistAccessor.getPlaylistById(playlistId).all();
        if (playlists != null) {
            return playlists;
        }
        return new ArrayList<Playlist>();
    }

    public List<Playlist> get(final List<String> ids) throws DataAccessException {
        JukeBoxPlaylistAccessor playlistAccessor = scyllaResource.getAccessor(JukeBoxPlaylistAccessor.class);
        return playlistAccessor.getPlaylistsByIds(ids).all();
    }

    public List<Playlist> getPlaylistsByName(final String name) throws DataAccessException {
        JukeBoxPlaylistAccessor accessor = scyllaResource.getAccessor(JukeBoxPlaylistAccessor.class);
        return accessor.getPlaylistsByName(name).all();
    }

    public Playlist createOrUpdate(final Playlist playlist) throws DataAccessException {
        Mapper<Playlist> playlistMapper = scyllaResource.getMapper(Playlist.class);
        playlistMapper.save(playlist);
        return playlist;
    }

    public void addSongToPlaylist(final String playlistId, final List<String> songs) throws DataAccessException {
        JukeBoxPlaylistAccessor accessor = scyllaResource.getAccessor(JukeBoxPlaylistAccessor.class);
        accessor.updateSongToPlaylist(songs, playlistId);
    }

    public void addMoviesToPlaylist(final String playlistId, final List<String> movies) throws DataAccessException {
        JukeBoxPlaylistAccessor accessor = scyllaResource.getAccessor(JukeBoxPlaylistAccessor.class);
        accessor.updateMovieToPlaylist(movies, playlistId);
    }

    public void delete(final String playlistId) throws DataAccessException {
        Mapper<Playlist> playlistMapper = scyllaResource.getMapper(Playlist.class);
        playlistMapper.delete(playlistId);
    }

    public List<Playlist> getPlaylistByRating(final Double low, final Double high) {
        JukeBoxPlaylistAccessor accessor = scyllaResource.getAccessor(JukeBoxPlaylistAccessor.class);
        return accessor.getPlaylistByRating(low, high).all();
    }
}
