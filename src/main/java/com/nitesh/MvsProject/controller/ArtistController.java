package com.nitesh.MvsProject.controller;

import com.nitesh.MvsProject.models.entity.Artist;
import com.nitesh.MvsProject.models.entity.Playlist;
import com.nitesh.MvsProject.models.entity.Song;
import com.nitesh.MvsProject.models.request.ArtistCreateRequest;
import com.nitesh.MvsProject.models.response.Response;
import com.nitesh.MvsProject.service.ArtistService;
import com.nitesh.MvsProject.service.JukeBoxService;
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
}
