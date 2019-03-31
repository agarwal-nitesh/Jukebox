package com.nitesh.jukebox.dao.scylla.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.nitesh.jukebox.models.entity.Artist;
import com.nitesh.jukebox.models.entity.Movie;

import java.util.List;

@Accessor
public interface JukeBoxMovieAccessor {
    @Query("SELECT * FROM jukebox.movie WHERE id IN :ids")
    Result<Movie> getMovies(List<String> ids);

    @Query("SELECT * FROM jukebox.movie WHERE name = :name")
    Result<Movie> getMoviesByName(@Param("name") String name);


    @Query("SELECT * FROM jukebox.mv_movie_by_artist_id WHERE artist_id IN :ids")
    Result<Movie> getMoviesByArtistIds(@Param("ids") List<String> ids);

    @Query("SELECT * FROM jukebox.movie WHERE rating <= :high AND rating >= :low ALLOW FILTERING")
    Result<Movie> getMoviesByRating(@Param("low") Integer low, @Param("high") Integer high);
}
