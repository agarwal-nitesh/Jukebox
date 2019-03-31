### Jukebox

#### Test, Build & Run 
```
mvn package
java -jar target/jukebox-1.0-SNAPSHOT.jar
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

#### Swagger
```
http://localhost:8080/swagger-ui.html
```

#### Script to populate data in scylla
```
python3 scripts/functional_test.py
```