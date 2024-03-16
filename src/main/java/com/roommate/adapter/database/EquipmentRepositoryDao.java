package com.roommate.adapter.database;

import com.roommate.domain.model.Equipment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EquipmentRepositoryDao extends CrudRepository<Equipment, Long> {

    List<Equipment> findAll();
}
