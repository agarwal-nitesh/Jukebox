package com.nitesh.MvsProject.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistCreateRequest {
    @JsonProperty(value = "name")
    String name;
    @JsonProperty(value = "songList")
    List<String> songList;
    @JsonProperty(value = "movieList")
    List<String> movieList;
    @JsonProperty(value = "isDuplicate")
    Boolean isDuplicate;
}
