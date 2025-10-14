package org.centrale.hceres.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.Education;
import org.centrale.hceres.items.EducationInvolvment;
import org.centrale.hceres.items.EducationLevel;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TypeActivity;
import org.centrale.hceres.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.Data;

// permet de traiter la requete HTTP puis l'associer a la fonction de repository qui va donner une reponse
@Data
@Service
public class ResearchService {
	
	/**
	 * Instanciation de ResearchRepository
	 */
	@Autowired
	private ResearchRepository researchRepo;
	
	/**
	 * permet d'avoir la liste des chercheurs
	 */
	public Iterable<Researcher> getResearchers(){
		return researchRepo.findAll();
	}
	
	/**
	 * supprimer l'elmt selon son id
	 * @param id : id de l'elmt
	 */
	public void deleteResearcher(final Integer id) {
		researchRepo.deleteById(id);
	}
	
	/**
	 * permet d'ajouter un elmt
	 * @return : l'elemt ajouter a la base de donnees
	 */
	@Transactional
	public Researcher saveResearcher(@RequestBody Map<String, Object> request) {
		
		Researcher researcherTosave = new Researcher();
		
		// researcherSurname :
		researcherTosave.setResearcherSurname((String)request.get("researcherSurname"));
		
		// researcherName :
		researcherTosave.setResearcherName((String)request.get("researcherName"));
		
		// researcherEmail :
		researcherTosave.setResearcherEmail((String)request.get("researcherEmail"));
				
		// Enregistrer Education dans la base de données :
		Researcher saveResearcher = researchRepo.save(researcherTosave);
		
		return saveResearcher;
	}
	
}
