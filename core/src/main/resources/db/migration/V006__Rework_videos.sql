DROP TABLE performance_videos;
DROP TABLE performances;

CREATE TABLE work_videos (
    id           SERIAL       PRIMARY KEY,
    work_id      BIGINT       REFERENCES works(id),
    title        TEXT,
    description  TEXT,
    video_id     VARCHAR(20),
    sort_order   INTEGER
)