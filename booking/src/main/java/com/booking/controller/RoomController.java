package com.booking.controller;

import com.booking.domain.Room;
import com.booking.domain.RoomDTO;
import com.booking.domain.RoomResponseDTO;
import com.booking.service.RoomService;
import jakarta.validation.Valid;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createRoom(@Valid @RequestBody RoomDTO roomDTO, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(validation(result));
        }

        return ResponseEntity.ok().body(this.service.createRoom(roomDTO));
    }

    @PutMapping
    public ResponseEntity<?> updateRoom(@Valid @RequestBody Room room, BindingResult result){
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(validation(result));
        }

        return ResponseEntity.badRequest().body(this.service.updateRoom(room));
    }

    @GetMapping
    public List<RoomResponseDTO> listRoom(){
        return this.service.listRoom();
    }

    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
    public void deleteRoom(@PathVariable("id") Integer id){
        this.service.deleteRoom(id);
    }

    public Map<String, String> validation(BindingResult result){
        Map<String, String> mapError = new HashMap<>();
        List<FieldError> errors = result.getFieldErrors();

        errors.forEach(fieldError -> {
            mapError.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return  mapError;
    }
}
