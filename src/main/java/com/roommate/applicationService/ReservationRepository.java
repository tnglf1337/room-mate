package com.roommate.applicationService;

import org.springframework.stereotype.Repository;
import roommate.domain.model.Reservation;
import java.util.List;

public interface ReservationRepository{
    List<Reservation> findAll();
    List<Reservation> findReservationsByUserName(String user_name);
    List<Reservation> findReservationsByRealName(String realName);
    int deleteReservationByReservation_id(int reservation_id);
    List<Reservation> findReservationByDateAndSeat(String reservation_date, String seat);
    List<Reservation> findReservationBySeat(String seat);
    int countByReservationId();

    void save(Reservation r);
}
