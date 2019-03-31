package com.nitesh.MvsProject.dao.scylla;


import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.annotations.Accessor;
import com.nitesh.MvsProject.dao.resource.ScyllaResource;
import com.nitesh.MvsProject.dao.scylla.accessor.JukeBoxPlaylistAccessor;
import com.nitesh.MvsProject.dao.scylla.accessor.JukeBoxSearchIndexAccessor;
import com.nitesh.MvsProject.models.entity.Playlist;
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

    public void delete(final String playlistId) throws DataAccessException {
        Mapper<Playlist> playlistMapper = scyllaResource.getMapper(Playlist.class);
        playlistMapper.delete(playlistId);
    }
}
