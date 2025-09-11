package com.booking.service;

import com.booking.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachMapper {

    public Coach toCoach(CoachDTO coachDTO){
        Coach  coach =  new Coach();

        coach.setAreas(coachDTO.areas());
        coach.setName(coachDTO.name());
        coach.setDescription(coachDTO.description());
        coach.setAvailability(coachDTO.availability());

        return coach;
    }

    public Coach toCoachUpdate(CoachUpdateDTO coachUpdateDTO, List<Area> areaList){
        Coach coach = new Coach();

        coach.setId(coachUpdateDTO.getId());
        coach.setName(coachUpdateDTO.getName());
        coach.setDescription(coachUpdateDTO.getDescription());
        coach.setAvailability(coachUpdateDTO.getAvailability());
        coach.setAreas(areaList);

        return coach;
    }

    public CoachResponseDTO toCoachResponseDTO(Coach coach){

        return new CoachResponseDTO(
                coach.getName(),
                coach.getDescription(),
                coach.getAvailability(),
                coach.getAreas()
        );
    }
}
