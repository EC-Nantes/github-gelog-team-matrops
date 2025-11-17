package org.centrale.hceres.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;


import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.Education;
import org.centrale.hceres.items.EducationInvolvment;
import org.centrale.hceres.items.EducationLevel;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TypeActivity;
import org.centrale.hceres.repository.ActivityRepository;
import org.centrale.hceres.repository.EducationInvolvmentRespository;
import org.centrale.hceres.repository.EducationLevelRepository;
import org.centrale.hceres.repository.EducationRepository;
import org.centrale.hceres.repository.ResearchRepository;
import org.centrale.hceres.repository.TypeActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Data;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

// permet de traiter la requete HTTP puis l'associer a la fonction de repository qui va donner une reponse
@Data
@Service
public class EducationService {
	
	/**
	 * Instanciation
	 */
	@Autowired
	private ResearchRepository researchRepo;
	@Autowired
	private EducationRepository educationRepo;
	@Autowired
	private EducationInvolvmentRespository educationInvolvmentRepo;
	@Autowired
	private EducationLevelRepository educationLevelRepo;
	@Autowired
	private ActivityRepository activityRepo;
	@Autowired
	private TypeActivityRepository typeActivityLevelRepo;
	
	/**
	 * permet de retourner la liste
	 */
	public Iterable<Education> getEducations(){
		return educationRepo.findAll();
	}
	
	/**
	 * retourner l'elmt selon son id
	 * @param id : id de l'elmt
	 * @return : elmt a retourner
	 */
	public Optional<Education> getEducation(final Integer id) { 
		return educationRepo.findById(id); 
	}
	
	/**
	 * supprimer l'elmt selon son id
	 * @param id : id de l'elmt
	 */
	public void deleteEducation(final Integer id) {
		educationRepo.deleteById(id);
	}
	
	/**
	 * permet d'ajouter un elmt
	 * @return : l'elemt ajouter a la base de donnees
	 */
	@Transactional
	public Education saveEducation(@RequestBody Map<String, Object> request) {
		
		Education educationTosave = new Education();
		
		// EducationCourseName :
		educationTosave.setEducationCourseName((String)request.get("educationCourseName"));
		
		// EducationCompletion :
		String dateString = (String)request.get("educationCompletion");
		educationTosave.setEducationCompletion(getDateFromString(dateString, "yyyy-MM-dd"));
		
		// EducationDescription :
		educationTosave.setEducationDescription((String)request.get("educationDescription"));
		
		// EducationFormation :
		educationTosave.setEducationFormation((String)request.get("educationFormation"));
		
				
		// EducationInvolvment
		EducationInvolvment educationInvolvment = new EducationInvolvment();
		educationInvolvment.setEducationInvolvmentName((String)request.get("educationInvolvmentText"));
		EducationInvolvment savedEducationInvolvment = educationInvolvmentRepo.save(educationInvolvment);
		educationTosave.setEducationInvolvmentId(savedEducationInvolvment);
		
		
		// EducationLevel : 
		EducationLevel educationLevel = new EducationLevel();
		educationLevel.setEducationLevelName((String)request.get("educationLevelText"));
		EducationLevel saveEducationLevel = educationLevelRepo.save(educationLevel);
		educationTosave.setEducationLevelId(saveEducationLevel);
		
		// Activity : 
		Activity activity = new Activity();
		TypeActivity typeActivity = typeActivityLevelRepo.getById(12);
		activity.setIdTypeActivity(typeActivity);
		
		// ajouter cette activité à la liste de ce chercheur :
		String researcherIdStr = (String)request.get("researcherId");
		int researcherId = -1;
		researcherId = Integer.parseInt(researcherIdStr);
		Optional<Researcher> researcherOp = researchRepo.findById(researcherId);
		Researcher researcher = researcherOp.get();
		
		Collection<Activity> activityCollection = researcher.getActivityCollection();
		activityCollection.add(activity);
		researcher.setActivityCollection(activityCollection);
		
		// Ajouter cette activité au chercheur :
		Collection<Researcher> activityResearch = activity.getResearcherCollection();
		if (activityResearch == null) {
			activityResearch = new ArrayList<Researcher>();
		}
		activityResearch.add(researcher);
		activity.setResearcherCollection(activityResearch);
		
		Activity savedActivity = activityRepo.save(activity);
		educationTosave.setActivity(savedActivity);
		
		
		// Id de l'education :
		Integer idEducation = activity.getIdActivity();
		educationTosave.setIdActivity(idEducation);
				
		// Enregistrer Education dans la base de données :
		Education saveEducation = educationRepo.save(educationTosave);
		
		return saveEducation;
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












