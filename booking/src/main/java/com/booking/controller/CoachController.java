package com.booking.controller;

import com.booking.domain.CoachDTO;
import com.booking.domain.CoachResponseDTO;
import com.booking.domain.CoachUpdateDTO;
import com.booking.service.CoachService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coaches")
public class CoachController {
    private final CoachService service;

    public CoachController(CoachService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<Page<CoachResponseDTO>> listCoaches(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ){
        if (size > 100){
            size = 50;
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok().body(this.service.allCoaches(pageRequest));
    }

    @PostMapping
    public ResponseEntity<?> createCoach(@Valid @RequestBody CoachDTO coachDTO, BindingResult result){
        if (result.hasFieldErrors()){
           return validation(result);
        }

        return ResponseEntity.ok().body(this.service.createCoach(coachDTO));
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateCoach(@Valid @RequestBody CoachUpdateDTO coachUpdateDTO, BindingResult result){
        if (result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.ok().body(this.service.updateCoach(coachUpdateDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCoach(@PathVariable("id") Integer id){
        this.service.deleteCoach(id);
    }

    private ResponseEntity<Map<String, String>> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(
                fieldError -> errors.put(fieldError.getField(), ": " + fieldError.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }

}
