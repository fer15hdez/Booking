package com.booking.service;

import com.booking.domain.*;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CoachService {

    Logger logger = Logger.getLogger(getClass().getName());
    private final CoachRepository repository;
    private final CoachMapper mapper;
    private final AreaRepository areaRepository;

    public CoachService(CoachRepository repository, CoachMapper mapper, AreaRepository areaRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.areaRepository = areaRepository;
    }

    public List<CoachResponseDTO> allCoaches(){
        return this.repository.findAll()
                .stream()
                .map(mapper::toCoachResponseDTO)
                .collect(Collectors.toList());
    }

    public CoachResponseDTO createCoach(CoachDTO coachDTO){

        if (coachDTO.areas() != null && !coachDTO.areas().isEmpty()){
            List<Area> areaList = new ArrayList<>(coachDTO.areas());

            for (Area area: areaList){
                this.areaRepository.findById(area.getId()).orElseThrow(
                        () -> {
                            logger.info("Entity Area not found  with id: " + area.getId());
                            return new EntityNotFoundException("Entity Area no found");
                        }
                );

            }
        }
        Coach coach = this.mapper.toCoach(coachDTO);

        return this.mapper.toCoachResponseDTO(this.repository.save(coach));

    }

    public CoachResponseDTO updateCoach(CoachUpdateDTO coachUpdateDTO){
        this.repository.findById(coachUpdateDTO.getId()).orElseThrow(
                () -> {
                    logger.info("Entity Coach not found  with id: " + coachUpdateDTO.getId());
                    return new EntityNotFoundException("Entity not found");
                }
        );
        Optional<Coach> coachOptional = this.repository.findById(coachUpdateDTO.getId());

        if (coachUpdateDTO.getName() == null){
            coachOptional.ifPresent(value -> coachUpdateDTO.setName(value.getName()));
        }
        if (coachUpdateDTO.getDescription() == null){
            coachOptional.ifPresent(value -> coachUpdateDTO.setDescription(value.getDescription()));
        }
        if (coachUpdateDTO.getAvailability() == null){
            coachOptional.ifPresent(value -> coachUpdateDTO.setAvailability(value.getAvailability()));
        }
        if (coachUpdateDTO.getAreas() == null){
            coachOptional.ifPresent(value -> coachUpdateDTO.setAreas(value.getAreas()));
        }

        Coach coach = mapper.toCoachUpdate(coachUpdateDTO);

        return this.mapper.toCoachResponseDTO(this.repository.save(coach));
    }

    public void deleteCoach(Integer id){
        this.repository.findById(id).orElseThrow(
                () -> {
                    logger.info("No found entity Coach with id: " + id);
                    return new EntityNotFoundException("Entity not found");
                }
        );
        this.repository.deleteById(id);
    }

}
