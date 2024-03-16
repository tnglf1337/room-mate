package com.roommate.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

public class Seat {

    @Id
    private final Long id;
    private final String roomNumber;
    private final String roomUuid;

    @PersistenceCreator
    public Seat(Long id, String roomNumber, String roomUuid) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomUuid = roomUuid;
    }

    public Seat(String roomNumber) {
        this(null, roomNumber, null);
    }



    public String getRoomNumber() {
        return roomNumber;
    }
}
