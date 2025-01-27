package com.booking.controller;

import com.booking.domain.RoomType;
import com.booking.domain.RoomTypeDTO;
import com.booking.service.RoomTypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("roomtypes")
public class RoomTypeController {

    private final RoomTypeService service;

    public RoomTypeController(RoomTypeService serve, RoomTypeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createRoomType(@Valid @RequestBody RoomTypeDTO roomTypeDTO, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            return validate(bindingResult);
        }
        return ResponseEntity.ok().body(this.service.createRoomType(roomTypeDTO));
    }

    private ResponseEntity<?> validate(BindingResult result){
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

}
