package com.nitesh.MvsProject.dao.scylla.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import com.nitesh.MvsProject.models.entity.Artist;

import java.util.List;


@Accessor
public interface JukeBoxArtistAccessor {
    @Query("SELECT * FROM jukebox.artist WHERE id IN :ids AND rating>=0")
    Result<Artist> getArtists(@Param("ids") List<String> ids);
}
