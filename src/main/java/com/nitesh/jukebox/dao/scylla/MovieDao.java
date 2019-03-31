package com.nitesh.jukebox.dao.scylla;


import com.datastax.driver.mapping.Mapper;
import com.nitesh.jukebox.dao.resource.ScyllaResource;
import com.nitesh.jukebox.dao.scylla.accessor.JukeBoxMovieAccessor;
import com.nitesh.jukebox.models.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieDao {

    @Autowired
    ScyllaResource scyllaResource;

    public List<Movie> get(final List<String> movieIds) throws DataAccessException {
        JukeBoxMovieAccessor accessor = scyllaResource.getAccessor(JukeBoxMovieAccessor.class);
        return accessor.getMovies(movieIds).all();
    }

    public Movie createOrUpdate(final Movie movie) throws DataAccessException {
        Mapper<Movie> movieMapper = scyllaResource.getMapper(Movie.class);
        movieMapper.save(movie);
        return movie;
    }

    public void delete(final String movieId) throws DataAccessException {
        Mapper<Movie> movieMapper = scyllaResource.getMapper(Movie.class);
        movieMapper.delete(movieId);
    }
}
