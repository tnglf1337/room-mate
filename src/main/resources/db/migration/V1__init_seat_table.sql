CREATE TABLE IF NOT EXISTS seat (
    id serial,
    room_number VARCHAR(10) PRIMARY KEY,
    room_uuid VARCHAR(40)
);