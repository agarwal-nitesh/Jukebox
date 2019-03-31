package com.nitesh.jukebox.service;


import com.nitesh.jukebox.dao.scylla.SongDao;
import com.nitesh.jukebox.models.entity.Artist;
import com.nitesh.jukebox.models.entity.Song;
import com.nitesh.jukebox.models.request.SongCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SongService {
    @Autowired
    SongDao songDao;

    @Autowired
    ArtistService artistService;

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
        List<Artist> artists = this.artistService.getArtists(songCreateRequest.getArtist());
        if (artists != null && artists.size() > 0) {
            Song song = Song.builder()
                    .artist(artists.get(0).getId())
                    .id(UUID.randomUUID().toString())
                    .name(songCreateRequest.getName())
                    .rating(songCreateRequest.getRating())
                    .url(songCreateRequest.getUrl())
                    .build();
            return this.songDao.createOrUpdate(song);
        } else {
            throw new UnsatisfiedServletRequestParameterException(new String[]{"Artist not found"}, null);
        }

    }

    public List<Song> getSongsByName(final String songName) {
        return songDao.getSongsByName(songName);
    }

    public List<Song> getSongsByArtistName(final String artistName) {
        List<Artist> artists = this.artistService.getArtists(artistName);
        List<String> artistIds = artists.stream().map(artist -> artist.getId()).collect(Collectors.toList());
        return songDao.getSongsByArtistIds(artistIds);
    }

    public List<Song> getSongsByRating(final Double low, final Double high) {
        return this.songDao.getSongsByRating(low, high);
    }

    public List<Song> getSongsByIds(final List<String> songIds) {
        return this.songDao.get(songIds);
    }
}
