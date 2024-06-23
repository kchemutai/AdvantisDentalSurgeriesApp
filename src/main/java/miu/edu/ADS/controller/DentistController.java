package miu.edu.ADS.controller;

import lombok.RequiredArgsConstructor;
import miu.edu.ADS.model.Dentist;
import miu.edu.ADS.service.DentistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/dentists")
@RequiredArgsConstructor
public class DentistController {
    private final DentistService dentistService;

    @GetMapping
    public ResponseEntity<List<Dentist>> getAllDentists() {
        Optional<List<Dentist>> dentistList = dentistService.findAllDentists();
        if (dentistList.isPresent() && !dentistList.get().isEmpty()) {
            return ResponseEntity.ok(dentistList.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> getDentistById(@PathVariable Integer id) {
        Optional<Dentist> dentist = dentistService.findDentistById(id);
        return dentist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Dentist> createDentist(@RequestBody Dentist dentist) {
        Optional<Dentist> optionalDentist = dentistService.saveDentist(dentist);
        return optionalDentist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dentist> updateDentist(@PathVariable Integer id, @RequestBody Dentist dentist) {
        Optional<Dentist> optionalDentist = dentistService.updateExistingDentist(id, dentist);
        return optionalDentist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Dentist> deleteDentist(@PathVariable Integer id) {
        dentistService.deleteDentist(id);
        return ResponseEntity.ok().build();
    }
}
