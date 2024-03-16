package com.roommate.domain.model.keymaster;

import java.util.UUID;

public record Access(UUID key, UUID room) {
}
