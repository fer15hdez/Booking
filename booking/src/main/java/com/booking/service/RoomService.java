package com.booking.service;

import com.booking.domain.*;
import com.booking.exceptions.DeleteEntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository repository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomMapper mapper;
    private final RoomMapperStruct roomMapperStruct;

    public RoomService(RoomRepository repository, RoomTypeRepository roomTypeRepository, RoomMapper mapper, RoomMapperStruct roomMapperStruct) {
        this.repository = repository;
        this.roomTypeRepository = roomTypeRepository;
        this.mapper = mapper;
        this.roomMapperStruct = roomMapperStruct;
    }

    public RoomResponseDTO createRoom(RoomDTO roomDTO){
        if (roomDTO.type() != null){
            Optional<RoomType>  roomTypeOptional = roomTypeRepository.findById(roomDTO.type());

            if (roomTypeOptional.isEmpty()){
                throw new EntityNotFoundException("No found entity roomType with id: " + roomDTO.type());
            }
        }

        Room room = this.mapper.toRoom(roomDTO);

        return this.mapper.toRoomResponseDTO(this.repository.save(room));
    }

    public RoomResponseDTO updateRoom(RoomDTO roomDTO, Integer id){
        Room roomDB = this.repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found")
        );

        roomMapperStruct.updateEntityFromDto(roomDTO, roomDB);

        return  this.mapper.toRoomResponseDTO(this.repository.save(roomDB));
    }

    public List<RoomResponseDTO> listRoom(){
        return this.repository.findAll()
                .stream()
                .map(mapper::toRoomResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteRoom(Integer id){
        this.repository.findById(id)
                .orElseThrow(
                        () -> new DeleteEntityNotFoundException("Entity not found with id: " + id)
                );
        this.repository.deleteById(id);
    }
}
