package com.booking.service;

import com.booking.domain.Equipment;
import com.booking.domain.EquipmentDTO;
import com.booking.domain.EquipmentResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class EquipmentMapper {
    public Equipment toEquipment(EquipmentDTO equipmentDTO){
        if (equipmentDTO == null){
            throw new NullPointerException("The Equipment DTO should no be null");
        }

        Equipment equipment = new Equipment();

        equipment.setName(equipmentDTO.name());
        equipment.setDescription(equipmentDTO.description());
        equipment.setAvailability(equipmentDTO.availability());

        equipment.setLength(equipmentDTO.length());
        equipment.setHeight(equipmentDTO.height());
        equipment.setWeight(equipmentDTO.weight());
        equipment.setFunction(equipmentDTO.function());

        return equipment;
    }

    public EquipmentResponseDTO toEquipmentResponseDTO(Equipment equipment){
//        String function = "";
//        if (equipment.getFunction() ==  null){
//            function = "No function assigned";
//        }
//        assert equipment.getFunction() != null;
        return new EquipmentResponseDTO(
                equipment.getName(),
                equipment.getDescription(),
                equipment.getAvailability(),
                equipment.getFunction().toString()
        );
    }
}
