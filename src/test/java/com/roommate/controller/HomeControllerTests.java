package com.roommate.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.roommate.adapter.web.controller.HomeController;
import com.roommate.applicationService.ReservationRepository;
import com.roommate.applicationService.ReservationService;
import com.roommate.helper.WithMockOAuth2User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

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

    @DisplayName("Für einen angemeldeten User werden Reservierungsdaten angezeigt")
    @Test
    @WithMockOAuth2User(login = "tnglf1337")
    public void test_02() throws Exception {

        String s = mvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("gitHubLogin", "tnglf1337"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Document html = Jsoup.parse(s);
        assertThat(html.select("tr[id=single-reservation]")).isNotNull();


    }


}
