package com.nitesh.jukebox.dao.scylla.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.nitesh.jukebox.models.entity.TrendingMedia;

@Accessor
public interface JukeBoxTrendingMediaAccessor {

    @Query("SELECT * FROM jukebox.trending_media WHERE type = :type AND updated_at > :updatedAt")
    Result<TrendingMedia> getTrendingMedia(String type, Long updatedAt);

    @Query("UPDATE jukebox.trending_media SET access_count = access_count + 1 WHERE type = :mediaType AND updated_at = :updatedAt AND id = :mediaId")
    void updateTrendingMediaCount(String mediaType, Long updatedAt, String mediaId);
}
