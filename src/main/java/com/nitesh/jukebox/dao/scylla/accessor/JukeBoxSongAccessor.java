package com.nitesh.jukebox.dao.scylla.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.nitesh.jukebox.models.entity.Song;

import java.util.List;

@Accessor
public interface JukeBoxSongAccessor {
    @Query("SELECT * FROM jukebox.song WHERE id IN :ids")
    Result<Song> getSongsByIds(List<String> ids);

    @Query("SELECT * FROM jukebox.song WHERE name = :name")
    Result<Song> getSongsByName(@Param("name") String name);

    @Query("SELECT * FROM jukebox.mv_song_by_artist_id WHERE artist_id IN :ids")
    Result<Song> getSongsByArtistIds(@Param("ids") List<String> ids);

    @Query("SELECT * FROM jukebox.song WHERE rating <= :high AND rating >= :low ALLOW FILTERING")
    Result<Song> getSongsByRating(@Param("low") Double low, @Param("high") Double high);
}
