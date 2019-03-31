package com.nitesh.jukebox.dao.scylla.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.nitesh.jukebox.models.entity.Artist;
import com.nitesh.jukebox.models.entity.Playlist;

import java.util.List;

@Accessor
public interface JukeBoxPlaylistAccessor {
    @Query("SELECT * FROM jukebox.playlist WHERE id IN :ids")
    Result<Playlist> getPlaylistsByIds(List<String> ids);

    @Query("SELECT * FROM jukebox.playlist WHERE id = :id")
    Playlist getPlaylistById(String id);

    @Query("SELECT * FROM jukebox.playlist WHERE name = :name")
    Result<Playlist> getPlaylistsByName(@Param("name") String name);

    @Query("UPDATE jukebox.playlist SET songs = songs + :ids WHERE id = :id")
    Result updateSongToPlaylist(List<String> ids, String id);

    @Query("UPDATE jukebox.playlist SET movies = movies + :ids WHERE id = :id")
    Result updateMovieToPlaylist(List<String> ids, String id);
}
