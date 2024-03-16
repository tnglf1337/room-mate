package com.roommate.applicationService;

import com.roommate.domain.model.Reservation;

public class ReservationBuilder {

    private Long reservation_id;

    private String date;

    private String time_begin;

    private String time_end;

    private String user_name;

    private String realName;

    private String seat;

    public ReservationBuilder withId(Long id) {
        reservation_id = id;
        return this;
    }

    public ReservationBuilder withDate(String date) {
        this.date = date;
        return this;
    }

    public ReservationBuilder withBegin(String begin) {
        time_begin = begin;
        return this;
    }

    public ReservationBuilder withEnd(String end) {
        time_end = end;
        return this;
    }

    public ReservationBuilder withUserName(String username) {
        user_name = username;
        return this;
    }

    public ReservationBuilder withRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public ReservationBuilder withSeat(String seat) {
        this.seat = seat;
        return this;
    }

    public Reservation build() {
        return new Reservation(reservation_id, date, time_begin, time_end, user_name, realName, seat);
    }


}
