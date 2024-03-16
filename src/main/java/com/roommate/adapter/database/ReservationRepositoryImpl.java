package com.roommate.adapter.database;

import org.springframework.stereotype.Repository;
import roommate.applicationService.ReservationRepository;
import roommate.domain.model.Reservation;
import java.util.List;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationRepositoryDao reservationRepositoryDao;

    public ReservationRepositoryImpl(ReservationRepositoryDao reservationRepositoryDao) {
        this.reservationRepositoryDao = reservationRepositoryDao;
    }


    @Override
    public List<Reservation> findAll() {
        return reservationRepositoryDao.findAll();
    }

    @Override
    public List<Reservation> findReservationsByUserName(String user_name) {
        return reservationRepositoryDao.findReservationsByUserName(user_name);
    }

    @Override
    public List<Reservation> findReservationsByRealName(String realName) {
        return reservationRepositoryDao.findReservationByRealName(realName);
    }

    @Override
    public int deleteReservationByReservation_id(int reservation_id) {
        return reservationRepositoryDao.deleteReservationByReservation_id(reservation_id);
    }

    @Override
    public List<Reservation> findReservationByDateAndSeat(String reservation_date, String seat) {
        return reservationRepositoryDao.findReservationByDateAndSeat(reservation_date, seat);
    }

    @Override
    public List<Reservation> findReservationBySeat(String seat) {
        return reservationRepositoryDao.findReservationBySeat(seat);
    }

    @Override
    public int countByReservationId() {
        return reservationRepositoryDao.countByReservationId();
    }

    @Override
    public void save(Reservation r) {
        reservationRepositoryDao.save(r);
    }
}
