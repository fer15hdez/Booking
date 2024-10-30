package com.booking.controller;

import com.booking.domain.RoomType;
import com.booking.service.RoomTypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roomtypes")
public class RoomTypeController {

    private final RoomTypeService service;

    public RoomTypeController(RoomTypeService serve, RoomTypeService service) {
        this.service = service;
    }

    @PostMapping
    public RoomType createRoomType(@RequestBody RoomType roomType){
        return this.service.createRoomType(roomType);
    }

}
