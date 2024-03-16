CREATE TABLE IF NOT EXISTS equipment(
                                        equipment_id serial PRIMARY KEY,
                                        equipment_name VARCHAR(256) UNIQUE
    );