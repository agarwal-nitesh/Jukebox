package com.nitesh.jukebox.controller;

import com.nitesh.jukebox.models.entity.Playlist;
import com.nitesh.jukebox.models.request.PlaylistCreateRequest;
import com.nitesh.jukebox.models.response.Response;
import com.nitesh.jukebox.service.JukeBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PlaylistController {

    @Autowired
    JukeBoxService jukeBoxService;

    @RequestMapping(value = "/playlist", method = RequestMethod.POST)
    public Response createPlaylist(@RequestBody PlaylistCreateRequest playlistCreateRequest) throws Exception {
        try {
            Playlist response = jukeBoxService.createPlaylist(playlistCreateRequest);
            return new Response<Playlist>().builder()
                    .data(response)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<Playlist>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @RequestMapping(value = "/playlist", method = RequestMethod.GET)
    public Response getPlaylists(@RequestParam(value = "name") String name) throws Exception {
        try {
            List<Playlist> playlists = jukeBoxService.getPlaylist(name);

            return new Response<List<Playlist>>().builder()
                    .data(playlists)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<List<Playlist>>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @RequestMapping(value = "/playlist/song/{playlistId}/{songId}", method = RequestMethod.GET)
    public Response addSongToPlaylist(@PathVariable(value = "playlistId") String playlistId,
                                      @PathVariable(value = "songId") String songId) throws Exception {
        try {
            boolean response = jukeBoxService.updateSongsToPlaylist(playlistId, songId);

            return new Response<Boolean>().builder()
                    .data(response)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<Boolean>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @RequestMapping(value = "/playlist/movie/{playlistId}/{movieId}", method = RequestMethod.GET)
    public Response addMovieToPlaylist(@PathVariable(value = "playlistId") String playlistId,
                                      @PathVariable(value = "movieId") String movieId) throws Exception {
        try {
            boolean response = jukeBoxService.updateMoviesToPlaylist(playlistId, movieId);

            return new Response<Boolean>().builder()
                    .data(response)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<Boolean>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }

    @RequestMapping(value = "/playlist/rating", method = RequestMethod.GET)
    public Response getPlaylistByRating(@RequestParam(value = "low") Double low, @RequestParam(value = "high") Double high) throws Exception {
        try {
            if(high == null) {
                high = 5D;
            }
            if(low == null) {
                low = 0D;
            }
            List<Playlist> playlists = this.jukeBoxService.getPlaylistByRating(low, high);

            return new Response<List<Playlist>>().builder()
                    .data(playlists)
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return new Response<Playlist>().builder()
                    .data(null)
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
    }
}
