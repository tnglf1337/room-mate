package com.roommate;

import com.roommate.applicationService.LocalDatePreparer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LocalDatePreparerTest {

    @DisplayName("Methode gibt korrektes Datum wieder")
    @Test
    void test_01() {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.of(2024, 1, 1), LocalTime.of(8, 30));

        LocalDateTime toTest = LocalDatePreparer.prepareLocalDate("2024-01-01", "08:30");

        assertThat(toTest).isEqualTo(ldt);
    }
}
