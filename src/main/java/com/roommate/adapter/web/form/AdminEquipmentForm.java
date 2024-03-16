package com.roommate.adapter.web.form;

import jakarta.validation.constraints.NotEmpty;

public record AdminEquipmentForm(@NotEmpty(message = "Bitte geben sie einen Namen für die Ausstattung ein!") String equipment_name) {
}
