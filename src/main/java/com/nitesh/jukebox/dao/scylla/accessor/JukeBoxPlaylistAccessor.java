package com.nitesh.jukebox.dao.scylla.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.nitesh.jukebox.models.entity.Playlist;

import java.util.List;

@Accessor
public interface JukeBoxPlaylistAccessor {
    @Query("SELECT * FROM jukebox.playlist WHERE id IN :ids AND rating >=0")
    Result<Playlist> getPlaylists(List<String> ids);

    @Query("SELECT * FROM jukebox.playlist WHERE id = :id AND rating >=0")
    Playlist getPlaylist(String id);

    @Query("UPDATE jukebox.playlist SET songs = songs + :ids WHERE id = :id AND rating>=0")
    Result updateSongToPlaylist(List<String> ids, String id);

    @Query("UPDATE jukebox.playlist SET movies = movies + :ids WHERE id = :id AND rating>=0")
    Result updateMovieToPlaylist(List<String> ids, String id);
}
