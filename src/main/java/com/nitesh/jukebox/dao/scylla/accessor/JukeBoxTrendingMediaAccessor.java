package com.nitesh.jukebox.dao.scylla.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.nitesh.jukebox.models.entity.TrendingMedia;

@Accessor
public interface JukeBoxTrendingMediaAccessor {

    @Query("SELECT type, id, WRITETIME(access_count) as last_updated, access_count FROM jukebox.trending_media WHERE type = :type")
    Result<TrendingMedia> getTrendingMedia(String type);

    @Query("UPDATE jukebox.trending_media SET access_count = access_count + 1 WHERE type = :mediaType AND id = :mediaId")
    void updateTrendingMediaCount(String mediaType, String mediaId);
}
