package org.centrale.hceres.controller;


import org.centrale.hceres.items.IncomingMobility;
import org.centrale.hceres.service.IncomingMobilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class IncomingMobilityController {

    @Autowired
    IncomingMobilityService IncomingMobilityService;

    @GetMapping("Api/IncomingMobilitys")
    public Iterable<IncomingMobility> getIncomingMobilitys() {
        return IncomingMobilityService.getIncomingMobilitys();
    }


    @GetMapping("Api/IncomingMobility/{id}")
    public IncomingMobility getIncomingMobility(@PathVariable("id") final Integer id) {
        Optional<IncomingMobility> IncomingMobility = IncomingMobilityService.getIncomingMobility(id);
        if(IncomingMobility.isPresent()) {
            return IncomingMobility.get();
        } else {
            return null;
        }
    }

    @PostMapping(value = "Api/AddIncomingMobility")
    public IncomingMobility createIncomingMobility(@RequestBody Map<String, Object> request) {
        return IncomingMobilityService.saveIncomingMobility(request);
    }
}
