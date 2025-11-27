package org.centrale.hceres.service;

import lombok.Data;
import org.centrale.hceres.items.*;
import org.centrale.hceres.repository.ActivityRepository;
import org.centrale.hceres.repository.ResearchRepository;
import org.centrale.hceres.repository.TypeActivityRepository;
import org.centrale.hceres.repository.PublicationRepository;
import org.centrale.hceres.repository.PublicationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@Service
public class PublicationService {

    /**
     * Instanciation
     */

    @Autowired
    private PublicationRepository publicationrepo;
    @Autowired
    private PublicationTypeRepository publicationTypeRepo;
    @Autowired
    private ResearchRepository researchRepo;
    @Autowired
    private ActivityRepository activityRepo;
    @Autowired
    private TypeActivityRepository typeActivityLevelRepo;

    /**
     * Retourner la liste des publications
     */
    public Iterable<Publication> getPublications()
    {return  publicationrepo.findAll();}

    /**
     * retourner l'elmt selon son id
     * @param id : id de l'elmt
     * @return : elmt a retourner
     */
    public Optional<Publication> getPublication(final Integer id)
    {return publicationrepo.findById(id);}

    /**
     * permet d'ajouter un elmt
     * @return : l'elemt ajouter a la base de donnees
     */
    @Transactional
    public Publication savePublication(@RequestBody Map<String, Object> request){

        Publication publicationTosave = new Publication();

        //Publication title
        publicationTosave.setTitle((String) request.get("title"));

        //Publication authors
        publicationTosave.setAuthors((String) request.get("authors"));

        //Publication source
        publicationTosave.setSource((String) request.get("source"));

        //Publication date
        String datepub = (String) request.get("publicationDate");
        publicationTosave.setPublicationDate(getDateFromString(datepub, "yyyy-MM-dd"));

        //Publication pmid
        publicationTosave.setPmid((String) request.get("pmid"));

        //Publication impact_factor
        String inputString = (String) request.get("impactFactor");
        BigDecimal result = new BigDecimal(inputString);
        publicationTosave.setImpactFactor((result));

        //Publication type
        PublicationType publicationType = new PublicationType();
        publicationType.setPublicationTypeName((String) request.get("publicationTypeName"));
        PublicationType savePublicationType = publicationTypeRepo.save(publicationType);
        publicationTosave.setPublicationTypeId(savePublicationType);

        // Activity :
        Activity activity = new Activity();
        TypeActivity typeActivity = typeActivityLevelRepo.getById(1);
        activity.setIdTypeActivity(typeActivity);

        // Add this activity to the researcher activity list :
        String researcherIdStr = (String)request.get("researcherId");
        int researcherId = 1;
        if (researcherIdStr != null){
            researcherId = Integer.parseInt(researcherIdStr);
        }

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
        publicationTosave.setActivity(savedActivity);

        // Created publication id :
        Integer idPublication = activity.getIdActivity();
        publicationTosave.setIdActivity(idPublication);

        // Persist publication to database :
        Publication savePublication = publicationrepo.save(publicationTosave);

        return savePublication;

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

