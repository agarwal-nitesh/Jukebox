package com.nitesh.jukebox.controller;

import com.nitesh.jukebox.models.entity.Movie;
import com.nitesh.jukebox.models.request.MovieCreateRequest;
import com.nitesh.jukebox.models.response.Response;
import com.nitesh.jukebox.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;


    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public Response createSong(@RequestBody MovieCreateRequest movie) throws Exception {
        try {
            Movie response = movieService.createMovie(movie);
            return new Response<Movie>().builder()
                    .data(response)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<Boolean>().builder()
                    .data(true)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public Response getMovie(@RequestParam(value = "name") String name) throws Exception {
        try {
            List<Movie> movies = movieService.getMoviesByName(name);

            return new Response<List<Movie>>().builder()
                    .data(movies)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<List<Movie>>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @RequestMapping(value = "/movie/artist", method = RequestMethod.GET)
    public Response getMovieByArtist(@RequestParam(value = "artistName") String artistName) throws Exception {
        try {
            List<Movie> movies = movieService.getMoviesByName(artistName);
            return new Response<List<Movie>>().builder()
                    .data(movies)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<List<Movie>>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @RequestMapping(value = "/movie/rating", method = RequestMethod.GET)
    public Response getMoviesByRating(@RequestParam(value = "low") Double low, @RequestParam(value = "high") Double high) throws Exception {
        try {
            if(high == null) {
                high = 5D;
            }
            if(low == null) {
                low = 0D;
            }
            List<Movie> movies = movieService.getMoviesByRating(low, high);

            return new Response<List<Movie>>().builder()
                    .data(movies)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<Movie>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }
}
