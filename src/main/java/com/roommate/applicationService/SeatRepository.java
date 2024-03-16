package com.roommate.applicationService;

import org.springframework.stereotype.Repository;
import roommate.domain.model.Seat;
import java.util.List;

public interface SeatRepository{

    List<Seat> findAll();

    List<String> findSeatsByEquipment(String equipment);

    void linkEquipmentToSeat(String equipment_name, String room_number);

    void save(Seat s);

    void setUuidtoRoom(String uuid, String room);

    String getUuid(String roomNumber);
}
