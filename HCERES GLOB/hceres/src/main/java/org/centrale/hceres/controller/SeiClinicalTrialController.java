package org.centrale.hceres.controller;


import org.centrale.hceres.items.SeiClinicalTrial;
import org.centrale.hceres.service.SeiClinicalTrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SeiClinicalTrialController {

    @Autowired
    SeiClinicalTrialService SeiClinicalTrialService;


    @GetMapping("Api/SeiClinicalTrials")
    public Iterable<SeiClinicalTrial> getSeiClinicalTrials() {
        return SeiClinicalTrialService.getSeiClinicalTrials();
    }


    @GetMapping("Api/SeiClinicalTrial/{id}")
    public SeiClinicalTrial getSeiClinicalTrial(@PathVariable("id") final Integer id) {
        Optional<SeiClinicalTrial> SeiClinicalTrial = SeiClinicalTrialService.getSeiClinicalTrial(id);
        if(SeiClinicalTrial.isPresent()) {
            return SeiClinicalTrial.get();
        } else {
            return null;
        }
    }

    @PostMapping(value = "Api/AddSeiClinicalTrial")
    public SeiClinicalTrial createSeiClinicalTrial(@RequestBody Map<String, Object> request) {
        return SeiClinicalTrialService.saveSeiClinicalTrial(request);
    }
}

