package com.roommate.adapter.web.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import roommate.applicationService.EquipmentRepository;
import roommate.applicationService.ReservationRepository;
import roommate.applicationService.SeatRepository;
import roommate.adapters.web.form.ReservationDateForm;
import roommate.applicationService.LocalDatePreparer;
import roommate.applicationService.ReservationBuilder;
import roommate.applicationService.ReservationService;
import roommate.applicationService.SeatService;
import roommate.domain.model.Reservation;
import roommate.adapters.web.form.ReservationSeatForm;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class ReservationController {

    @Autowired private SeatRepository seatRepository;
    @Autowired private EquipmentRepository equipmentRepository;
    @Autowired private ReservationRepository reservationRepository;

    @GetMapping("/reservation_seat")
    public String reservationSeat(Model model) {
        model.addAttribute("equipmentList", equipmentRepository.findAll());
        model.addAttribute("seats", seatRepository.findAll());

        return "reservation_seat";
    }

    @PostMapping("/reservation_seat")
    public String postReservationSeat(Model model,
                                  ReservationSeatForm reservationSeatForm,
                                  OAuth2AuthenticationToken auth,
                                  HttpServletResponse response
    ) {

        model.addAttribute("postRequestDone", true);
        model.addAttribute("equipmentList", equipmentRepository.findAll());
        model.addAttribute("seats", seatRepository.findAll());
        model.addAttribute("desiredEquipment", reservationSeatForm.checkbox());
        model.addAttribute("desiredSeat", reservationSeatForm.seat());
        response.addCookie(new Cookie("desiredSeatCookie", reservationSeatForm.seat()));

        SeatService seatService = new SeatService(seatRepository);

        boolean seatsAvailable = false;

        if(reservationSeatForm.checkbox() == null) {
            seatsAvailable = true;
        } else {
            seatsAvailable = seatService.seatWithEquipment(reservationSeatForm.seat(), reservationSeatForm.checkbox().split(","));
            model.addAttribute("seatsWithEquipment", seatService.getSeatsWithEquipment(reservationSeatForm.checkbox().split(",")));
        }

        model.addAttribute("seatsAvailable", seatsAvailable);

        return "reservation_seat";
    }

    @GetMapping("/reservation_date")
    public String reservationDate(Model model) {
        model.addAttribute("minDate", LocalDate.now());

        return "reservation_date";
    }

    @PostMapping("/reservation_date")
    public String postReservationDate(Model model,
                                      @CookieValue("desiredSeatCookie") String desiredSeat,
                                      ReservationDateForm reservationDateForm,
                                      OAuth2AuthenticationToken auth) {

        ReservationService reservationService = new ReservationService(reservationRepository);
        LocalDateTime from = LocalDatePreparer.prepareLocalDate(reservationDateForm.date(), reservationDateForm.timeBegin());
        LocalDateTime to = LocalDatePreparer.prepareLocalDate(reservationDateForm.date(), reservationDateForm.timeEnd());

        boolean reservationPossible = reservationService.isReservationPossible(from, to, desiredSeat);

        model.addAttribute("postRequestDone", true);
        model.addAttribute("reservationPossible", reservationPossible);
        model.addAttribute("desiredSeat", desiredSeat);

        if(reservationPossible) {
            ReservationBuilder rb = new ReservationBuilder();
            Reservation r = rb.withId(null)
                    .withDate(reservationDateForm.date())
                    .withBegin(reservationDateForm.timeBegin())
                    .withEnd(reservationDateForm.timeEnd())
                    .withUserName(auth.getPrincipal().getAttribute("login"))
                    .withRealName(auth.getPrincipal().getAttribute("name"))
                    .withSeat(desiredSeat)
                    .build();

            reservationRepository.save(r);
        } else {
            return "reservation_date";
        }



        return "redirect:/home";
    }

}
