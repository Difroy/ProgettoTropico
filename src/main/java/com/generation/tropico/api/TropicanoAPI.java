
package com.generation.tropico.api;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.tropico.model.entities.Education;
import com.generation.tropico.model.entities.Gender;
import com.generation.tropico.model.entities.Party;
import com.generation.tropico.model.dto.TropicanoDTO;
import com.generation.tropico.model.dto.TropicanoDTOMapper;
import com.generation.tropico.model.entities.Tropicano;
import com.generation.tropico.model.repository.TropicanoRepository;

@RestController
@CrossOrigin
@RequestMapping("/tropico/api/tropicanos")
public class TropicanoAPI {

    @Autowired
    private TropicanoRepository tropicanoRepository;

    @Autowired
    private TropicanoDTOMapper tropicanoDTOMapper;
    
    
    
    // GET all Tropicanos
    @GetMapping
    public List<TropicanoDTO> getAllTropicanos() {
        List <Tropicano> tropicanos = tropicanoRepository.findAll();
        return tropicanos.stream()
                .map(tropicanoDTOMapper::toDTO)
                .collect(Collectors.toList());
        
    }

    // GET Tropicano by ID
    @GetMapping("/{id}")
    public ResponseEntity<TropicanoDTO> getTropicanoById(@PathVariable int id) {
        Optional<Tropicano> tropicano = tropicanoRepository.findById(id);
        return tropicano.map(t -> ResponseEntity.ok(tropicanoDTOMapper.toDTO(t)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<TropicanoDTO> createTropicano(@RequestBody TropicanoDTO tropicanoDTO) {
       
Tropicano tropicano = tropicanoDTOMapper.toEntity(tropicanoDTO);
        
        // Validate before saving
        if (!tropicano.isValid()) {
            return ResponseEntity.badRequest().build();
        }
        
        Tropicano savedTropicano = tropicanoRepository.save(tropicano);
        return ResponseEntity.ok(tropicanoDTOMapper.toDTO(savedTropicano)); 	
    }
    // POST (create) new Tropicano
    
    @PostMapping("/all")
    public ResponseEntity<List<TropicanoDTO>> createTropicanos(@RequestBody List<TropicanoDTO> tropicanoDTOs) {
        List<Tropicano> tropicanos = tropicanoDTOs.stream()
                .map(tropicanoDTOMapper::toEntity)
                .collect(Collectors.toList());

        // Validate before saving
        List<Tropicano> notValid = tropicanos.stream()
                .filter(t -> !t.isValid())
                .collect(Collectors.toList());

        if (!notValid.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    notValid.stream()
                            .map(tropicanoDTOMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }
        List<Tropicano> savedTropicanos = tropicanoRepository.saveAll(tropicanos);
        return ResponseEntity.ok(savedTropicanos.stream()
                .map(tropicanoDTOMapper::toDTO)
                .collect(Collectors.toList())); 
         
    }
    
    
   
    
    
    // PUT (update) Tropicano
    @PutMapping("/{id}")
    public ResponseEntity<TropicanoDTO> updateTropicano(@PathVariable int id, @RequestBody TropicanoDTO tropicanoDTO) {
        Optional<Tropicano> tropicanoOptional = tropicanoRepository.findById(id);

        if (tropicanoOptional.isPresent()) {
            Tropicano tropicano = tropicanoOptional.get();
            tropicano.setName(tropicanoDTO.getName());
            tropicano.setSurname(tropicanoDTO.getSurname());
            tropicano.setBirthyear(tropicanoDTO.getBirthyear());
            tropicano.setGender(Gender.valueOf(tropicanoDTO.getGender()));
            tropicano.setEducation(Education.valueOf(tropicanoDTO.getEducation()));
            tropicano.setParty(Party.valueOf(tropicanoDTO.getParty()));
            tropicano.setSatisfaction(tropicanoDTO.getSatisfaction());
            tropicano.setImage(tropicanoDTO.getImage());

            Tropicano updatedTropicano = tropicanoRepository.save(tropicano);
            return ResponseEntity.ok(tropicanoDTOMapper.toDTO(updatedTropicano));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
 
    // DELETE Tropicano
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTropicano(@PathVariable int id) {
        Optional<Tropicano> tropicano = tropicanoRepository.findById(id);

        if (tropicano.isPresent()) {
            tropicanoRepository.delete(tropicano.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
