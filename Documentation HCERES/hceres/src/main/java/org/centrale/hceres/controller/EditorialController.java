package org.centrale.hceres.controller;

import org.centrale.hceres.items.EditorialActivity;

import org.centrale.hceres.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EditorialController {

    @Autowired
    EditorialService editorialService;


    @GetMapping("Api/Editorials")
    public Iterable<EditorialActivity> getEditorials() {
        return editorialService.getEditorials();
    }


    @GetMapping("Api/Editorial/{id}")
    public EditorialActivity getEditorial(@PathVariable("id") final Integer id) {
        Optional<EditorialActivity> editorial = editorialService.getEditorial(id);
        if(editorial.isPresent()) {
            return editorial.get();
        } else {
            return null;
        }
    }

    @PostMapping(value = "Api/AddEditorial")
    public EditorialActivity createEditorial(@RequestBody Map<String, Object> request) {
        return editorialService.saveEditorial(request);
    }
}
