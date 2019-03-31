package com.nitesh.jukebox.controller;


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
    public Response getSongsByRating(@RequestParam(value = "low") Double low, @RequestParam(value = "high") Double high) throws Exception {
        try {
            if(high == null) {
                high = 5D;
            }
            if(low == null) {
                low = 0D;
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


    @RequestMapping(value = "/songs", method = RequestMethod.POST)
    public Response getSongsByIds(@RequestBody List<String> songIds) throws Exception {
        try {

            List<Song> songs = this.songService.getSongsByIds(songIds);

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

    @RequestMapping(value = "/songs/trending", method = RequestMethod.POST)
    public Response getTrendingSongs() throws Exception {
        try {

            List<Song> songs = this.songService.getTrendingSongs();

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

    @RequestMapping(value = "/songs/select/{songId}", method = RequestMethod.POST)
    public Response getSongsByIds(@PathVariable(value = "songId") String songId) throws Exception {
        try {

            this.songService.selectSongToPlay(songId);

            return new Response<List<Song>>().builder()
                    .data(null)
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
