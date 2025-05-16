package com.booking.controller;

import com.booking.domain.Equipment;
import com.booking.domain.EquipmentDTO;
import com.booking.domain.EquipmentResponseDTO;
import com.booking.service.EquipmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {
    private final EquipmentService service;

    public EquipmentController(EquipmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createEquipment(@Valid @RequestBody EquipmentDTO equipmentDTO, BindingResult result){
        if (result.hasFieldErrors()){
           return validation(result);
        }

        return ResponseEntity.ok().body(this.service.createEquipment(equipmentDTO));
    }

    @GetMapping
    public List<EquipmentResponseDTO> equipments(){
        return this.service.allEquipment();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEquipment(@Valid @RequestBody Equipment equipment, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }

        return ResponseEntity.ok().body(this.service.updateEquipment(equipment));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEquipment(@PathVariable("id") Integer id){
        this.service.deleteEquipment(id);
    }

    private ResponseEntity<Map<String, String>> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(
                fieldError -> errors.put(fieldError.getField(), ": " + fieldError.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }

}
