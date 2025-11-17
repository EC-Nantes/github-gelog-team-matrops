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
import org.centrale.hceres.items.ScientificExpertise;

import org.centrale.hceres.items.ScientificExpertiseType;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TypeActivity;
import org.centrale.hceres.repository.ActivityRepository;
import org.centrale.hceres.repository.ScientificExpertiseTypeRepository;
import org.centrale.hceres.repository.ScientificExpertiseRepository;
import org.centrale.hceres.repository.ResearchRepository;
import org.centrale.hceres.repository.TypeActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.Data;

// permet de traiter la requete HTTP puis l'associer a la fonction de repository qui va donner une reponse
@Data
@Service
public class ScientificExpertiseService {
	
	/**
	 * Instanciation
	 */
	@Autowired
	private ResearchRepository researchRepo;
	@Autowired
	private ScientificExpertiseRepository ScientificExpertiseRepo;
	@Autowired
	private ScientificExpertiseTypeRepository ScientificExpertiseTypeRepo;
	@Autowired
	private ActivityRepository activityRepo;
	@Autowired
	private TypeActivityRepository typeActivityLevelRepo;
	
	/**
	 * permet de retourner la liste
	 */
	public Iterable<ScientificExpertise> getScientificExpertises(){
		return ScientificExpertiseRepo.findAll();
	}
	
	/**
	 * retourner l'elmt selon son id
	 * @param id : id de l'elmt
	 * @return : elmt a retourner
	 */
	public Optional<ScientificExpertise> getScientificExpertise(final Integer id) { 
		return ScientificExpertiseRepo.findById(id); 
	}
	
	/**
	 * supprimer l'elmt selon son id
	 * @param id : id de l'elmt
	 */
	public void deleteScientificExpertise(final Integer id) {
		ScientificExpertiseRepo.deleteById(id);
	}
	
	/**
	 * permet d'ajouter un elmt
	 * @return : l'elemt ajouter a la base de donnees
	 */
	@Transactional
	public ScientificExpertise saveScientificExpertise(@RequestBody Map<String, Object> request) {
		
		ScientificExpertise ScientificExpertiseTosave = new ScientificExpertise();
		
		// setStartDate :
		String dateString = (String)request.get("ScientificExpertiseStartDate");
		ScientificExpertiseTosave.setStartDate(getDateFromString(dateString, "yyyy-MM-dd"));

		// setEndDate :
		String dateString2 = (String)request.get("ScientificExpertiseEndDate");
		ScientificExpertiseTosave.setEndDate(getDateFromString(dateString2, "yyyy-MM-dd"));

		// setDescription :
		ScientificExpertiseTosave.setDescription((String)request.get("ScientificExpertiseDescription"));
		
		
		// ScientificExpertiseType : 
		ScientificExpertiseType ScientificExpertiseType = new ScientificExpertiseType();
		ScientificExpertiseType.setNameChoice((String)request.get("ScientificExpertiseTypeName"));
		ScientificExpertiseType saveScientificExpertiseType = ScientificExpertiseTypeRepo.save(ScientificExpertiseType);
		ScientificExpertiseTosave.setScientificExpertiseTypeId(saveScientificExpertiseType);
		
		// Activity : 
		Activity activity = new Activity();
		TypeActivity typeActivity = typeActivityLevelRepo.getById(16);
		activity.setIdTypeActivity(typeActivity);
		//Activity savedActivity = activityRepo.save(activity);
		//ScientificExpertiseTosave.setActivity(savedActivity);
		
		// ajouter cette activité à la liste de ce chercheur :

		String researcherIdStr = (String)request.get("researcherId");
        int researcherId = -1;
        researcherId = Integer.parseInt(researcherIdStr);
        Optional<Researcher> researcherOp = researchRepo.findById(researcherId);
        Researcher researcher = researcherOp.get();

		Collection<Activity> activityCollection = researcher.getActivityCollection();
        activityCollection.add(activity);
        researcher.setActivityCollection(activityCollection);


		 // Add this activity to the reasearcher :
		 Collection<Researcher> activityResearch = activity.getResearcherCollection();
		 if (activityResearch == null) {
			 activityResearch = new ArrayList<Researcher>();
		 }
		 activityResearch.add(researcher);
		 activity.setResearcherCollection(activityResearch);
 
		 Activity savedActivity = activityRepo.save(activity);
		 ScientificExpertiseTosave.setActivity(savedActivity);
		  // Created platform id :
		  Integer idSE = activity.getIdActivity();
		  ScientificExpertiseTosave.setIdActivity(idSE);
		// Enregistrer ScientificExpertise dans la base de données :
		ScientificExpertise saveScientificExpertise = ScientificExpertiseRepo.save(ScientificExpertiseTosave);
		
		return saveScientificExpertise;
	}
	
	// Convertir une date string en Date
	public Date getDateFromString(String aDate, String format) {
        Date returnedValue = null;
        try {
            // try to convert
            SimpleDateFormat aFormater = new SimpleDateFormat(format);
            returnedValue = aFormater.parse(aDate);
        } catch (ParseException ex) {
        }
        
        if (returnedValue != null) {
            Calendar aCalendar = Calendar.getInstance();
            aCalendar.setTime(returnedValue);
        }
        return returnedValue;
    }
}



