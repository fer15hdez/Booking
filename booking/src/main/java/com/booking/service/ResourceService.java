package com.booking.service;

import com.booking.domain.Resource;
import com.booking.domain.ResourceRepositoy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    private final ResourceRepositoy repositoy;

    public ResourceService(ResourceRepositoy repositoy) {
        this.repositoy = repositoy;
    }

    public ResponseEntity<?> createResource(Resource resource){
        return ResponseEntity.ok().body(this.repositoy.save(resource));
    }
}
