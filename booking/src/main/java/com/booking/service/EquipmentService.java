package com.booking.service;

import com.booking.domain.*;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService {

    private final EquipmentRepository repository;
    private final EquipmentMapper mapper;
    private final FunctionTypeRepository functionTypeRepository;

    public EquipmentService(EquipmentRepository repository, EquipmentMapper mapper, FunctionTypeRepository functionTypeRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.functionTypeRepository = functionTypeRepository;
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

    public void deleteEquipment(Integer id){
        this.repository.deleteById(id);
    }
}
