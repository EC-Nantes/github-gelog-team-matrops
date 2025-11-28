package org.centrale.hceres.controller;


import org.centrale.hceres.items.NationalInternationalCollaboration;
import org.centrale.hceres.service.NationalInternationalCollaborationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class NationalInternationalCollaborationController {

	/**
	 * instanciation
	 */
	@Autowired
	private NationalInternationalCollaborationService nationalInternationalCollaborationService;
	
	/**
	 * ajouter un elmt a la base de donnees
	 * @param education : l'elmt a ajouter
	 * @return l'elmt ajoute
	 */
	@PostMapping(value ="/AddNationalInternationalCollaboration")
	public NationalInternationalCollaboration createNationalInternationalCollaboration(@RequestBody Map<String, Object> request) {
		return nationalInternationalCollaborationService.saveNationalInternationalCollaboration(request);
	}
}
