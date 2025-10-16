/* --------------------------------------------------------------------------------
 * Projet HCERES
 *
 * Gestion de données pour l'HCERES
 *
 * Ecole Centrale Nantes - laboratoire CRTI
 * Avril 2021
 * L LETERTRE, S LIMOUX, JY MARTIN
 * -------------------------------------------------------------------------------- */
package org.centrale.hceres.items;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum TypeActivityId {
    UNDEFINED(0),
    PUBLICATION(1),
    BOOK(2),
    BOOK_CHAPTER(3),
    PATENT_LICENCE_INVENTION_DISCLOSURE(4),
    EDITORIAL_ACTIVITY(5),
    PLATFORM(6),
    TOOL_PRODUCT_DECISION_SUPPORT_TOOL(7),
    TOOL_PRODUCT_BIOCOLLECTION(8),
    TOOL_PRODUCT_SOFTWARE(9),
    TOOL_PRODUCT_DATABASE(10),
    TOOL_PRODUCT_COHORT(11),
    EDUCATIONAL_OUTPUT(12),
    NATIONAL_INTERNATIONAL_COLLABORATION(13),
    NETWORK(14),
    INVITED_SEMINAR(15),
    SCIENTIFIC_EXPERTISE(16),
    RESEARCH_CONTRACT_FUNDED_PUBLIC_CHARITABLE_INST(17),
    TRAINING_THESIS_PUBLICATION(18),
    INVOLVEMENT_TRAINING_M1_M2_TRAINEE_HOSTING(19),
    INVOLVEMENT_TRAINING_PEDAGOGICAL_RESPONSIBILITY(20),
    POST_DOC(21),
    PUBLIC_OUTREACH(22),
    REVIEWING_JOURNAL_ARTICLES(23),
    PROJECT_EVALUATION_THESIS(24),
    PROJECT_EVALUATION_GRANT(25),
    LAB_EVALUATION(26),
    RESPONSIBILITY_INSTITUTIONAL_COMITEE_JURY(27),
    SR_RESPONSIBILITY_LEARNED_SCIENTIFIC_SOCIETY(28),
    SR_AWARD(29),
    MEETING_CONGRESS_ORG(30),
    INVITED_ORAL_COMMUNICATION(31),
    ORAL_COMMUNICATION_POSTER(32),
    OUTGOING_MOBILITY(33),
    INCOMING_MOBILITY(34),
    SEI_CIFRE_FELLOWSHIP(35),
    SEI_LABCOM_CREATION(36),
    SEI_NETWORK_UNIT_CREATION(37),
    SEI_COMPANY_CREATION(38),
    SEI_LEAD_CONSORTIUM_INDUSTRY(39),
    SEI_INDUSTRIAL_R_D_CONTRACT(40),
    SEI_CLINICAL_TRIAL(41),

    // application defined activity types
    EDUCATION_FORMATION(1001),

    ;
    private final int id;

    TypeActivityId(int id) {
        this.id = id;
    }

    private static final Map<Integer, TypeActivityId> idToEnum = new HashMap<>();
    static {
        for (TypeActivityId e : values()) {
            idToEnum.put(e.id, e);
        }
    }

    public static TypeActivityId fromId(int activityTypeId) {
        return idToEnum.get(activityTypeId);
    }
}