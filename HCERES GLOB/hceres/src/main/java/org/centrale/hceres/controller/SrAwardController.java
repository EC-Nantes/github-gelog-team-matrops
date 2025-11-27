package org.centrale.hceres.controller;


import org.centrale.hceres.items.SrAward;
import org.centrale.hceres.service.SrAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SrAwardController {

    @Autowired
    SrAwardService SrAwardService;


    @GetMapping("Api/SrAwards")
    public Iterable<SrAward> getSrAwards() {
        return SrAwardService.getSrAwards();
    }


    @GetMapping("Api/SrAward/{id}")
    public SrAward getSrAward(@PathVariable("id") final Integer id) {
        Optional<SrAward> SrAward = SrAwardService.getSrAward(id);
        if(SrAward.isPresent()) {
            return SrAward.get();
        } else {
            return null;
        }
    }

    @PostMapping(value = "Api/AddSrAward")
    public SrAward createSrAward(@RequestBody Map<String, Object> request) {
        return SrAwardService.saveSrAward(request);
    }
}
