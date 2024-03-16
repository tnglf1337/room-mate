package com.roommate.applicationService;

import org.springframework.stereotype.Repository;
import roommate.domain.model.Equipment;
import java.util.List;

@Repository
public interface EquipmentRepository {
    List<Equipment> findAll();

    Equipment save(Equipment equipment);
}
