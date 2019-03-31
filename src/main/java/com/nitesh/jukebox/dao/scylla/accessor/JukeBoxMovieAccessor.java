package com.nitesh.jukebox.dao.scylla.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.nitesh.jukebox.models.entity.Movie;

import java.util.List;

@Accessor
public interface JukeBoxMovieAccessor {
    @Query("SELECT * FROM jukebox.movie WHERE id IN :ids AND rating>=0")
    Result<Movie> getMovies(List<String> ids);
}
