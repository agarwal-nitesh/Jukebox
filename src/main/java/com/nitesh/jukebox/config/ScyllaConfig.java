package com.nitesh.jukebox.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties("scylla")
public class ScyllaConfig {
    @NotNull
    private List<String> hosts;
    @NotNull
    private Integer port;
    private String user;
    private String pwd;
    private String cluster;
    @NotNull
    private String keyspace;

}