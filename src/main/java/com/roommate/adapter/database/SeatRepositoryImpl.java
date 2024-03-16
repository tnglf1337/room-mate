package com.roommate.adapter.database;

import org.springframework.stereotype.Repository;
import roommate.applicationService.SeatRepository;
import roommate.domain.model.Seat;
import java.util.List;

@Repository
public class SeatRepositoryImpl implements SeatRepository {

    private final SeatRepositoryDao seatRepositoryDao;


    public SeatRepositoryImpl(SeatRepositoryDao seatRepositoryDao) {
        this.seatRepositoryDao = seatRepositoryDao;
    }

    @Override
    public List<Seat> findAll() {
        return seatRepositoryDao.findAll();
    }

    @Override
    public List<String> findSeatsByEquipment(String equipment) {
        return seatRepositoryDao.findSeatsByEquipment(equipment);
    }

    @Override
    public void linkEquipmentToSeat(String equipment_name, String room_number) {
        seatRepositoryDao.linkEquipmentToSeat(equipment_name, room_number);
    }

    @Override
    public void save(Seat s) {
        seatRepositoryDao.save(s);
    }

    @Override
    public void setUuidtoRoom(String uuid, String room) {
        seatRepositoryDao.setUuidtoRoom(uuid, room);
    }

    @Override
    public String getUuid(String roomNumber) {
        return seatRepositoryDao.findUuidByRoomNumber(roomNumber);
    }
}
