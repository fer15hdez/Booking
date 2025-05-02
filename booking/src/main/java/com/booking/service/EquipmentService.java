package com.booking.service;

import com.booking.domain.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class EquipmentService {

    private final EquipmentRepository repository;
    private final EquipmentMapper mapper;
    private final FunctionTypeRepository functionTypeRepository;
    Logger logger = Logger.getLogger(getClass().getName());

    public EquipmentService(EquipmentRepository repository, EquipmentMapper mapper, FunctionTypeRepository functionTypeRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.functionTypeRepository = functionTypeRepository;
    }

    public EquipmentResponseDTO createEquipment(EquipmentDTO equipmentDTO){
        Equipment equipment = this.mapper.toEquipment(equipmentDTO);

        List<Integer> functionTypesIds = new ArrayList<>();
        equipment.getFunction().forEach(functionType -> {
            System.out.println("functionType: " + functionType);
            functionTypesIds.add(functionType.getId());
        });

        if (!functionTypesIds.isEmpty()){
            for (Integer id : functionTypesIds) {
                this.functionTypeRepository.findById(id).orElseThrow(
                        () -> {
                            logger.info("No found functionType entity with id: " + id);
                            return new EntityNotFoundException("No found FunctionType");

                        }
                );
            }
        }

        return this.mapper.toEquipmentResponseDTO(this.repository.save(equipment));
    }

    public List<EquipmentResponseDTO> allEquipment(){
        return this.repository.findAll()
                .stream()
                .map(mapper::toEquipmentResponseDTO)
                .collect(Collectors.toList());

    }

    public EquipmentResponseDTO updateEquipment(Equipment e){
        this.repository.findById(e.getId()).orElseThrow(
                () -> {
                    logger.info("No found entity");
                    return new EntityNotFoundException();
                }
        );

        return this.mapper.toEquipmentResponseDTO(this.repository.save(e));
    }

    public void deleteEquipment(Integer id){
        this.repository.findById(id).orElseThrow(
                () -> {
                    logger.info("No found entity");
                    return new EntityNotFoundException();
                }
        );
        this.repository.deleteById(id);
    }
}
