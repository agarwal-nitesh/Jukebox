package com.nitesh.jukebox.service;

import com.nitesh.jukebox.dao.scylla.TrendingDao;
import com.nitesh.jukebox.models.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrendingService {

    @Autowired
    TrendingDao trendingDao;



    @Value("${trending.NumberOfMedias}")
    private Long maxTrendingMedia;

    /*
        Can use ExecutorService to enqueue trending update requests using a thread pool to unblock get requests.
        TODO: Later
     */

    public void incrementMediaAccessCount(TrendingMediaType trendingMediaType, String mediaId) {
        this.trendingDao.incrementCounter(trendingMediaType, mediaId);
    }

    /*
        Gets trending media based on access count in last 1 hour, using atomic counters in Scylla.
        Use WRITETIME property of column to check when it was last updated.
     */
    public List<String> getTrendingMedia(TrendingMediaType trendingMediaType) {
        List<TrendingMedia> medias = this.trendingDao.getTrendingMedia(trendingMediaType);
        Long tsBefore1Hour = System.currentTimeMillis()/1000- 3600;
        medias = medias.stream().filter(m -> m.getLastUpdate()/1000000 > tsBefore1Hour).collect(Collectors.toList());
        medias.sort((m1, m2) -> m2.getAccessCount().compareTo(m1.getAccessCount()));
        return medias.stream().limit(maxTrendingMedia).map(m -> m.getId()).collect(Collectors.toList());
    }
}
