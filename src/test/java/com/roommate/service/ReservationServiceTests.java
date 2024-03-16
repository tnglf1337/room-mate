package com.roommate.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.roommate.applicationService.LocalDatePreparer;
import com.roommate.applicationService.ReservationRepository;
import com.roommate.applicationService.ReservationService;
import com.roommate.domain.model.Reservation;
import com.roommate.helper.TestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJdbcTest
public class ReservationServiceTests {

    static ReservationService reservationService;

    static List<Reservation> reservationList;

    private final String SEAT = "0.0.2";

    @BeforeAll
    static void init() {
        reservationService = new ReservationService(mock(ReservationRepository.class));
        reservationList = new ArrayList<>();
        reservationList.add(TestUtil.sampleReservation());
    }

    @DisplayName("Reservierungen außerhalb der Zeit 10:00-12:00 sind möglich")
    @Test
    void test_01() {

        when(reservationService.findReservationByDateAndSeat(anyString(), anyString())).thenReturn(reservationList);

        LocalDateTime before1 = LocalDatePreparer.prepareLocalDate("2024-01-16", "09:00");
        LocalDateTime before2 = LocalDatePreparer.prepareLocalDate("2024-01-16", "10:00");

        LocalDateTime after1 = LocalDatePreparer.prepareLocalDate("2024-01-16", "12:00");
        LocalDateTime after2 = LocalDatePreparer.prepareLocalDate("2024-01-16", "14:00");

        boolean beforeIsPossible = reservationService.isReservationPossible(before1, before2, SEAT);
        boolean afterIsPossible = reservationService.isReservationPossible(after1, after2, SEAT);

        assertTrue(beforeIsPossible);
        assertTrue(afterIsPossible);
    }

    @DisplayName("Reservierungen innerhalb der Zeit 10:00-12:00 sind nicht möglich")
    @Test
    void test_02() {
        when(reservationService.findReservationByDateAndSeat(anyString(), anyString())).thenReturn(reservationList);

        LocalDateTime inside1 = LocalDatePreparer.prepareLocalDate("2024-01-16", "10:00");
        LocalDateTime inside2 = LocalDatePreparer.prepareLocalDate("2024-01-16", "11:00");

        LocalDateTime inside3 = LocalDatePreparer.prepareLocalDate("2024-01-16", "11:00");
        LocalDateTime inside4 = LocalDatePreparer.prepareLocalDate("2024-01-16", "12:00");

        boolean beforeIsNotPossible = reservationService.isReservationPossible(inside1, inside2, SEAT);
        boolean afterIsNotPossible = reservationService.isReservationPossible(inside3, inside4, SEAT);

        assertFalse(beforeIsNotPossible);
        assertFalse(afterIsNotPossible);
    }

    @DisplayName("Reservierungen, die den Slot 10:00-12:00 am Anfang und am Ende schneiden, sind nicht möglich")
    @Test
    void test_03() {
        when(reservationService.findReservationByDateAndSeat(anyString(), anyString())).thenReturn(reservationList);

        LocalDateTime cut1 = LocalDatePreparer.prepareLocalDate("2024-01-16", "09:00");
        LocalDateTime cut2 = LocalDatePreparer.prepareLocalDate("2024-01-16", "11:00");

        LocalDateTime cut3 = LocalDatePreparer.prepareLocalDate("2024-01-16", "11:00");
        LocalDateTime cut4 = LocalDatePreparer.prepareLocalDate("2024-01-16", "13:00");

        boolean beforeIsNotPossible = reservationService.isReservationPossible(cut1, cut2, SEAT);
        boolean afterIsNotPossible = reservationService.isReservationPossible(cut3, cut4, SEAT);

        assertFalse(beforeIsNotPossible);
        assertFalse(afterIsNotPossible);
    }
}
