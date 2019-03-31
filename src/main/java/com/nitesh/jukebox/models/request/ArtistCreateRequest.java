package com.nitesh.jukebox.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtistCreateRequest {
    @JsonProperty("name")
    String name;

    @JsonProperty("rating")
    Double rating;

    @JsonProperty("isDuplicate")
    Boolean isDuplicate;

}
