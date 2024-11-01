package com.booking.service;

import com.booking.domain.Room;
import com.booking.domain.RoomDTO;
import com.booking.domain.RoomResponseDTO;
import com.booking.domain.RoomType;
import org.springframework.stereotype.Service;

@Service
public class RoomMapper {

    public Room toRoom(RoomDTO roomDTO){
        if (roomDTO == null){
            throw new NullPointerException("The Room DTO should no be null");
        }

        RoomType roomType = new RoomType();
        roomType.setId(roomDTO.type());

        Room room = new Room();

        room.setLength(roomDTO.length());
        room.setType(roomType);
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
