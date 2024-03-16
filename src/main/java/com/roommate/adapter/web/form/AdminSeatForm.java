package com.roommate.adapter.web.form;

import jakarta.validation.constraints.Pattern;

public record AdminSeatForm(@Pattern(regexp = "^\\d+\\.\\d+\\.\\d+$") String room_number) {
}
