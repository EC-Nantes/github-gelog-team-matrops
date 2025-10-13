package org.centrale.hceres.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.SeiClinicalTrial;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TypeActivity;
import org.centrale.hceres.repository.ActivityRepository;
import org.centrale.hceres.repository.SeiClinicalTrialRepository;
import org.centrale.hceres.repository.ResearchRepository;
import org.centrale.hceres.repository.TypeActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.Data;

// permet de traiter la requete HTTP puis l'associer a la fonction de repository qui va donner une reponse
@Data
@Service
public class SeiClinicalTrialService {
	
	/**
	 * Instanciation
	 */
	@Autowired
	private ResearchRepository researchRepo;
	@Autowired
	private SeiClinicalTrialRepository SeiClinicalTrialRepo;
	@Autowired
	private ActivityRepository activityRepo;
	@Autowired
	private TypeActivityRepository typeActivityLevelRepo;
	
	/**
	 * permet de retourner la liste
	 */
	public Iterable<SeiClinicalTrial> getSeiClinicalTrials(){
		return SeiClinicalTrialRepo.findAll();
	}
	
	/**
	 * retourner l'elmt selon son id
	 * @param id : id de l'elmt
	 * @return : elmt a retourner
	 */
	public Optional<SeiClinicalTrial> getSeiClinicalTrial(final Integer id) { 
		return SeiClinicalTrialRepo.findById(id); 
	}
	
	/**
	 * supprimer l'elmt selon son id
	 * @param id : id de l'elmt
	 */
	public void deleteSeiClinicalTrial(final Integer id) {
		SeiClinicalTrialRepo.deleteById(id);
	}
	
	/**
	 * permet d'ajouter un elmt
	 * @return : l'elemt ajouter a la base de donnees
	 */
	public SeiClinicalTrial saveSeiClinicalTrial(@RequestBody Map<String, Object> request) {
		
		SeiClinicalTrial SeiClinicalTrialTosave = new SeiClinicalTrial();
		
		
		// setStartDate :
		String dateString = (String)request.get("startDate");
		SeiClinicalTrialTosave.setStartDate(getDateFromString(dateString, "yyyy-MM-dd"));

        // setEndDate :
		String dateString2 = (String)request.get("endDate");
		SeiClinicalTrialTosave.setEndDate(getDateFromString(dateString2, "yyyy-MM-dd"));
		
		// setCoordinatorPartner :
		SeiClinicalTrialTosave.setCoordinatorPartner(Boolean.parseBoolean((String)request.get("coordinatorPartner")));
		
        // setTitleClinicalTrial :
		SeiClinicalTrialTosave.setTitleClinicalTrial((String)request.get("titleClinicalTrial"));

         // setRegistrationNb :
		SeiClinicalTrialTosave.setRegistrationNb((String)request.get("registrationNb"));
          
        // setSponsorName :
		SeiClinicalTrialTosave.setSponsorName((String)request.get("sponsorName"));

        // setIncludedPatientsNb :
		SeiClinicalTrialTosave.setIncludedPatientsNb(Integer.parseInt((String)request.get("includedPatientsNb")));

        // setFunding :
		SeiClinicalTrialTosave.setFunding((String)request.get("funding"));

        // setFundingAmount :
		SeiClinicalTrialTosave.setFundingAmount(Integer.parseInt((String)request.get("fundingAmount")));

	    // Activity : 
		Activity activity = new Activity();
		TypeActivity typeActivity = typeActivityLevelRepo.getById(41);
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
		SeiClinicalTrialTosave.setActivity(savedActivity);
		
		// Id de l'SeiClinicalTrial :
		Integer idSeiClinicalTrial = activity.getIdActivity();
		SeiClinicalTrialTosave.setIdActivity(idSeiClinicalTrial);
				
		// Enregistrer SeiClinicalTrial dans la base de données :
		SeiClinicalTrial saveSeiClinicalTrial = SeiClinicalTrialRepo.save(SeiClinicalTrialTosave);
		
		return saveSeiClinicalTrial;
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
