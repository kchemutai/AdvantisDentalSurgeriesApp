package miu.edu.ADS.controller;

import lombok.RequiredArgsConstructor;
import miu.edu.ADS.model.Surgery;
import miu.edu.ADS.service.SurgeryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/surgeries")
@RequiredArgsConstructor
public class SurgeryController {
    private final SurgeryService surgeryService;

    @GetMapping
    public ResponseEntity<List<Surgery>> getAll() {
        Optional<List<Surgery>> optionalSurgeries = surgeryService.findAllSurgeries();
        return optionalSurgeries.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Surgery> getById(@PathVariable Integer id) {
        Optional<Surgery> optionalSurgery= surgeryService.getSurgeryById(id);
        return optionalSurgery.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Surgery> create(@RequestBody Surgery surgery) {
        Optional<Surgery> surgeryOptional = surgeryService.createNewSurgery(surgery);
        return surgeryOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Surgery> update(@PathVariable Integer id, @RequestBody Surgery surgery) {
        Optional<Surgery> surgeryOptional = surgeryService.updateSurgery(id, surgery);
        return surgeryOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        surgeryService.deleteSurgery(id);
        return ResponseEntity.noContent().build();
    }
}
