CREATE TABLE IF NOT EXISTS reservation (
    reservation_id serial PRIMARY KEY,
    reservation_date VARCHAR(50),
    time_begin VARCHAR(20),
    time_end VARCHAR(20),
    user_name VARCHAR(256),
    real_name VARCHAR(256),
    seat VARCHAR(10),
    FOREIGN KEY(seat) REFERENCES seat(room_number)
    );