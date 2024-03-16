CREATE TABLE IF NOT EXISTS seatWithEquipment (
                                    seat VARCHAR(10) references seat(room_number),
                                    equipment VARCHAR(100)
    );