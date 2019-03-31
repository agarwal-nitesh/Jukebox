package com.nitesh.jukebox.models.entity;


import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Computed;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(keyspace = "jukebox", name = "trending_media")
public class TrendingMedia {
    @Column(name = "type")
    @PartitionKey
    String type;
    @Computed(value = "last_updated")
    Long lastUpdate;
    @Column(name = "id")
    String id;
    @Column(name = "access_count")
    Long accessCount;
}
