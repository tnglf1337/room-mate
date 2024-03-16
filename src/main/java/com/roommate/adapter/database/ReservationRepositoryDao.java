package com.roommate.adapter.database;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import roommate.domain.model.Reservation;
import java.util.List;

public interface ReservationRepositoryDao extends CrudRepository<Reservation, Long> {
    List<Reservation> findAll();

    @Query("SELECT * FROM Reservation r WHERE r.user_name = :user_name")
    List<Reservation> findReservationsByUserName(@Param("user_name") String user_name);

    @Modifying
    @Query("DELETE FROM reservation r WHERE r.reservation_id = :reservation_id")
    int deleteReservationByReservation_id(@Param("reservation_id") int reservation_id);

    @Query("SELECT * FROM Reservation r WHERE r.reservation_date = :reservation_date AND seat = :seat")
    List<Reservation> findReservationByDateAndSeat(@Param("reservation_date") String reservation_date,
                                                   @Param("seat") String seat);

    @Query("SELECT * FROM Reservation r WHERE r.seat = :seat ORDER BY r.reservation_date")
    List<Reservation> findReservationBySeat(@Param("seat") String seat);

    @Query("SELECT COUNT(reservation_id) FROM reservation")
    int countByReservationId();

    @Query("SELECT * FROM reservation WHERE real_name = :realName")
    List<Reservation> findReservationByRealName(@Param("realName") String realName);
}
