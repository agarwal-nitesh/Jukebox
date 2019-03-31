package com.nitesh.jukebox.dao.scylla.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.nitesh.jukebox.models.entity.Playlist;

import java.util.List;

@Accessor
public interface JukeBoxPlaylistAccessor {
    @Query("SELECT * FROM jukebox.playlist WHERE id IN :ids")
    Result<Playlist> getPlaylistsByIds(List<String> ids);

    @Query("SELECT * FROM jukebox.playlist WHERE id = :id")
    Result<Playlist> getPlaylistById(String id);

    @Query("SELECT * FROM jukebox.playlist WHERE name = :name")
    Result<Playlist> getPlaylistsByName(@Param("name") String name);

    @Query("UPDATE jukebox.playlist SET songs = songs + :songIds WHERE id = :playlistId")
    void updateSongToPlaylist(@Param("songIds") List<String> songIds, @Param("playlistId") String playlistId);

    @Query("UPDATE jukebox.playlist SET movies = movies + :ids WHERE id = :id")
    void updateMovieToPlaylist(List<String> ids, String id);

    @Query("SELECT * FROM jukebox.playlist WHERE rating <= :high AND rating >= :low ALLOW FILTERING")
    Result<Playlist> getPlaylistByRating(@Param("low") Integer low, @Param("high") Integer high);
}
