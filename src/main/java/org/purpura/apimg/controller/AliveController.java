package org.purpura.apimg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alive")
public class AliveController {
    public record AliveResponseDTO(String status, String message) {}

    @GetMapping
    public ResponseEntity<AliveResponseDTO> get() {
        return ResponseEntity.ok(new AliveResponseDTO("banana", "API is alive!"));
    }
}
