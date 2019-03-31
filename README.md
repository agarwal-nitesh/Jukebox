### com.nitesh.jukebox

#### Test, Build & Run 
```
mvn package
java -jar target/com.nitesh.jukebox-1.0-SNAPSHOT.jar
```

##### Setup Scylla
```
docker pull scylladb/scylla
docker run --name scylla1 -p 9042:9042 -d scylladb/scylla --broadcast-address 127.0.0.1 --listen-address 0.0.0.0 --broadcast-rpc-address 127.0.0.1
docker exec -it scylla1 cqlsh
```

#### Db Deltas
```
docker cp  src/main/resources/schema.cql scylla1:/tmp/
docker exec -it scylla1 cqlsh -f /tmp/schema.cql
```

