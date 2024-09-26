package com.generation.tropico.api;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.tropico.model.dto.IsleDTO;
import com.generation.tropico.model.dto.IsleMapper;
import com.generation.tropico.model.entities.Isle;
import com.generation.tropico.model.repository.IsleRepository;

@RestController
@RequestMapping("/api/isles")
public class IsleAPI {

    @Autowired
    private IsleRepository isleRepo;

    @Autowired
    private IsleMapper isleMapper;

    // GET all isles
    @GetMapping
    public List<IsleDTO> getAllIsles() {
        List<Isle> isles = isleRepo.findAll();
        return isles.stream()
                .map(isleMapper::toDTO)
                .toList();
    }

    // GET isle by ID
    @GetMapping("/{id}")
    public ResponseEntity<IsleDTO> getIsleById(@PathVariable Integer id) {
        Optional<Isle> isleOpt = isleRepo.findById(id);
        if (isleOpt.isPresent()) {
            return ResponseEntity.ok(isleMapper.toDTO(isleOpt.get()));
        }
        return ResponseEntity.notFound().build();
    }

    // POST a new isle
    @PostMapping
    public ResponseEntity<IsleDTO> createIsle(@RequestBody IsleDTO isleDTO) {
        Isle isle = isleMapper.fromDTO(isleDTO);
        Isle savedIsle = isleRepo.save(isle);
        return ResponseEntity.ok(isleMapper.toDTO(savedIsle));
    }

    // PUT (update) an isle by ID
    @PutMapping("/{id}")
    public ResponseEntity<IsleDTO> updateIsle(@PathVariable Integer id, @RequestBody IsleDTO isleDTO) {
        Optional<Isle> isleOpt = isleRepo.findById(id);
        if (isleOpt.isPresent()) {
            Isle isleToUpdate = isleMapper.fromDTO(isleDTO);
            isleToUpdate.setId(id); // Imposta l'ID dell'isola esistente
            Isle updatedIsle = isleRepo.save(isleToUpdate);
            return ResponseEntity.ok(isleMapper.toDTO(updatedIsle));
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE an isle by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIsle(@PathVariable Integer id) {
        if (isleRepo.existsById(id)) {
            isleRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
