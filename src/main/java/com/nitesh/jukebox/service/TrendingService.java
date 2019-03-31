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

    @Autowired
    MovieService movieService;

    @Autowired
    ArtistService artistService;

    @Autowired
    JukeBoxService jukeBoxService;

    @Value("${trending.NumberOfMedias}")
    private Integer maxTrendingMedia;

    /*
        Can use ExecutorService to enqueue trending update requests using a thread pool to unblock get requests.
        TODO: Later
     */

    public void incrementMediaAccessCount(TrendingMediaType trendingMediaType, String mediaId) {
        this.trendingDao.incrementCounter(trendingMediaType, mediaId);
    }

    /*
        Gets trending media based on access count in last 2 hours, using atomic counters in Scylla.
     */
    public List<String> getTrendingMedia(TrendingMediaType trendingMediaType) {
        List<TrendingMedia> medias = this.trendingDao.getTrendingMedia(trendingMediaType);
        return medias.stream().limit(maxTrendingMedia).map(m -> m.getId()).collect(Collectors.toList());
    }
}
