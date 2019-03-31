package com.nitesh.jukebox.controller;


import com.nitesh.jukebox.models.entity.Playlist;
import com.nitesh.jukebox.models.entity.Song;
import com.nitesh.jukebox.models.request.SongCreateRequest;
import com.nitesh.jukebox.models.response.Response;
import com.nitesh.jukebox.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongController {

    @Autowired
    SongService songService;


    @RequestMapping(value = "/song", method = RequestMethod.POST)
    public Response createSong(@RequestBody SongCreateRequest song) throws Exception {
        try {
            Song response = songService.createSong(song);
            return new Response<Song>().builder()
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

    @RequestMapping(value = "/song", method = RequestMethod.GET)
    public Response getSong(@RequestParam(value = "name") String name) throws Exception {
        try {
            List<Song> songs = songService.getSongsByName(name);

            return new Response<List<Song>>().builder()
                    .data(songs)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<List<Song>>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @RequestMapping(value = "/song/artists", method = RequestMethod.GET)
    public Response getSongByArtistName(@RequestParam(value = "artistName") String artistName) throws Exception {
        try {
            List<Song> songs = songService.getSongsByArtistName(artistName);

            return new Response<List<Song>>().builder()
                    .data(songs)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<List<Song>>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @RequestMapping(value = "/song/rating", method = RequestMethod.GET)
    public Response getSongsByRating(@RequestParam(value = "low") Integer low, @RequestParam(value = "low") Integer high) throws Exception {
        try {
            if(high == null) {
                high = 5;
            }
            if(low == null) {
                low = 0;
            }
            List<Song> songs = this.songService.getSongsByRating(low, high);

            return new Response<List<Song>>().builder()
                    .data(songs)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<Song>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }
}
