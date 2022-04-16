CREATE TABLE IF NOT EXISTS movie
(
    id text COLLATE pg_catalog."default" NOT NULL,
    name text COLLATE pg_catalog."default",
    category text COLLATE pg_catalog."default",
    directors text COLLATE pg_catalog."default",
    writers text COLLATE pg_catalog."default",
    actors text COLLATE pg_catalog."default",
    release_date text COLLATE pg_catalog."default",
    CONSTRAINT movie_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE movie
    OWNER to postgres;