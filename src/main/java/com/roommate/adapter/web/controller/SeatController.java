package com.roommate.adapter.web.controller;

import com.roommate.applicationService.ReservationRepository;
import com.roommate.applicationService.ReservationService;
import com.roommate.domain.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SeatController {

    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/seat/{seatId}")
    public String seatIndex(@PathVariable("seatId") String seatId,
                            Model model) {
        ReservationService reservationService = new ReservationService(reservationRepository);
        List<Reservation> reservationList = reservationService.findReservationBySeat(seatId);

        System.out.println(reservationList);

        model.addAttribute("seat", seatId);
        model.addAttribute("reservationList", reservationList);

        return "seat.html";

    }
}
