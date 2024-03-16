package com.roommate.applicationService;

import com.roommate.domain.model.Equipment;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EquipmentRepository {
    List<Equipment> findAll();

    Equipment save(Equipment equipment);
}
