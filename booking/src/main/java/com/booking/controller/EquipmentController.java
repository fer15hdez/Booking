package com.booking.controller;

import com.booking.domain.EquipmentDTO;
import com.booking.domain.EquipmentResponseDTO;
import com.booking.service.EquipmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {
    private final EquipmentService service;

    public EquipmentController(EquipmentService service) {
        this.service = service;
    }

    @PostMapping
    public EquipmentResponseDTO createEquipment(@RequestBody EquipmentDTO equipmentDTO){
        return this.service.createEquipment(equipmentDTO);

    }

}
