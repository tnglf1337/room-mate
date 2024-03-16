package com.roommate.adapter.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import roommate.applicationService.EquipmentRepository;
import roommate.applicationService.ReservationRepository;
import roommate.applicationService.SeatRepository;
import roommate.adapters.web.form.*;
import roommate.config.AdminOnly;
import roommate.domain.model.Equipment;
import roommate.domain.model.Seat;

@Controller
@RequestMapping(path = "admin")
public class AdminController {
    @Autowired ReservationRepository reservationRepository;
    @Autowired EquipmentRepository equipmentRepository;
    @Autowired SeatRepository seatRepository;

    @ModelAttribute("countReservations")
    public int countReservations() {
        return reservationRepository.countByReservationId();
    }

    @GetMapping()
    @AdminOnly
    public String admin(Model model,
                        OAuth2AuthenticationToken auth) {

        model.addAttribute("userReservations", reservationRepository.findAll());
        model.addAttribute("equipmentList", equipmentRepository.findAll());
        model.addAttribute("seatList", seatRepository.findAll());
        String usersRealName = auth.getPrincipal().getAttribute("name");
        model.addAttribute("usersRealName", usersRealName);

        return "admin";
    }

    @PostMapping("/entfernen")
    @AdminOnly
    public String removeReservation(DeleteForm deleteForm) {
        reservationRepository.deleteReservationByReservation_id(deleteForm.idToDelete());
        return "redirect:/admin";
    }

    @GetMapping("/add_equipment")
    @AdminOnly
    public String addEquipment(@ModelAttribute("addEquipmentForm") AdminEquipmentForm addEquipmentForm) {
        return "admin_equipment";
    }

    @PostMapping("/add_equipment")
    @AdminOnly
    public String addEquipment(Model model,
                               @Valid @ModelAttribute("addEquipmentForm") AdminEquipmentForm adminEquipmentForm,
                               BindingResult br) {

        if (br.hasErrors()) {
            System.out.println("equip error");
            return "admin_equipment";
        }

        Equipment e = equipmentRepository.save(new Equipment(null, adminEquipmentForm.equipment_name()));

        return "redirect:/admin";
    }

    @GetMapping("/add_seat")
    @AdminOnly
    public String addEquipment(@ModelAttribute("addSeatForm") AdminSeatForm addSeatForm) {
        return "admin_seat";
    }
    @PostMapping("/add_seat")
    @AdminOnly
    public String addSeat(@ModelAttribute("addSeatForm") AdminSeatForm adminSeatForm) {

        Seat s = new Seat(null, adminSeatForm.room_number(), null);
        seatRepository.save(s);

        return "redirect:/admin";
    }

    @GetMapping("/add_equipment_to_seat")
    @AdminOnly
    public String addEquipmentToSeat(Model model,
                                     @ModelAttribute("addSeatEquipForm") AdminSeatEquipForm addSeatEquipForm) {
        model.addAttribute("equipmentList", equipmentRepository.findAll());
        model.addAttribute("seatList", seatRepository.findAll());

        return "admin_equipment_to_seat";
    }

    @PostMapping("/add_seat_with_equipment")
    @AdminOnly
    public String addSeatWithEquipment(@ModelAttribute("addSeatEquipForm") AdminSeatEquipForm adminSeatEquipForm) {
        seatRepository.linkEquipmentToSeat(adminSeatEquipForm.equipment_name(), adminSeatEquipForm.room_number());

        return "redirect:/admin";
    }
}