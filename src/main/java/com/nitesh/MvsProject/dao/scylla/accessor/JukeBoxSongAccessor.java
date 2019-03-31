package com.nitesh.MvsProject.dao.scylla.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.nitesh.MvsProject.models.entity.Song;

import java.util.List;

@Accessor
public interface JukeBoxSongAccessor {
    @Query("SELECT * FROM jukebox.song WHERE id IN :ids AND rating>=0")
    Result<Song> getSongs(List<String> ids);
}
