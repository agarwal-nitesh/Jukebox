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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(keyspace = "jukebox", name = "artist")
public class Artist {
    @JsonProperty("id")
    @Column(name = "id")
    @PartitionKey
    String id;

    @JsonProperty("name")
    @Column(name = "name")
    String name;

    @JsonProperty("rating")
    @Column(name = "rating")
    Double rating;
}
