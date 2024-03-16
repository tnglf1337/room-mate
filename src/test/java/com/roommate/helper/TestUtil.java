package com.roommate.helper;

import roommate.applicationService.ReservationBuilder;
import roommate.domain.model.Reservation;
import java.util.LinkedList;
import java.util.List;

public class TestUtil {

    public static Reservation testReservation1() {
        ReservationBuilder rb = new ReservationBuilder();
        Reservation r = rb.withId(null)
                .withDate("2024-01-01")
                .withBegin("10:00")
                .withEnd("12:00")
                .withUserName("peterpan")
                .withSeat("0.0.1")
                .build();

        return  r;
    }

    public static Reservation testReservation2() {
        ReservationBuilder rb = new ReservationBuilder();
        Reservation r = rb.withId(null)
                .withDate("2024-01-02")
                .withBegin("10:00")
                .withEnd("12:00")
                .withUserName("julia666")
                .withSeat("0.0.1")
                .build();

        return  r;
    }

    public static Reservation testReservation3() {
        ReservationBuilder rb = new ReservationBuilder();
        Reservation r = rb.withId(null)
                .withDate("2024-01-03")
                .withBegin("10:00")
                .withEnd("12:00")
                .withUserName("simbo")
                .withSeat("0.0.1")
                .build();

        return  r;
    }

    public static List<Reservation> testReservationList() {
        List<Reservation> l = new LinkedList<>();
        l.add(TestUtil.testReservation1());
        l.add(TestUtil.testReservation2());
        l.add(TestUtil.testReservation3());

        return  l;
    }

    public static Reservation sampleReservation() {
        ReservationBuilder rb = new ReservationBuilder();
        Reservation r = rb.withId(null)
                .withDate("2024-01-16")
                .withBegin("10:00")
                .withEnd("12:00")
                .withUserName("testuser")
                .withSeat("0.0.2")
                .build();

        return  r;
    }

}
