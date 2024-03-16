package com.roommate.adapter.database;

import com.roommate.applicationService.EquipmentRepository;
import com.roommate.domain.model.Equipment;
import org.springframework.stereotype.Repository;
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
