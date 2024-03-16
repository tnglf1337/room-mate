package com.roommate.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

public class Equipment {

    @Id
    Long equipment_id;
    String equipment_name;

    @PersistenceCreator
    public Equipment(Long equipment_id, String equipment_name) {
        this.equipment_id = equipment_id;
        this.equipment_name = equipment_name;
    }

    public Equipment(String equipment_name) {
        this(null, equipment_name);
    }

    public Long getEquipment_id() {
        return equipment_id;
    }

    public String getEquipment_name() {
        return equipment_name;
    }
}
