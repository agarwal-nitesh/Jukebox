CREATE KEYSPACE jukebox WITH replication = {'class':'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE jukebox.movie(
    id text,
    artist_id text,
    name text,
    url text,
    rating double,
    PRIMARY KEY (id, rating)
);

CREATE TABLE jukebox.song(
    id text,
    artist_id text,
    name text,
    url text,
    rating double,
    PRIMARY KEY (id, rating)
);

CREATE TABLE jukebox.artist(
    id text,
    name text,
    rating double,
    PRIMARY KEY (id, rating)
);

CREATE TABLE jukebox.playlist(
    id text,
    name text,
    songs list<text>,
    movies list<text>,
    rating double,
    PRIMARY KEY (id, rating)
);

CREATE TABLE jukebox.search_index(
    search_type text,
    value text,
    ids list<text>,
    PRIMARY KEY(search_type, value)
);

CREATE TABLE jukebox.trending_search(
    id text,
    count bigint,
    PRIMARY KEY(id, count)
);

CREATE MATERIALIZED VIEW jukebox.mv_song_by_artist_id AS
    SELECT * FROM jukebox.song
        WHERE id IS NOT NULL AND artist_id IS NOT NULL AND rating IS NOT NULL
        PRIMARY KEY (artist_id, id, rating);
