package com.roommate.adapter.database;

import org.springframework.stereotype.Repository;
import roommate.applicationService.EquipmentRepository;
import roommate.domain.model.Equipment;
import java.util.List;

@Repository
public class EquipmentRepositoryImpl implements EquipmentRepository {

    private final EquipmentRepositoryDao equipmentRepositoryDao;


    public EquipmentRepositoryImpl(EquipmentRepositoryDao equipmentRepositoryDao) {
        this.equipmentRepositoryDao = equipmentRepositoryDao;
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentRepositoryDao.findAll();
    }

    @Override
    public Equipment save(Equipment equipment) {
        return equipmentRepositoryDao.save(equipment);
    }
}
