package com.nitesh.jukebox.dao.scylla.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.nitesh.jukebox.models.entity.Artist;

import java.util.List;


@Accessor
public interface JukeBoxArtistAccessor {
    @Query("SELECT * FROM jukebox.artist WHERE id IN :ids")
    Result<Artist> getArtistsById(@Param("ids") List<String> ids);


    @Query("SELECT * FROM jukebox.artist WHERE name = :name")
    Result<Artist> getArtistByName(@Param("name") String name);


    @Query("SELECT * FROM jukebox.artist WHERE rating IN :ratings")
    Result<Artist> getArtistsByRating(@Param("ratings") List<Double> ratings);
}
