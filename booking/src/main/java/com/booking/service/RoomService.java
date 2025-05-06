package com.booking.service;

import com.booking.domain.Room;
import com.booking.domain.RoomDTO;
import com.booking.domain.RoomRepository;
import com.booking.domain.RoomResponseDTO;
import com.booking.exceptions.DeleteEntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository repository;
    private final RoomMapper mapper;
    Logger logger = Logger.getLogger(getClass().getName());


    public RoomService(RoomRepository repository, RoomMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RoomResponseDTO createRoom(RoomDTO roomDTO){
        Room room = this.mapper.toRoom(roomDTO);

        this.repository.findById(room.getId()).orElseThrow(
                () -> {
                    logger.info("No found Room entity with id: " + room.getId());
                    return new EntityNotFoundException("No found Room entity with given id");
                }
        );

        return this.mapper.toRoomResponseDTO(this.repository.save(room));
    }

    public RoomResponseDTO updateRoom(Room room){
        this.repository.findById(room.getId()).orElseThrow(
                () -> {
                    logger.info("No found Room entity with id: " + room.getId());
                    return new EntityNotFoundException("No found Room entity with given id");
                }
        );
        return  this.mapper.toRoomResponseDTO(this.repository.save(room));
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
                        () -> new EntityNotFoundException("Entity not found with id: " + id)
                );
        this.repository.deleteById(id);
    }
}
