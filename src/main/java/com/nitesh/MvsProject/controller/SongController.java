package com.nitesh.MvsProject.controller;


import com.nitesh.MvsProject.models.entity.Playlist;
import com.nitesh.MvsProject.models.entity.Song;
import com.nitesh.MvsProject.models.request.PlaylistCreateRequest;
import com.nitesh.MvsProject.models.request.SongCreateRequest;
import com.nitesh.MvsProject.models.response.Response;
import com.nitesh.MvsProject.service.JukeBoxService;
import com.nitesh.MvsProject.service.SongService;
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
}
