package com.nitesh.MvsProject.service;

import com.nitesh.MvsProject.models.entity.Playlist;

import java.util.List;

public interface IPlaylistService {
    public Playlist createPlaylist(Playlist playlist);
    public List<Playlist> getPlaylist(String playlistId);
}
