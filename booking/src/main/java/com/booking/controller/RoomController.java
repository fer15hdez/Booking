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
import org.springframework.validation.Errors;
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
    public ResponseEntity<?> createRoom(@Valid @RequestBody RoomDTO roomDTO, BindingResult bindingResult){

        Map<String, String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError error: bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        return ResponseEntity.ok().body(this.service.createRoom(roomDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Integer id, @Valid @RequestBody RoomDTO roomDTO, BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        if (bindingResult.hasErrors()){
            for (FieldError error: bindingResult.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        return ResponseEntity.ok().body(this.service.updateRoom(roomDTO, id));
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
}
