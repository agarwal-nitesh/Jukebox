package com.nitesh.MvsProject.dao.scylla;


import com.datastax.driver.mapping.Mapper;
import com.nitesh.MvsProject.dao.resource.ScyllaResource;
import com.nitesh.MvsProject.models.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDao {

    @Autowired
    ScyllaResource scyllaResource;

    public Movie get(final String movieId) throws DataAccessException {
        Mapper<Movie> movieMapper = scyllaResource.getMapper(Movie.class);
        return movieMapper.get(movieId);
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
