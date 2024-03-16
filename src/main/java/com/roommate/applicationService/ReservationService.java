package com.roommate.applicationService;

import org.springframework.stereotype.Service;
import roommate.domain.model.Reservation;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    public boolean isReservationPossible(LocalDateTime from, LocalDateTime to, String seat) {
        List<Reservation> allReservations = reservationRepository.findReservationByDateAndSeat(from.toLocalDate().toString(), seat);

        boolean reservationAvailable = true;

        for(int i = 0; i < allReservations.size(); i++) {
            Reservation currentReservation = allReservations.get(i);
            LocalDateTime ldtBegin = LocalDatePreparer.prepareLocalDate(currentReservation.getDate(), currentReservation.getTime_begin());
            LocalDateTime ldtEnd = LocalDatePreparer.prepareLocalDate(currentReservation.getDate(), currentReservation.getTime_end());

            if(from.isBefore(ldtBegin) && to.isEqual(ldtBegin)) {
                reservationAvailable = true;
            } else if(from.isEqual(ldtEnd) && to.isAfter(ldtEnd)) {
                reservationAvailable = true;
            } else {
                reservationAvailable = false;
            }
        }

        return reservationAvailable;
    }

    public boolean areReservationsAvailableInGeneral() {
        List<Reservation> l = reservationRepository.findAll();

        if(l.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean areReservationsAvailableByUser(String user_name) {
        List<Reservation> l = reservationRepository.findReservationsByUserName(user_name);

        if(l.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public List<Reservation> findReservationsByUserName(String userName) {
        List<Reservation> l = reservationRepository.findReservationsByUserName(userName);
        return l;
    }

    public List<Reservation> findReservationByDateAndSeat(String date, String seat) {
        return reservationRepository.findReservationByDateAndSeat(date, seat);
    }

    public List<Reservation> findReservationBySeat(String seat) {
        return reservationRepository.findReservationBySeat(seat);
    }
}