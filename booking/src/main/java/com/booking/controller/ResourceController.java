package com.booking.controller;

import com.booking.domain.Resource;
import com.booking.service.ResourceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
public class ResourceController {
    private final ResourceService service;

    public ResourceController(ResourceService serviceResource, ResourceService service) {
        this.service = service;
    }

    @PostMapping
    public Resource createResouce(Resource resource){
        return this.service.createResource(resource);
    }
}
