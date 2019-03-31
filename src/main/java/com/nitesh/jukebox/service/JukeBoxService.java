package com.nitesh.jukebox.service;

import com.nitesh.jukebox.dao.scylla.PlaylistDao;
import com.nitesh.jukebox.dao.scylla.SearchIndexDao;
import com.nitesh.jukebox.models.entity.*;
import com.nitesh.jukebox.models.request.PlaylistCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JukeBoxService {

    @Autowired
    PlaylistDao playlistDao;

    @Autowired
    SearchIndexDao searchIndexDao;

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
                .movieIds(playlistCreateRequest.getMovieList())
                .songIds(playlistCreateRequest.getSongList())
                .build();
        searchIndexDao.addIndex(SearchIndexType.PLAYLIST.toString(), playlist.getName(), playlist.getId());
        return playlistDao.createOrUpdate(playlist);
    }

    public List<Playlist> getPlaylist(String name) {
        SearchIndex searchIndex = this.searchIndexDao.getIndexByTypeAndName(SearchIndexType.PLAYLIST.toString(),
                name);
        if (searchIndex == null) {
            return null;
        }
        return this.playlistDao.get(searchIndex.getIds());
    }
}
