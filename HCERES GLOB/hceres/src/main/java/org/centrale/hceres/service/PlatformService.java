package org.centrale.hceres.service;

import org.centrale.hceres.items.*;
import org.centrale.hceres.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PlatformService {

    @Autowired
    PlatformRepository platformRepository;
    @Autowired
    private ResearchRepository researchRepo;
    @Autowired
    private ActivityRepository activityRepo;
    @Autowired
    private TypeActivityRepository typeActivityLevelRepo;


    public Iterable<Platform> getPlatforms(){
        return platformRepository.findAll();
    }


    public Optional<Platform> getPlatform(final Integer id) {
        return platformRepository.findById(id);
    }


    public void deleteEducation(final Integer id) {
        platformRepository.deleteById(id);
    }

    public Platform savePlatform(@RequestBody Map<String, Object> request) {

        Platform platfomToSave = new Platform();

        System.out.println((String) request.get("researcherId"));

        // Creation Date
        try {
        platfomToSave.setCreationDate(getDateFromString((String)request.get("creationDate"), "yyyy-MM-dd"));}
        catch (Exception e){
            platfomToSave.setCreationDate(getDateFromString("2022-03-05", "yyyy-MM-dd"));
        }

        // Description
        platfomToSave.setDescription((String)request.get("description"));

        // Managers
        platfomToSave.setManagers((String)request.get("managers"));

        // Affiliation
        platfomToSave.setAffiliation((String)request.get("affiliation"));

        // Labellisation
        platfomToSave.setLabellisation((String)request.get("labellisation"));

        // Open Private Researches
        platfomToSave.setOpenPrivateResearchers(Boolean.valueOf((String)request.get("openPrivateResearchers")));


        // Activity :
        Activity activity = new Activity();
        TypeActivity typeActivity = typeActivityLevelRepo.getById(6);
        activity.setIdTypeActivity(typeActivity);

        // Add this activity to the researcher activity list :
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
        platfomToSave.setActivity(savedActivity);


        // Created platform id :
        Integer idPlatform = activity.getIdActivity();
        platfomToSave.setIdActivity(idPlatform);

        // Persist Platform to database :
        Platform savePlatform = platformRepository.save(platfomToSave);

        return savePlatform;


    }

    // Date to String
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
