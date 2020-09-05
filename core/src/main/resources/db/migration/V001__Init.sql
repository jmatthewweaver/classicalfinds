CREATE TABLE users (
    id          SERIAL        NOT NULL PRIMARY KEY,
    first_name  VARCHAR(100)  NOT NULL,
    last_name   VARCHAR(100)  NOT NULL,
    email       VARCHAR(255)  NOT NULL,
    password    VARCHAR(64)   NOT NULL
);

CREATE TABLE login_tokens (
    id          SERIAL       NOT NULL PRIMARY KEY,
    user_id     BIGINT       REFERENCES users(id),
    token       VARCHAR(32)  NOT NULL,
    expiration  TIMESTAMP    NOT NULL
);

CREATE TABLE email_codes (
    id          SERIAL      NOT NULL PRIMARY KEY,
    user_id     BIGINT      REFERENCES users(id),
    email_code  VARCHAR(6)  NOT NULL,
    expiration  TIMESTAMP   NOT NULL
);

CREATE TABLE images (
    id    SERIAL  NOT NULL PRIMARY KEY,
    data  BYTEA   NOT NULL
);

CREATE TABLE eras (
    id    SERIAL        NOT NULL PRIMARY KEY,
    name  VARCHAR(100)  NOT NULL
);

CREATE TABLE genres (
    id    SERIAL        NOT NULL PRIMARY KEY,
    name  VARCHAR(100)  NOT NULL
);

CREATE TABLE composers (
    id             SERIAL        NOT NULL PRIMARY KEY,
    name           VARCHAR(100)  NOT NULL,
    complete_name  VARCHAR(100),
    era_id         BIGINT        REFERENCES eras(id) NOT NULL,
    birth_date     TIMESTAMP     NOT NULL,
    death_date     TIMESTAMP     NOT NULL,
    popular        BOOLEAN       NOT NULL,
    recommended    BOOLEAN       NOT NULL,
    image_id       BIGINT        REFERENCES images(id)
);

CREATE TABLE works (
    id           SERIAL        NOT NULL PRIMARY KEY,
    title        VARCHAR(100)  NOT NULL,
    subtitle     VARCHAR(100),
    popular      BOOLEAN       NOT NULL,
    recommended  BOOLEAN       NOT NULL,
    genre_id     BIGINT        REFERENCES genres(id)
);

CREATE TABLE performers (
    id        SERIAL        NOT NULL PRIMARY KEY,
    name      VARCHAR(100),
    image_id  BIGINT        REFERENCES images(id)
);

CREATE TABLE performances (
    id            SERIAL  NOT NULL PRIMARY KEY,
    work_id       BIGINT  REFERENCES works(id),
    performer_id  BIGINT  REFERENCES performers(id)
);

CREATE TABLE performance_videos (
    id              SERIAL       NOT NULL PRIMARY KEY,
    performance_id  BIGINT       REFERENCES performances(id),
    sequence        INT          NOT NULL,
    video_id        VARCHAR(50)
);