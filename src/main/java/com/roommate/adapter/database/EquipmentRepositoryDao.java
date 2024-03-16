package com.roommate.adapter.database;

import org.springframework.data.repository.CrudRepository;
import roommate.domain.model.Equipment;
import java.util.List;

public interface EquipmentRepositoryDao extends CrudRepository<Equipment, Long> {

    List<Equipment> findAll();
}
