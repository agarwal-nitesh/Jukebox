package com.nitesh.jukebox.service;

import com.nitesh.jukebox.dao.scylla.PlaylistDao;
import com.nitesh.jukebox.models.entity.*;
import com.nitesh.jukebox.models.request.PlaylistCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JukeBoxService {

    @Autowired
    PlaylistDao playlistDao;

    public Playlist createPlaylist(final PlaylistCreateRequest playlistCreateRequest) {
        /*
            When isDuplicate param is false or not passed, check if playlist name already exists.
            If playlist name exists return existing playlist
         */
        if (playlistCreateRequest.getIsDuplicate() == null || !playlistCreateRequest.getIsDuplicate()) {
            List<Playlist> playlists = this.getPlaylist(playlistCreateRequest.getName());
            if (playlists != null && playlists.size() > 0) {
                return playlists.get(0);
            }
        }

        Playlist playlist = Playlist.builder()
                .id(UUID.randomUUID().toString())
                .name(playlistCreateRequest.getName())
                .movieIds(playlistCreateRequest.getMovieList())
                .songIds(playlistCreateRequest.getSongList())
                .rating(playlistCreateRequest.getRating())
                .build();
        return playlistDao.createOrUpdate(playlist);
    }

    public List<Playlist> getPlaylist(String name) {
        return this.playlistDao.getPlaylistsByName(name);
    }

    public boolean updateSongsToPlaylist(String playlistId, String songId) {
        List<String> songIds = new ArrayList<>();
        songIds.add(songId);
        this.playlistDao.addSongToPlaylist(playlistId, songIds);
        return true;

    }

    public boolean updateMoviesToPlaylist(String playlistId, String movieId) {
        List<String> movieIds = new ArrayList<>();
        movieIds.add(movieId);
        this.playlistDao.addMoviesToPlaylist(playlistId, movieIds);
        return true;
    }

    public List<Playlist> getPlaylistByRating(final Double low, final Double high) {
        return this.playlistDao.getPlaylistByRating(low, high);
    }
}
