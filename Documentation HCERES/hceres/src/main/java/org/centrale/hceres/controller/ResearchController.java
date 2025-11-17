package org.centrale.hceres.controller;

import java.util.Map;

import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.repository.ResearchRepository;
import org.centrale.hceres.service.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.Optional;


// Controller permet de receptionner la requete http depuis le client, envoyer cette requete a service pour la traiter, puis retouner la reponse
// la reponse sera sous format JSON (il s'agit d'une REST API)
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ResearchController {
	
	/**
	 * instanciation de ResearchService
	 */
	@Autowired
	private ResearchService rs;
	/**
	 * Instanciation de ResearchRepository
	 */
	@Autowired
	private ResearchRepository researchRepo;
	
	/**
	 * pour une requete GET dans localhost/researchs => fournir la liste des chercheurs
	 * le resultat est traduit automatiquement en JSON
	 * @return : liste des chercheurs
	 */
	@GetMapping("/Researchers")
	public Iterable<Researcher> getResearchers() {
		return rs.getResearchers();
	}
	
	/**
	 * Delete - Delete an element
	 * @param id - The id of the element
	 */
	@DeleteMapping("/deleteResearcher/{id}")
	
	public void deleteEducation(@RequestBody @PathVariable("id") final Integer id) {
		rs.deleteResearcher(id);
	}
	
	/**
	 * ajouter un elmt a la base de donnees
	 * @param education : l'elmt a ajouter
	 * @return l'elmt ajoute
	 */
	@PostMapping(value ="/AddResearcher")
	public Researcher createResearcher(@RequestBody Map<String, Object> request) {
		return rs.saveResearcher(request);
	}
	
	
	/**
	 * Update - Update an existing element
	 * @param id - The id of the element
	 * @param researcher - The element
	 * @return
	 */
	@PutMapping("/updateResearcher/{id}")
	public Researcher updateResearcher(@PathVariable("id") final Integer id, @RequestBody Map<String, Object> request) {
		Optional<Researcher> e = researchRepo.findById(id);
		if(e.isPresent()) {
			Researcher currentResearcher = e.get();
			
			String researcherSurname = (String)request.get("researcherSurname");
			if(researcherSurname != null) {
				currentResearcher.setResearcherSurname(researcherSurname);
			}
			
			String researcherName = (String)request.get("researcherName");
			if(researcherName != null) {
				currentResearcher.setResearcherName(researcherName);
			}
			
			String researcherEmail = (String)request.get("researcherEmail");
			if(researcherEmail != null) {
				currentResearcher.setResearcherEmail(researcherEmail);
			}
			
			researchRepo.save(currentResearcher);
			return currentResearcher;
			
		} else {
			return null;
		}
	}
}
