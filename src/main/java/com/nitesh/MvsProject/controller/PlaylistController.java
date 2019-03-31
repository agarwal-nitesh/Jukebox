package com.nitesh.MvsProject.controller;

import com.nitesh.MvsProject.models.entity.Playlist;
import com.nitesh.MvsProject.models.request.PlaylistCreateRequest;
import com.nitesh.MvsProject.models.response.Response;
import com.nitesh.MvsProject.service.JukeBoxService;
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
}
