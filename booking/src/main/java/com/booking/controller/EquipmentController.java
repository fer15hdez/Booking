package com.booking.controller;

import com.booking.domain.Equipment;
import com.booking.domain.EquipmentDTO;
import com.booking.domain.EquipmentResponseDTO;
import com.booking.service.EquipmentService;
import org.apache.catalina.LifecycleState;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public List<EquipmentResponseDTO> equipments(){
        return this.service.allEquipment();
    }

    @PutMapping("/update")
    public EquipmentResponseDTO updateEquipment(@RequestBody Equipment equipment){
        return this.service.updateEquipment(equipment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEquipment(@PathVariable("id") Integer id){
        this.service.deleteEquipment(id);
    }

}
