package org.centrale.hceres.controller;

//import org.centrale.hceres.items.RequestDDO;
import org.centrale.hceres.items.ScientificExpertise;
import org.centrale.hceres.service.ScientificExpertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ScientificExpertiseController {

    @Autowired
    private ScientificExpertiseService ScientificExpertiseService;

    @GetMapping("Api/ScientificExpertises")
    public Iterable<ScientificExpertise> getScientificExpertises() {
        return ScientificExpertiseService.getScientificExpertises();
    }


    @GetMapping("Api/ScientificExpertises/{id}")
    public ScientificExpertise getScientificExpertise(@PathVariable("id") final Integer id) {
        Optional<ScientificExpertise> ScientificExpertise = ScientificExpertiseService.getScientificExpertise(id);
        if(ScientificExpertise.isPresent()) {
            return ScientificExpertise.get();
        } else {
            return null;
        }
    }
    
    @PostMapping(value ="Api/AddScientificExpertise")
    public ScientificExpertise createScientificExpertise(@RequestBody Map<String, Object> request) {
        return ScientificExpertiseService.saveScientificExpertise(request);
    }
	
	/*
	@PostMapping("/AddScientificExpertise")
    public RequestDDO createScientificExpertise(@RequestBody RequestDDO request) {
        return request ;
    }
*/
    @DeleteMapping("Api/DeletScientificExpertise/{id}")
    public void deleteScientificExpertise(@PathVariable("id") final Integer id) {
        ScientificExpertiseService.deleteScientificExpertise(id);
    }

}
