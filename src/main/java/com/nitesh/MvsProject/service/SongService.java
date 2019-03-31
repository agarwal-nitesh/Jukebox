package com.nitesh.MvsProject.service;


import com.nitesh.MvsProject.dao.scylla.SearchIndexDao;
import com.nitesh.MvsProject.dao.scylla.SongDao;
import com.nitesh.MvsProject.models.entity.Artist;
import com.nitesh.MvsProject.models.entity.SearchIndex;
import com.nitesh.MvsProject.models.entity.SearchIndexType;
import com.nitesh.MvsProject.models.entity.Song;
import com.nitesh.MvsProject.models.request.SongCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

import java.util.List;
import java.util.UUID;

@Service
public class SongService {
    @Autowired
    SongDao songDao;

    @Autowired
    SearchIndexDao searchIndexDao;

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
