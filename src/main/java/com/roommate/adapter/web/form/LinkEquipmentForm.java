package com.roommate.adapter.web.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record LinkEquipmentForm(String equip_name,
                                @Pattern(regexp = "\\d+(\\.\\d+){2}", message = "Platz-Nr. im falschen Format. Benutze x.y.z")
                                @NotEmpty(message = "Bitte gib eine Sitzplatz-Nummer ein")
                                String seat_number) {
}
