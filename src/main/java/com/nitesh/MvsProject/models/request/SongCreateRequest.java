package com.nitesh.MvsProject.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongCreateRequest {
    @JsonProperty(value = "artistName")
    String artist;

    @JsonProperty(value = "songName")
    String name;

    @JsonProperty(value = "url")
    String url;

    @JsonProperty(value = "rating")
    double rating;

    @JsonProperty(value = "isDuplicate")
    Boolean isDuplicate;
}
