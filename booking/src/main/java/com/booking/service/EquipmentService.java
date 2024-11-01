package com.booking.service;

import com.booking.domain.Equipment;
import com.booking.domain.EquipmentDTO;
import com.booking.domain.EquipmentRepository;
import com.booking.domain.EquipmentResponseDTO;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService {

    private final EquipmentRepository repository;
    private final EquipmentMapper mapper;

    public EquipmentService(EquipmentRepository repository, EquipmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public EquipmentResponseDTO createEquipment(EquipmentDTO equipmentDTO){
        Equipment equipment = this.mapper.toEquipment(equipmentDTO);

        return this.mapper.toEquipmentResponseDTO(this.repository.save(equipment));
    }

    public List<EquipmentResponseDTO> allEquipment(){
        return this.repository.findAll()
                .stream()
                .map(mapper::toEquipmentResponseDTO)
                .collect(Collectors.toList());

    }

    public EquipmentResponseDTO updateEquipment(Equipment e){
        return this.mapper.toEquipmentResponseDTO(this.repository.save(e));
    }

    public void deleteEquipment(Integer e){
        this.repository.deleteById(e);
    }
}
