package com.nitesh.jukebox.controller;

import com.nitesh.jukebox.models.entity.Artist;
import com.nitesh.jukebox.models.request.ArtistCreateRequest;
import com.nitesh.jukebox.models.response.Response;
import com.nitesh.jukebox.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArtistController {
    @Autowired
    ArtistService artistService;


    @RequestMapping(value = "/artist", method = RequestMethod.POST)
    public Response createArtist(@RequestBody ArtistCreateRequest artist) throws Exception {
        try {
            Artist response = artistService.createArtist(artist);
            return new Response<Artist>().builder()
                    .data(response)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<Artist>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @RequestMapping(value = "/artists", method = RequestMethod.GET)
    public Response getAllArtists() throws Exception {
        try {
            List<Artist> artists = artistService.getAllArtists();

            return new Response<List<Artist>>().builder()
                    .data(artists)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<Artist>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @RequestMapping(value = "/artist", method = RequestMethod.GET)
    public Response getArtists(@RequestParam(value = "name") String name) throws Exception {
        try {
            List<Artist> artists = artistService.getArtists(name);

            return new Response<List<Artist>>().builder()
                    .data(artists)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<Artist>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @RequestMapping(value = "/artist/rating", method = RequestMethod.GET)
    public Response getArtistsByRating(@RequestParam(value = "low") Double low, @RequestParam(value = "high") Double high) throws Exception {
        try {
            if(high == null) {
                high = 5D;
            }
            if(low == null) {
                low = 0D;
            }
            List<Artist> artists = artistService.getArtistsByRating(low, high);

            return new Response<List<Artist>>().builder()
                    .data(artists)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<Artist>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @RequestMapping(value = "/artist/trending", method = RequestMethod.GET)
    public Response getTrendingArtists() throws Exception {
        try {
            List<Artist> artists = artistService.getTrendingArtists();

            return new Response<List<Artist>>().builder()
                    .data(artists)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<Artist>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }
}
