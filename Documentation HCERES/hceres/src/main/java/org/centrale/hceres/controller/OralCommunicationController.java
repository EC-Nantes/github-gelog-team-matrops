package org.centrale.hceres.controller;

import javax.servlet.http.HttpServletRequest;

import org.centrale.hceres.items.OralCommunication;
import org.centrale.hceres.service.OralCommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OralCommunicationController {
		
	/**
	 * instanciation
	 */
	@Autowired
	private OralCommunicationService communicationService;
	
	/**
	 * ajouter un elmt a la base de donnees
	 * @param education : l'elmt a ajouter
	 * @return l'elmt ajoute
	 */
	@PostMapping(value ="/AddOralCommunication")
	public OralCommunication createOralCommunication(@RequestBody Map<String, Object> request) {
		return communicationService.saveOralCommunication(request);
	}
}
