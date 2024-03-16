package com.roommate.applicationService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDatePreparer {

    public static LocalDateTime prepareLocalDate(String date, String time) {
        LocalDate d = LocalDate.parse(date);
        LocalTime t = LocalTime.parse(time);

        return LocalDateTime.of(d, t);
    }
}
