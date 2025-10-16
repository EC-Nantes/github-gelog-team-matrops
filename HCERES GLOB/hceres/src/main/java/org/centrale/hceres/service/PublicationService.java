package org.centrale.hceres.service;

import java.util.*;

import org.centrale.hceres.items.*;

import org.centrale.hceres.repository.ActivityRepository;
import org.centrale.hceres.util.RequestParseException;
import org.centrale.hceres.util.RequestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.Data;

// permet de traiter la requete HTTP puis l'associer a la fonction de repository qui va donner une reponse
@Data
@Service
public class PublicationService {


    @Autowired
    private ActivityRepository activityRepo;

    /**
     * permet de retourner la liste
     */
    public List<Activity> getPublications() {
        return activityRepo.findByIdTypeActivity(TypeActivityId.PUBLICATION.getId());
    }

    /**
     * supprimer l'elmt selon son id
     *
     * @param id : id de l'elmt
     */
    public void deletePublication(final Integer id) {
        activityRepo.deleteById(id);
    }

    /**
     * permet d'ajouter un elmt
     *
     * @return : l'elemt ajouter a la base de donnees
     */
    public Activity savePublication(@RequestBody Map<String, Object> request) throws RequestParseException {

        Publication publication = new Publication();
        publication.setTitle(RequestParser.getAsString(request.get("title")));
        publication.setAuthors(RequestParser.getAsString(request.get("authors")));
        publication.setSource(RequestParser.getAsString(request.get("source")));
        publication.setPublicationDate(RequestParser.getAsDate(request.get("publicationDate")));
        publication.setPmid(RequestParser.getAsString(request.get("pmid")));
        publication.setImpactFactor(RequestParser.getAsBigDecimal(request.get("impactFactor")));

        // Activity :
        Activity activity = new Activity();
        publication.setActivity(activity);
        activity.setPublication(publication);
        activity.setIdTypeActivity(TypeActivityId.PUBLICATION.getId());

        // get list of researcher doing this activity - currently only one is sent
        activity.setResearcherList(Collections.singletonList(new Researcher(RequestParser.getAsInteger(request.get("researcherId")))));

        activity = activityRepo.save(activity);
        return activity;
    }

}













