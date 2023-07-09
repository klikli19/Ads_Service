-- liquibase formatted sql
-- changeset Alex:1
CREATE TABLE image
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    media_Type TEXT    NOT NULL,
    data      BYTEA
);
-- changeset klikli:1
CREATE TABLE users
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    phone      VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR DEFAULT 'USER',
    image_id   BIGINT REFERENCES image (id)
);

-- changeset MGubina:1
CREATE TABLE ads
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    description TEXT,
    price       INTEGER,
    title       TEXT,
    user_id     BIGINT REFERENCES users (id),
    image_id    BIGINT REFERENCES image(id)
);
-- changeset ilya:1
CREATE TABLE comments
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    created_at TIMESTAMP,
    text      TEXT NOT NULL,
    author_id   BIGINT REFERENCES users (id),
    ad_id     BIGINT REFERENCES ads (id)
);

