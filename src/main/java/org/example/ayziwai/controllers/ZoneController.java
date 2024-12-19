package org.example.ayziwai.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ayziwai.dto.request.ZoneRequest;
import org.example.ayziwai.dto.response.ZoneResponse;
import org.example.ayziwai.services.interfaces.ZoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping("/api/user/zones/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getZone(@PathVariable String id) {
        try {
            ZoneResponse zone = zoneService.getZoneById(id);
            return ResponseEntity.ok(zone);
        } catch (AccessDeniedException e) {
            return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Access denied: Insufficient privileges");
        }
    }

    @PostMapping("/api/admin/zones")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createZone(@Valid @RequestBody ZoneRequest zoneRequest) {
        try {
            ZoneResponse zone = zoneService.createZone(zoneRequest);
            return new ResponseEntity<>(zone, HttpStatus.CREATED);
        } catch (AccessDeniedException e) {
            return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Access denied: Admin privileges required");
        }
    }
}
