package com.nitesh.MvsProject.service;

import com.nitesh.MvsProject.dao.scylla.ArtistDao;
import com.nitesh.MvsProject.dao.scylla.PlaylistDao;
import com.nitesh.MvsProject.dao.scylla.SearchIndexDao;
import com.nitesh.MvsProject.dao.scylla.SongDao;
import com.nitesh.MvsProject.models.entity.*;
import com.nitesh.MvsProject.models.request.ArtistCreateRequest;
import com.nitesh.MvsProject.models.request.PlaylistCreateRequest;
import com.nitesh.MvsProject.models.request.SongCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

import javax.naming.ServiceUnavailableException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JukeBoxService {

    @Autowired
    PlaylistDao playlistDao;

    @Autowired
    SongDao songDao;

    @Autowired
    SearchIndexDao searchIndexDao;

    @Autowired
    ArtistDao artistDao;

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

    public Artist createArtist(final ArtistCreateRequest artistCreateRequest) {
        /*
            When isDuplicate param is false or not passed, check if artist name already exists.
            If artist name exists return existing artist
         */
        if ( artistCreateRequest.getIsDuplicate() == null || !artistCreateRequest.getIsDuplicate()) {
            List<Artist> artists = this.getArtists(artistCreateRequest.getName());
            if (artists != null && artists.size() > 0) {
                return artists.get(0);
            }
        }

        Artist artist = Artist.builder()
                .id(UUID.randomUUID().toString())
                .name(artistCreateRequest.getName())
                .rating(artistCreateRequest.getRating())
                .build();
        searchIndexDao.addIndex(SearchIndexType.ARTIST.toString(), artist.getName(), artist.getId());
        return artistDao.createOrUpdate(artist);
    }

    public List<Artist> getArtists(final String artistName) {
        SearchIndex searchIndex = this.searchIndexDao.getIndexByTypeAndName(SearchIndexType.ARTIST.toString(),
                artistName);
        if (searchIndex == null) {
            return null;
        }
        return artistDao.get(searchIndex.getIds());
    }

    public Song createSong(final SongCreateRequest songCreateRequest) throws UnsatisfiedServletRequestParameterException {
        /*
            When isDuplicate param is false or not passed, check if song name already exists.
            If song name exists return existing song
         */
        if (songCreateRequest.getIsDuplicate() == null || !songCreateRequest.getIsDuplicate()) {
            List<Song> songs = this.getSongsByName(songCreateRequest.getName());
            if (songs != null && songs.size() > 0) {
                return songs.get(0);
            }
        }

        /*
            Check if artist in song exists. If not throw Artist not found Error
         */
        List<Artist> artists = this.getArtists(songCreateRequest.getArtist());
        if (artists != null && artists.size() > 0) {
            Song song = Song.builder()
                    .artist(artists.get(0).getId())
                    .id(UUID.randomUUID().toString())
                    .name(songCreateRequest.getName())
                    .rating(songCreateRequest.getRating())
                    .build();
            searchIndexDao.addIndex(SearchIndexType.SONG.toString(), song.getName(), song.getId());
            return this.songDao.createOrUpdate(song);
        } else {
            throw new UnsatisfiedServletRequestParameterException(new String[]{"Artist not found"}, null);
        }

    }

    public List<Song> getSongsByName(final String songName) {
        SearchIndex searchIndex = this.searchIndexDao.getIndexByTypeAndName(SearchIndexType.SONG.toString(),
                songName);
        if (searchIndex == null) {
            return null;
        }
        return songDao.get(searchIndex.getIds());

    }

    public List<Song> getSongsByIds(final List<String> songIds) {
        return this.songDao.get(songIds);
    }
}
