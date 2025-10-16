package org.centrale.hceres.service;

import java.util.*;

import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.CompanyCreation;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TypeActivityId;
import org.centrale.hceres.repository.ActivityRepository;
import org.centrale.hceres.repository.CompanyCreationRepository;
import org.centrale.hceres.util.RequestParseException;
import org.centrale.hceres.util.RequestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.Data;

// permet de traiter la requete HTTP puis l'associer a la fonction de repository qui va donner une reponse
@Data
@Service
public class CompanyCreationService {

    @Autowired
    private CompanyCreationRepository companyCreationRepo;

    @Autowired
    private ActivityRepository activityRepo;

    /**
     * permet de retourner la liste
     */
    public List<Activity> getCompanyCreations() {
        return activityRepo.findByIdTypeActivity(TypeActivityId.SEI_COMPANY_CREATION.getId());
    }

    /**
     * supprimer l'elmt selon son id
     *
     * @param id : id de l'elmt
     */
    public void deleteCompanyCreation(final Integer id) {
        companyCreationRepo.deleteById(id);
    }

    /**
     * permet d'ajouter un elmt
     *
     * @return : l'elemt ajouter a la base de donnees
     */
    public Activity saveCompanyCreation(@RequestBody Map<String, Object> request) throws RequestParseException {

        CompanyCreation companyCreation = new CompanyCreation();
        companyCreation.setCompanyCreationName(RequestParser.getAsString(request.get("companyCreationName")));
        companyCreation.setCompanyCreationDate(RequestParser.getAsDate(request.get("companyCreationDate")));
        companyCreation.setCompanyCreationActive(RequestParser.getAsBoolean(request.get("companyCreationActive")));

        // Activity :
        Activity activity = new Activity();
        companyCreation.setActivity(activity);
        activity.setCompanyCreation(companyCreation);
        activity.setIdTypeActivity(TypeActivityId.SEI_COMPANY_CREATION.getId());

        // get list of researcher doing this activity - currently only one is sent
        activity.setResearcherList(Collections.singletonList(new Researcher(RequestParser.getAsInteger(request.get("researcherId")))));

        activity = activityRepo.save(activity);
        return activity;
    }

}













