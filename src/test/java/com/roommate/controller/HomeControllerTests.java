package com.roommate.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.roommate.adapter.web.controller.HomeController;
import com.roommate.applicationService.ReservationRepository;
import com.roommate.applicationService.ReservationService;
import com.roommate.domain.model.Reservation;
import com.roommate.helper.TestUtil;
import com.roommate.helper.WithMockOAuth2User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


@WebMvcTest(HomeController.class)
public class HomeControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    ReservationRepository reservationRepository;

    @DisplayName("Die Seite home.html ist erreichbar")
    @Test
    @WithMockOAuth2User(login = "testuser")
    public void test_01() throws Exception {
        mvc.perform(get("/home"))
                .andExpect(status().isOk());
    }


    @DisplayName("Reservierungen werden im Dashboard angezeigt")
    @Test
    @WithMockOAuth2User(login = "testuser")
    public void test_02() throws Exception {
        String testUser = "peterpan";
        List<Reservation> reservationList = TestUtil.testReservationList();
        ReservationService reservationService = new ReservationService(reservationRepository);

        when(reservationService.areReservationsAvailableByUser(testUser)).thenReturn(true);

        when(reservationService.findReservationsByUserName(testUser)).thenReturn(reservationList);

        String testHtml = mvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Document html = Jsoup.parse(testHtml);

        assertThat(html.select("tr[id=single-reservation]")).isEqualTo("single-reservation");
    }

}
