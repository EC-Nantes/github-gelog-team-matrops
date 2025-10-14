package org.centrale.hceres.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.OralCommunication;
import org.centrale.hceres.items.Meeting;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TypeActivity;
import org.centrale.hceres.items.TypeOralCommunication;
import org.centrale.hceres.repository.ActivityRepository;
import org.centrale.hceres.repository.MeetingCongressOrgRepository;
import org.centrale.hceres.repository.MeetingRepository;
import org.centrale.hceres.repository.OralCommunicationRepository;
import org.centrale.hceres.repository.ResearchRepository;
import org.centrale.hceres.repository.TypeActivityRepository;
import org.centrale.hceres.repository.TypeOralCommunicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@Data
@Service
public class OralCommunicationService {
	
	/**
	 * Instanciation
	 */
	@Autowired
	private ResearchRepository researchRepo;
	
	@Autowired
	private OralCommunicationRepository oralCommunicationRepo;
	
	@Autowired
	private TypeOralCommunicationRepository typeOralCommunicationRepo;
	
	@Autowired
	private MeetingRepository meetingRepo;
	
	@Autowired
	private TypeActivityRepository typeActivityLevelRepo;
	
	@Autowired
	private ActivityRepository activityRepo;
	
	@Autowired
	private MeetingCongressOrgRepository meetingCongressOrgRepo;
	
	/**
	 * permet d'ajouter un elmt
	 * @return : l'elemt ajouter a la base de donnees
	 */
	@Transactional
	public OralCommunication saveOralCommunication(@RequestBody Map<String, Object> request) {
		
		OralCommunication oralCommunicationTosave = new OralCommunication();
		
		// OralCommunicationTitle :
		oralCommunicationTosave.setOralCommunicationTitle((String)request.get("OralCommunicationTitle"));
		
		// OralCommunicationDat :
		String dateString = (String)request.get("OralCommunicationDate");
		oralCommunicationTosave.setOralCommunicationDat(getDateFromString(dateString, "yyyy-MM-dd"));
		
		// Authors :
		oralCommunicationTosave.setAuthors((String)request.get("Authors"));
		
		// Activity : 
		Activity activity = new Activity();
		TypeActivity typeActivity = typeActivityLevelRepo.getById(32);
		activity.setIdTypeActivity(typeActivity);
		
		// Meeting
		Meeting meeting = new Meeting();
		meeting.setNeetingName((String)request.get("MeetingName"));
		String meetingYearString = (String)request.get("MeetingYear");
		meeting.setMeetingYear(Integer.parseInt(meetingYearString));
		meeting.setMeetingLocation((String)request.get("MeetingLocation"));
		String dateStringStart = (String)request.get("MeetingStart");
		meeting.setMeetingStart(getDateFromString(dateStringStart, "yyyy-MM-dd"));
		String dateStringEnd = (String)request.get("MeetingEnd");
		meeting.setMeetingEnd(getDateFromString(dateStringEnd, "yyyy-MM-dd"));
		
		Meeting savedMeeting = meetingRepo.save(meeting);
		oralCommunicationTosave.setMeetingId(savedMeeting);
		
		
		// TypeOralCommunication : 
		TypeOralCommunication typeOralCommunication = new TypeOralCommunication();
		typeOralCommunication.setTypeOralCommunicationName((String)request.get("TypeOralCommunicationName"));
		TypeOralCommunication savetypeOralCommunication = typeOralCommunicationRepo.save(typeOralCommunication);
		oralCommunicationTosave.setTypeOralCommunicationId(savetypeOralCommunication);
		
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
		oralCommunicationTosave.setActivity(savedActivity);
		
		
		// Id :
		Integer idOralCommunicationTosave = activity.getIdActivity();
		oralCommunicationTosave.setIdActivity(idOralCommunicationTosave);
				
		// Enregistrer dans la base de données :
		OralCommunication saveOralCommunication = oralCommunicationRepo.save(oralCommunicationTosave);
		
		return saveOralCommunication;
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
