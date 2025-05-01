package com.booking.controller;

import com.booking.domain.FunctionType;
import com.booking.domain.FunctionTypeDTO;
import com.booking.domain.FunctionTypeResponseDTO;
import com.booking.service.FunctionTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/functions")
public class FunctionTypeController {

    private final FunctionTypeService service;

    public FunctionTypeController(FunctionTypeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createFunctionType(@Valid @RequestBody FunctionTypeDTO functionType, BindingResult result){

        Map<String, String> mapError = new HashMap<>();
        if (result.hasFieldErrors()){
            List<FieldError> errors = result.getFieldErrors();
            errors.forEach(fieldError -> mapError.put(fieldError.getField(), fieldError.getDefaultMessage()));

            return ResponseEntity.badRequest().body(mapError);
        }
        return ResponseEntity.ok().body(this.service.createFunctionType(functionType));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFunctionType(@PathVariable("id") Integer id){
        this.service.deleteFunctionType(id);
    }
}
