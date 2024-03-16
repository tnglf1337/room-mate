package com.roommate.adapter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import roommate.applicationService.ReservationRepository;
import roommate.adapters.web.form.DeleteForm;
import roommate.applicationService.ReservationService;

@Controller
public class HomeController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(OAuth2AuthenticationToken auth,
                               Model m) {

        ReservationService rs = new ReservationService(reservationRepository);

        boolean reservationsAvailable = rs.areReservationsAvailableByUser(auth.getPrincipal().getAttribute("login"));

        String gitHubLogin = auth.getPrincipal().getAttribute("login");
        String realName = auth.getPrincipal().getAttribute("name");

        m.addAttribute("gitHubLogin", gitHubLogin);
        m.addAttribute("realName", realName);
        m.addAttribute("userReservations", reservationRepository.findReservationsByUserName(gitHubLogin));
        m.addAttribute("reservationAvailable", reservationsAvailable);

        return "home.html";
    }

    @PostMapping("/home/delete")
    public String removeReservation(DeleteForm deleteForm) {
        reservationRepository.deleteReservationByReservation_id(deleteForm.idToDelete());

        return "redirect:/home";
    }
}
