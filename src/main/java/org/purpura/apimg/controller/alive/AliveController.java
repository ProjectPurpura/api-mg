package org.purpura.apimg.controller.alive;

import org.purpura.apimg.dto.alive.AliveResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alive")
public class AliveController implements AliveContract {

    public ResponseEntity<AliveResponseDTO> get() {
        return ResponseEntity.ok(new AliveResponseDTO("banana", "API is alive!"));
    }
}
