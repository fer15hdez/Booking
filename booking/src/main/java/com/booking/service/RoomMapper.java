package com.booking.service;

import com.booking.domain.Room;
import com.booking.domain.RoomDTO;
import com.booking.domain.RoomResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class RoomMapper {

    public Room toRoom(RoomDTO roomDTO){
        Room room = new Room();

        room.setLength(roomDTO.length());
        room.setType(roomDTO.type());
        room.setWidth(roomDTO.width());
        room.setName(roomDTO.name());
        room.setDescription(roomDTO.description());
        room.setAvailability(roomDTO.availability());

        return room;

    }

    public RoomResponseDTO toRoomResponseDTO(Room room){
        return new RoomResponseDTO(
                room.getName(),
                room.getDescription(),
                room.getWidth(),
                room.getLength(),
                room.getType()
        );
    }
}
