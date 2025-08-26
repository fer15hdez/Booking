package com.booking.controller;

import com.booking.domain.Room;
import com.booking.domain.RoomDTO;
import com.booking.domain.RoomResponseDTO;
import com.booking.service.RoomService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping
    public RoomResponseDTO createRoom(@RequestBody RoomDTO roomDTO){
        return this.service.createRoom(roomDTO);
    }

    @PutMapping
    public RoomResponseDTO updateRoom(@RequestBody Room room){
        return this.service.updateRoom(room);
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
