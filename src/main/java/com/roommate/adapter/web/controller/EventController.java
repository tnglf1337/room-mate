package com.roommate.adapter.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.roommate.applicationService.EventService;
import com.roommate.applicationService.ReservationRepository;
import com.roommate.applicationService.SeatRepository;
import com.roommate.domain.model.keymaster.Access;
import com.roommate.domain.model.keymaster.Key;
import com.roommate.domain.model.keymaster.Room;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EventController {

    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;

    public EventController(SeatRepository seatRepository, ReservationRepository reservationRepository) {
        this.seatRepository = seatRepository;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/api/access")
    public List<Access> send() throws JsonProcessingException {

        EventService es = new EventService(seatRepository, reservationRepository);

        List<Room> rooms = es.fetchRooms();
        List<Key> keys = es.fetchKeys();
        es.setUuids(rooms);

        List<Access> allAccesses = es.createAccessList(keys, rooms);

        System.out.println(allAccesses);

        return allAccesses;
    }










}
