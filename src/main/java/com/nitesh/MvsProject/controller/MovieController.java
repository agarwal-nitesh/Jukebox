package com.nitesh.MvsProject.controller;

import com.nitesh.MvsProject.models.entity.Movie;
import com.nitesh.MvsProject.models.entity.Song;
import com.nitesh.MvsProject.models.request.MovieCreateRequest;
import com.nitesh.MvsProject.models.response.Response;
import com.nitesh.MvsProject.service.MovieService;
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
    public Response getSong(@RequestParam(value = "name") String name) throws Exception {
        try {
            List<Movie> movies = movieService.getMoviesByName(name);

            return new Response<List<Song>>().builder()
                    .data(movies)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<List<Song>>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }
}
