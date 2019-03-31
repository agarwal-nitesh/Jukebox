package com.nitesh.jukebox.models.entity;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(keyspace = "jukebox", name = "search_index")
public class SearchIndex {

    @PartitionKey
    @Column(name = "search_type")
    String searchType;

    @Column(name = "value")
    String value;

    @Column(name = "ids")
    List<String> ids;
}
