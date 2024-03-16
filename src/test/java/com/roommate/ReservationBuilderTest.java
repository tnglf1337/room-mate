package com.roommate;

import com.roommate.applicationService.ReservationBuilder;
import com.roommate.domain.model.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReservationBuilderTest {

    @DisplayName("Builder baut korrekte Reservierung")
    @Test
    void test_01() {
        Reservation a = new Reservation(
                1L,
                "24-01-01",
                "08:00",
                "09:00",
                "a",
                "b",
                "0.0.1");

        ReservationBuilder rb = new ReservationBuilder();

        Reservation b = rb.withId(1L)
                .withDate("24-01-01")
                .withBegin("08:00")
                .withEnd("09:00")
                .withUserName("a")
                .withRealName("b")
                .withSeat("0.0.1")
                .build();

        assertThat(a).isEqualTo(b);

    }
}
