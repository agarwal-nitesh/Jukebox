package com.nitesh.jukebox.service;


import com.nitesh.jukebox.dao.scylla.ArtistDao;
import com.nitesh.jukebox.models.entity.Artist;
import com.nitesh.jukebox.models.entity.SearchIndexType;
import com.nitesh.jukebox.models.entity.Song;
import com.nitesh.jukebox.models.request.ArtistCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArtistService {

    @Autowired
    ArtistDao artistDao;


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
        return artistDao.createOrUpdate(artist);
    }

    public List<Artist> getArtists(final String artistName) {
        return artistDao.getArtistsByName(artistName);
    }

    public List<Artist> getAllArtists() {
        return artistDao.getAllArtists();
    }

    public List<Artist> getArtistsByRating(final int low, final int high) {
        return this.artistDao.getArtistsByRating(low, high);
    }
}
