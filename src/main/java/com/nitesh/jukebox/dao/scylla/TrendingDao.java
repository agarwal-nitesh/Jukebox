package com.nitesh.jukebox.dao.scylla;

import com.nitesh.jukebox.dao.resource.ScyllaResource;
import com.nitesh.jukebox.dao.scylla.accessor.JukeBoxTrendingMediaAccessor;
import com.nitesh.jukebox.models.entity.TrendingMedia;
import com.nitesh.jukebox.models.entity.TrendingMediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrendingDao {
    @Autowired
    ScyllaResource scyllaResource;

    public void incrementCounter(TrendingMediaType type, String id) {
        JukeBoxTrendingMediaAccessor accessor = this.scyllaResource.getAccessor(JukeBoxTrendingMediaAccessor.class);
        accessor.updateTrendingMediaCount(type.toString(), System.currentTimeMillis(), id);
    }

    public List<TrendingMedia> getTrendingMedia(TrendingMediaType type) {
        JukeBoxTrendingMediaAccessor accessor = this.scyllaResource.getAccessor(JukeBoxTrendingMediaAccessor.class);
        return accessor.getTrendingMedia(type.toString(), System.currentTimeMillis()-2*60*60*1000).all();
    }
}
