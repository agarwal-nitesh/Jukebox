DROP KEYSPACE IF EXISTS jukebox;
CREATE KEYSPACE jukebox WITH replication = {'class':'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE jukebox.movie(
    id text,
    artist_id text,
    name text,
    url text,
    rating double,
    PRIMARY KEY (id)
);

CREATE INDEX movie_name_index ON jukebox.movie(name);
CREATE INDEX movie_rating_index ON jukebox.movie(rating);


CREATE TABLE jukebox.song(
    id text,
    artist_id text,
    name text,
    url text,
    rating double,
    PRIMARY KEY (id)
);

CREATE INDEX song_name_index ON jukebox.song(name);
CREATE INDEX song_rating_index ON jukebox.song(rating);


CREATE TABLE jukebox.artist(
    id text,
    name text,
    rating double,
    PRIMARY KEY (id)
);

CREATE INDEX artist_name_index ON jukebox.artist(name);
CREATE INDEX artist_rating_index ON jukebox.artist(rating);


CREATE TABLE jukebox.playlist(
    id text,
    name text,
    songs list<text>,
    movies list<text>,
    rating double,
    PRIMARY KEY (id)
);

CREATE INDEX playlist_name_index ON jukebox.playlist(name);
CREATE INDEX playlist_rating_index ON jukebox.playlist(rating);

CREATE TABLE jukebox.trending_search(
    id text,
    count bigint,
    PRIMARY KEY(id, count)
);

CREATE MATERIALIZED VIEW jukebox.mv_song_by_artist_id AS
    SELECT * FROM jukebox.song
        WHERE id IS NOT NULL AND artist_id IS NOT NULL
        PRIMARY KEY (artist_id, id);

CREATE MATERIALIZED VIEW jukebox.mv_movie_by_artist_id AS
    SELECT * FROM jukebox.movie
        WHERE id IS NOT NULL AND artist_id IS NOT NULL
        PRIMARY KEY (artist_id, id);
