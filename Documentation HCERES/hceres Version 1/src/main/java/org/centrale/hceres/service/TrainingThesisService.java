package org.centrale.hceres.service;

import java.util.*;
import org.centrale.hceres.items.Activity;

import org.centrale.hceres.items.PhdStudent;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TrainingThesis;
import org.centrale.hceres.items.TypeActivityId;
import org.centrale.hceres.repository.ActivityRepository;
import org.centrale.hceres.repository.PhdStudentRepository;
import org.centrale.hceres.repository.TrainingThesisRepository;
import org.centrale.hceres.util.RequestParseException;
import org.centrale.hceres.util.RequestParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TrainingThesisService {

    @Autowired
    private ActivityRepository activityRepo;

    @Autowired
    private PhdStudentRepository phdStudentRepository;

    /**
     * Retourner toutes les thèses
     */
    public List<Activity> getTrainingTheses() {
        return activityRepo.findByIdTypeActivity(TypeActivityId.TRAINING_THESIS_PUBLICATION.getId());
    }

    /**
     * Supprimer une thèse
     */
    public void deleteTrainingThesis(Integer idActivity) {
        activityRepo.deleteById(idActivity);
    }

    /**
     * Ajouter une nouvelle thèse depuis une requête JSON
     */
     public Activity saveTrainingThesis(Map<String, Object> request) throws RequestParseException {

        TrainingThesis thesis = new TrainingThesis();
        Activity activity = new Activity();

        // --- champs de TrainingThesis ---
        thesis.setThesisStart(RequestParser.getAsDate(request.get("thesisStart")));
        thesis.setThesisTypeId(RequestParser.getAsInteger(request.get("thesisTypeId")));
        thesis.setThesisMainFunding(RequestParser.getAsString(request.get("thesisMainFunding")));
        thesis.setThesisDefenseDate(RequestParser.getAsDate(request.get("thesisDefenseDate")));
        thesis.setThesisDuration(RequestParser.getAsInteger(request.get("thesisDuration")));
        thesis.setThesisFutur(RequestParser.getAsString(request.get("thesisFutur")));
        thesis.setThesisNumberArticles(RequestParser.getAsInteger(request.get("thesisNumberArticles")));
        thesis.setThesisNumberArticlesFirstSecondPosition(
                RequestParser.getAsInteger(request.get("thesisNumberArticlesFirstSecondPosition")));
        thesis.setThesisArticlesFirstSecondPositionReferences(
                RequestParser.getAsString(request.get("thesisArticlesFirstSecondPositionReferences")));

        // --- PhD student ---
        Integer phdStudentId = RequestParser.getAsInteger(request.get("phdStudentId"));
        if (phdStudentId != null) {
            PhdStudent phdStudent = phdStudentRepository.findById(phdStudentId).orElse(null);
            thesis.setPhdStudentId(phdStudent);
        }

        // --- Lien Activity <-> TrainingThesis ---
        thesis.setActivity(activity);
        activity.setTrainingThesis(thesis);

        // type d’activité
        activity.setIdTypeActivity(TypeActivityId.TRAINING_THESIS_PUBLICATION.getId());

        // chercheur porteur
        activity.setResearcherList(
                Collections.singletonList(
                        new Researcher(RequestParser.getAsInteger(request.get("researcherId")))
                )
        );
        activity.setTrainingThesis(thesis);
        activity = activityRepo.save(activity);

        return activity;
    }

}
