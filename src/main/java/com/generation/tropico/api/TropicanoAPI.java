
package com.generation.tropico.api;

import com.generation.tropico.model.entities.Tropicano;
import com.generation.tropico.model.repository.TropicanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tropico/api/tropicanos")
public class TropicanoAPI {

    @Autowired
    private TropicanoRepository tropicanoRepository;

    // GET all Tropicanos
    @GetMapping
    public List<Tropicano> getAllTropicanos() {
        return tropicanoRepository.findAll();
    }

    // GET Tropicano by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tropicano> getTropicanoById(@PathVariable int id) {
        Optional<Tropicano> tropicano = tropicanoRepository.findById(id);
        return tropicano.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // POST (create) new Tropicano
    @PostMapping
    public ResponseEntity<List<Tropicano>> createTropicanos(@RequestBody List <Tropicano> tropicanos) {
       
    	//ALL OR NONE
    	
    	for(Tropicano tropicano:tropicanos)
			if (!tropicano.isValid())
				return ResponseEntity.badRequest().build();

		return ResponseEntity.ok(tropicanoRepository.saveAll(tropicanos));
    	
    	
    	
    }
    
    @PostMapping
    public ResponseEntity<Tropicano> createTropicano(@RequestBody Tropicano tropicano) {
       
    	if(!tropicano.isValid())
    		return ResponseEntity.badRequest().build();
    	
    	return ResponseEntity.ok(tropicanoRepository.save(tropicano));
    }
    
    
    
    
    
    
    
    
    
    
    
    

    // PUT (update) Tropicano
    @PutMapping("/{id}")
    public ResponseEntity<Tropicano> updateTropicano(@PathVariable int id, @RequestBody Tropicano tropicanoDetails) {
        Optional<Tropicano> optionalTropicano = tropicanoRepository.findById(id);
        if (optionalTropicano.isPresent()) {
            Tropicano tropicanoToUpdate = optionalTropicano.get();
            tropicanoToUpdate.setName(tropicanoDetails.getName());
            tropicanoToUpdate.setSurname(tropicanoDetails.getSurname());
            tropicanoToUpdate.setBirthyear(tropicanoDetails.getBirthyear());
            tropicanoToUpdate.setEducation(tropicanoDetails.getEducation());
            tropicanoToUpdate.setGender(tropicanoDetails.getGender());
            tropicanoToUpdate.setParty(tropicanoDetails.getParty());
            Tropicano updatedTropicano = tropicanoRepository.save(tropicanoToUpdate);
            return ResponseEntity.ok(updatedTropicano);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // DELETE Tropicano
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTropicano(@PathVariable int id) {
        Optional<Tropicano> tropicano = tropicanoRepository.findById(id);
        if (tropicano.isPresent()) {
            tropicanoRepository.delete(tropicano.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
