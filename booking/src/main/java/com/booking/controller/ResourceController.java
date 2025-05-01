package com.booking.controller;

import com.booking.domain.Resource;
import com.booking.service.ResourceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resources")
public class ResourceController {
    private final ResourceService service;

    public ResourceController(ResourceService serviceResource, ResourceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createResource(@Valid @RequestBody Resource resource, BindingResult result){
        List<FieldError> errors = result.getFieldErrors();
        Map<String, String> mapError = new HashMap<>();
        if (result.hasErrors()){
            errors.forEach(fieldError -> mapError.put(fieldError.getField(), fieldError.getDefaultMessage()));

            return ResponseEntity.badRequest().body(mapError);
        }
        return this.service.createResource(resource);
    }
}
