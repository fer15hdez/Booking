package com.booking.service;

import com.booking.domain.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
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

    @Transactional(readOnly = true)
    public Page<CoachResponseDTO> allCoaches(Pageable pageable){
        Page<Coach> coachPage = this.repository.findAll(pageable);
        List<CoachResponseDTO> responseDTOList = coachPage
                .stream()
                .map(mapper::toCoachResponseDTO)
                .toList();

        return new PageImpl<>(responseDTOList,pageable, responseDTOList.size());
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

    public CoachResponseDTO updateCoach(CoachUpdateDTO coachUpdateDTO) {
        Coach coach = this.repository.findById(coachUpdateDTO.getId()).orElseThrow(
                () -> {
                    logger.info("Entity Coach not found  with id: " + coachUpdateDTO.getId());
                    return new EntityNotFoundException("Entity not found");
                }
        );

        // orElseThrow: Se encarga de desempaquetar el optional que devuelve findById y devuelve la entidad

        if (coachUpdateDTO.getName() != null) {
            coach.setName(coachUpdateDTO.getName());
        }
        if (coachUpdateDTO.getDescription() != null) {
            coach.setDescription(coachUpdateDTO.getDescription());
        }
        if (coachUpdateDTO.getAvailability() != null) {
            coach.setAvailability(coachUpdateDTO.getAvailability());
        }

        if (coachUpdateDTO.getAreas() != null) {
            List<Area> areaList = coachUpdateDTO.getAreas().stream()
                    .map(areaId -> areaRepository.findById(areaId)
                            .orElseThrow(() -> {
                                logger.log(Level.INFO, "Area not found with ID: {0}", areaId);
                                return new EntityNotFoundException("Area not found with ID: " + areaId);
                            })
                    ).collect(Collectors.toCollection(ArrayList::new));
            coach.setAreas(areaList); // Si la lista que se le pasa para actualizar es inmutable hibernate lanza un
            // un excepcion de unsuportedOperation

        }

        logger.log(Level.INFO, "Coach to update: {0}", coach);

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
