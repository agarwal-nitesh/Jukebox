package com.nitesh.jukebox.service;

import com.nitesh.jukebox.models.entity.Playlist;

import java.util.List;

public interface IPlaylistService {
    public Playlist createPlaylist(Playlist playlist);
    public List<Playlist> getPlaylist(String playlistId);
}
