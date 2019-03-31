package com.nitesh.jukebox.service;

import com.nitesh.jukebox.dao.scylla.MovieDao;
import com.nitesh.jukebox.dao.scylla.SearchIndexDao;
import com.nitesh.jukebox.models.entity.*;
import com.nitesh.jukebox.models.request.MovieCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {
    @Autowired
    MovieDao movieDao;

    @Autowired
    SearchIndexDao searchIndexDao;

    @Autowired
    ArtistService artistService;

    public Movie createMovie(final MovieCreateRequest movieCreateRequest) throws UnsatisfiedServletRequestParameterException {
        /*
            When isDuplicate param is false or not passed, check if song name already exists.
            If song name exists return existing song
         */
        if (movieCreateRequest.getIsDuplicate() == null || !movieCreateRequest.getIsDuplicate()) {
            List<Movie> movies = this.getMoviesByName(movieCreateRequest.getName());
            if (movies != null && movies.size() > 0) {
                return movies.get(0);
            }
        }

        /*
            Check if artist in song exists. If not throw Artist not found Error
         */
        List<Artist> artists = this.artistService.getArtists(movieCreateRequest.getArtist());
        if (artists != null && artists.size() > 0) {
            Movie movie = Movie.builder()
                    .artist(artists.get(0).getId())
                    .id(UUID.randomUUID().toString())
                    .name(movieCreateRequest.getName())
                    .rating(movieCreateRequest.getRating())
                    .build();
            searchIndexDao.addIndex(SearchIndexType.MOVIE.toString(), movie.getName(), movie.getId());
            return this.movieDao.createOrUpdate(movie);
        } else {
            throw new UnsatisfiedServletRequestParameterException(new String[]{"Artist not found"}, null);
        }

    }

    public List<Movie> getMoviesByName(final String movieName) {
        SearchIndex searchIndex = this.searchIndexDao.getIndexByTypeAndName(SearchIndexType.MOVIE.toString(),
                movieName);
        if (searchIndex == null) {
            return null;
        }
        return movieDao.get(searchIndex.getIds());
    }
}
