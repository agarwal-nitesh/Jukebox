package com.nitesh.jukebox.models.entity;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(keyspace = "jukebox", name = "playlist")
public class Playlist {
    @JsonProperty(value = "id")
    @Column(name = "id")
    @PartitionKey
    String id;

    @JsonProperty(value = "name")
    @Column(name = "name")
    String name;

    @JsonProperty(value = "songs")
    @Column(name = "songs")
    List<String> songIds;

    @JsonProperty(value = "movies")
    @Column(name = "movies")
    List<String> movieIds;

    @JsonProperty(value = "rating")
    @Column(name = "rating")
    Double rating;
}
