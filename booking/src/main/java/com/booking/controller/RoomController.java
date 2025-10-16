package com.booking.controller;

import com.booking.domain.Room;
import com.booking.domain.RoomDTO;
import com.booking.domain.RoomResponseDTO;
import com.booking.service.RoomService;
import jakarta.validation.Valid;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<Page<RoomResponseDTO>> listRoom(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        if (size > 100){
            size = 50;
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<RoomResponseDTO> listRoom= this.service.listRoom(pageRequest);
        return ResponseEntity.ok().body(listRoom);
    }

    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
    public void deleteRoom(@PathVariable("id") Integer id){
        this.service.deleteRoom(id);
    }
}