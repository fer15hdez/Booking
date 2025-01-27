package com.booking.service;

import com.booking.domain.RoomType;
import com.booking.domain.RoomTypeDTO;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeMapper {

    public RoomType toRoomType(RoomTypeDTO dto){
        if (dto == null){
            throw new NullPointerException("The RoomType DTO should no be null");
        }
        RoomType roomType = new RoomType();
        roomType.setName(dto.name());
        roomType.setDescription(dto.description());
        roomType.setRooms(dto.rooms());


        return roomType;
    }
}
