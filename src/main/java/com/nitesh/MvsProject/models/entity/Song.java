package com.nitesh.MvsProject.models.entity;


import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(keyspace = "jukebox", name = "song")
public class Song {
    @JsonProperty(value = "id")
    @Column(name = "id")
    @PartitionKey
    String id;

    @JsonProperty(value = "artistId")
    @Column(name = "artist_id")
    String artist;

    @JsonProperty(value = "name")
    @Column(name = "name")
    String name;

    @JsonProperty(value = "url")
    @Column(name = "url")
    String url;

    @JsonProperty(value = "rating")
    @Column(name = "rating")
    double rating;
}
