package com.roommate.domain.model;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Reservation{

    @Id
    private final Long reservation_id;

    private final String reservation_date;

    private final String time_begin;

    private final String time_end;

    private final String user_name;

    private final String realName;

    private final String seat;

    public Reservation(Long reservation_id, String reservation_date, String time_begin, String time_end, String user_name, String realName, String seat) {
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.time_begin = time_begin;
        this.time_end = time_end;
        this.user_name = user_name;
        this.realName = realName;
        this.seat = seat;
    }

    public Long getReservation_id() {
        return reservation_id;
    }

    public String getDate() {
        return reservation_date;
    }

    public String getTime_begin() {
        return time_begin;
    }

    public String getTime_end() {
        return time_end;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getSeat() {
        return seat;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservation_id=" + reservation_id +
                ", reservation_date=" + reservation_date +
                ", time_begin=" + time_begin +
                ", time_end=" + time_end +
                ", user_name='" + user_name + '\'' +
                ", seat='" + seat + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return Objects.equals(getReservation_id(), that.getReservation_id()) && Objects.equals(reservation_date, that.reservation_date) && Objects.equals(getTime_begin(), that.getTime_begin()) && Objects.equals(getTime_end(), that.getTime_end()) && Objects.equals(getUser_name(), that.getUser_name()) && Objects.equals(realName, that.realName) && Objects.equals(getSeat(), that.getSeat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReservation_id(), reservation_date, getTime_begin(), getTime_end(), getUser_name(), realName, getSeat());
    }
}
