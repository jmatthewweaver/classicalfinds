ALTER TABLE works
    ADD COLUMN composer_id BIGINT REFERENCES composers (id);