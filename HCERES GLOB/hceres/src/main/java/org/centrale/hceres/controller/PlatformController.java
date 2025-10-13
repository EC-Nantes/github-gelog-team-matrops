package org.centrale.hceres.controller;


import org.centrale.hceres.items.Platform;
import org.centrale.hceres.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PlatformController {

    @Autowired
    PlatformService platformService;


    @GetMapping("Api/Platforms")
    public Iterable<Platform> getPlatforms() {
        return platformService.getPlatforms();
    }


    @GetMapping("Api/Platform/{id}")
    public Platform getPlatform(@PathVariable("id") final Integer id) {
        Optional<Platform> platform = platformService.getPlatform(id);
        if(platform.isPresent()) {
            return platform.get();
        } else {
            return null;
        }
    }

    @PostMapping(value = "Api/AddPlatform")
    public Platform createPlatform(@RequestBody Map<String, Object> request) {
        return platformService.savePlatform(request);
    }
}
