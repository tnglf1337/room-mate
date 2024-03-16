package com.roommate.adapter.database;

import com.roommate.domain.model.Seat;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SeatRepositoryDao extends CrudRepository<Seat, Long> {

    List<Seat> findAll();

    @Query("SELECT seat FROM seatwithequipment WHERE equipment = :equipment ")
    List<String> findSeatsByEquipment(@Param("equipment") String equipment);

    @Modifying
    @Query("INSERT INTO seatwithequipment VALUES(:room_number, :equipment_name)")
    void linkEquipmentToSeat(@Param("equipment_name") String equipment_name,
                             @Param("room_number") String room_number);


    @Modifying
    @Query("UPDATE seat SET room_uuid = :uuid WHERE room_number = :room")
    void setUuidtoRoom(@Param("uuid") String uuid,
                       @Param("room") String room);

    @Query("SELECT room_uuid FROM seat WHERE room_number = :roomNumber")
    String findUuidByRoomNumber(@Param("roomNumber") String roomNumber);
}
