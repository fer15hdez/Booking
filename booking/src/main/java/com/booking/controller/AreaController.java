package com.booking.controller;

import com.booking.domain.Area;
import com.booking.service.AreaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/areas")
public class AreaController {
    private final AreaService service;

    public AreaController(AreaService service) {
        this.service = service;
    }

    @GetMapping
    public List<com.booking.domain.Area> Areas(){
        return this.service.allArea();
    }

    @PostMapping
    public ResponseEntity<?> createArea(@Valid @RequestBody Area area, BindingResult result){
        if (result.hasFieldErrors()){
            return validate(result);
        }

        return ResponseEntity.ok().body(this.service.createArea(area));
    }

    @PutMapping
    public ResponseEntity<?> updateArea(@Valid @RequestBody Area area, BindingResult result){
        if (result.hasFieldErrors()){
            return validate(result);
        }

        return ResponseEntity.ok().body(this.service.updateArea(area));
    }


    private ResponseEntity<Map<String, String>> validate(BindingResult result){
        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError: result.getFieldErrors()){
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }
}
