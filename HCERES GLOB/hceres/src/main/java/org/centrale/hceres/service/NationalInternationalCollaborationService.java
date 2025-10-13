package org.centrale.hceres.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.NationalInternationalCollaboration;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TypeActivity;
import org.centrale.hceres.items.TypeCollab;
import org.centrale.hceres.repository.ActivityRepository;
import org.centrale.hceres.repository.NationalInternationalCollaborationRepository;
import org.centrale.hceres.repository.ResearchRepository;
import org.centrale.hceres.repository.TypeActivityRepository;
import org.centrale.hceres.repository.TypeCollabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@Data
@Service
public class NationalInternationalCollaborationService {

	/**
	 * Instanciation
	 */
	@Autowired
	private ResearchRepository researchRepo;
	
	@Autowired
	private ActivityRepository activityRepo;
	
	@Autowired
	private TypeActivityRepository typeActivityLevelRepo;
	
	@Autowired
	private NationalInternationalCollaborationRepository nationalInternationalCollaborationRepo;
	
	@Autowired
	private TypeCollabRepository typeCollabRepo;
	
	/**
	 * permet d'ajouter un elmt
	 * @return : l'elemt ajouter a la base de donnees
	 */
	@Transactional
	public NationalInternationalCollaboration saveNationalInternationalCollaboration(@RequestBody Map<String, Object> request) {
		
		NationalInternationalCollaboration nationalInternationalCollaborationTosave = new NationalInternationalCollaboration();
		
		// DateProjectStart :
		String dateProjectStart = (String)request.get("DateProjectStart");
		nationalInternationalCollaborationTosave.setDateProjectStart(getDateFromString(dateProjectStart, "yyyy-MM-dd"));
		
		// PartnerEntity :
		nationalInternationalCollaborationTosave.setPartnerEntity((String)request.get("PartnerEntity"));
		
		// CountryStateCity :
		nationalInternationalCollaborationTosave.setCountryStateCity((String)request.get("CountryStateCity"));
		
		// setPiPartners :
		nationalInternationalCollaborationTosave.setPiPartners((String)request.get("PiPartners"));
		
		// MailPartners
		nationalInternationalCollaborationTosave.setMailPartners((String)request.get("MailPartners"));
		
		// setProjetcTitle
		nationalInternationalCollaborationTosave.setProjetcTitle((String)request.get("ProjetcTitle"));
		
		// StrategicRecurringCollab : probleme => boolean n'est pas de type bit
		nationalInternationalCollaborationTosave.setStrategicRecurringCollab(Boolean.valueOf((String)request.get("StrategicRecurringCollab")));
		
		// ActiveProject
		nationalInternationalCollaborationTosave.setActiveProject(Boolean.valueOf((String)request.get("ActiveProject")));
		
		// AssociatedFunding
		nationalInternationalCollaborationTosave.setAssociatedFunding((String)request.get("AssociatedFunding"));

		// NumberResultingPublications
		nationalInternationalCollaborationTosave.setNumberResultingPublications(Integer.parseInt((String)request.get("NumberResultingPublications")));
		
		// RefJointPublication
		nationalInternationalCollaborationTosave.setRefJointPublication((String)request.get("RefJointPublication"));

		// UmrCoordinated
		nationalInternationalCollaborationTosave.setUmrCoordinated(Boolean.valueOf((String)request.get("UmrCoordinated")));
		
		
		// AgreementSigned
		nationalInternationalCollaborationTosave.setAgreementSigned(Boolean.valueOf((String)request.get("AgreementSigned")));
		
		
		// TypeCollab :
		TypeCollab typeCollab = new TypeCollab();
		typeCollab.setNameChoice((String)request.get("NameChoice"));
		TypeCollab savedTypeCollab = typeCollabRepo.save(typeCollab);
		nationalInternationalCollaborationTosave.setTypeCollabId(savedTypeCollab);
		
		// Activity : 
		Activity activity = new Activity();
		TypeActivity typeActivity = typeActivityLevelRepo.getById(13);
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
		nationalInternationalCollaborationTosave.setActivity(savedActivity);
		
		
		// Id de l'education :
		Integer idEducation = activity.getIdActivity();
		nationalInternationalCollaborationTosave.setIdActivity(idEducation);
				
		// Enregistrer Education dans la base de données :
		NationalInternationalCollaboration nationalInternationalCollaborationSaved = nationalInternationalCollaborationRepo.save(nationalInternationalCollaborationTosave);
		
		return nationalInternationalCollaborationSaved;
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
