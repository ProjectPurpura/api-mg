package org.purpura.apimg.controller.alive;

import org.purpura.apimg.dto.alive.AliveResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface AliveContract {
    @GetMapping
    ResponseEntity<AliveResponseDTO> get();
}
