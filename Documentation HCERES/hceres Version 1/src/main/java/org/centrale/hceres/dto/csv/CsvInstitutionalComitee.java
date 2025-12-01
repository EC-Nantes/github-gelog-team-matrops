package org.centrale.hceres.dto.csv;

import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.centrale.hceres.dto.csv.utils.*;
import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.InstitutionalComitee;
import org.centrale.hceres.items.TypeActivityId;
import org.centrale.hceres.dto.csv.utils.SupportedCsvTemplate;
import org.centrale.hceres.util.RequestParser;

import java.util.List;
import java.util.Map;
import org.centrale.hceres.items.Researcher;

@EqualsAndHashCode(callSuper = true)
@Data
public class CsvInstitutionalComitee extends DependentCsv<Activity, Integer> {

    // important the read field of name id_activity isn't the same
    // id activity in activity.csv
    // to get the id activity use both key:
    // the type of activity and the specific count
    private Integer idCsvInstitutionalComitee;
    private static final int ID_CSV_INSTITUTIONAL_COMITEE_ORDER = 0;

    private Integer year;
    private static final int YEAR_ORDER = 1;
    private String nameInstitutionalComitee;
    private static final int NAME_INSTITUTIONAL_COMITEE_ORDER = 2;
    private Integer idRolePiLabEval;
    private static final int ID_ROLE_PI_LAB_EVAL_ORDER = 3;

    // dependency element
    private CsvActivity csvActivity;
    private Map<Integer, CsvActivity> activityIdCsvMap;

    public CsvInstitutionalComitee(Map<Integer, CsvActivity> activityIdCsvMap) {
        this.activityIdCsvMap = activityIdCsvMap;
    }

    public static int getID_CSV_INSTITUTIONAL_COMITEE_ORDER() {
        return ID_CSV_INSTITUTIONAL_COMITEE_ORDER;
    }

    public static int getYEAR_ORDER() {
        return YEAR_ORDER;
    }

    public static int getNAME_INSTITUTIONAL_COMITEE_ORDER() {
        return NAME_INSTITUTIONAL_COMITEE_ORDER;
    }

    public static int getID_ROLE_PI_LAB_EVAL_ORDER() {
        return ID_ROLE_PI_LAB_EVAL_ORDER;
    }

    public Integer getIdCsvInstitutionalComitee() {
        return idCsvInstitutionalComitee;
    }

    public void setIdCsvInstitutionalComitee(Integer idCsvInstitutionalComitee) {
        this.idCsvInstitutionalComitee = idCsvInstitutionalComitee;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getNameInstitutionalComitee() {
        return nameInstitutionalComitee;
    }

    public void setNameInstitutionalComitee(String nameInstitutionalComitee) {
        this.nameInstitutionalComitee = nameInstitutionalComitee;
    }

    public Integer getIdRolePiLabEval() {
        return idRolePiLabEval;
    }

    public void setIdRolePiLabEval(Integer idRolePiLabEval) {
        this.idRolePiLabEval = idRolePiLabEval;
    }

    public CsvActivity getCsvActivity() {
        return csvActivity;
    }

    public void setCsvActivity(CsvActivity csvActivity) {
        this.csvActivity = csvActivity;
    }

    public Map<Integer, CsvActivity> getActivityIdCsvMap() {
        return activityIdCsvMap;
    }

    public void setActivityIdCsvMap(Map<Integer, CsvActivity> activityIdCsvMap) {
        this.activityIdCsvMap = activityIdCsvMap;
    }
    
    

    @Override
    public void fillCsvDataWithoutDependency(List<?> csvData) throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_INSTITUTIONAL_COMITEE_ORDER,
                        f -> this.setIdCsvInstitutionalComitee(RequestParser.getAsInteger(csvData.get(f)))),
                () -> CsvParserUtil.wrapCsvParseException(YEAR_ORDER,
                        f -> this.setYear(RequestParser.getAsInteger(csvData.get(f)))),
                () -> CsvParserUtil.wrapCsvParseException(NAME_INSTITUTIONAL_COMITEE_ORDER,
                        f -> this.setNameInstitutionalComitee(RequestParser.getAsString(csvData.get(f)))),
                () -> CsvParserUtil.wrapCsvParseException(ID_ROLE_PI_LAB_EVAL_ORDER,
                        f -> this.setIdRolePiLabEval(RequestParser.getAsInteger(csvData.get(f))))
        );
    }

    @Override
    public void initializeDependencies() throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvDependencyException(ID_CSV_INSTITUTIONAL_COMITEE_ORDER,
                        this.getIdCsvInstitutionalComitee(),
                        SupportedCsvTemplate.ACTIVITY,
                        this.activityIdCsvMap.get(this.getIdCsvInstitutionalComitee()),
                        this::setCsvActivity)
        );
    }

    @Override
    public Activity convertToEntity() {
        Activity activity = this.getCsvActivity().convertToEntity();
        activity.getTypeActivity().setIdTypeActivity(TypeActivityId.RESPONSIBILITY_INSTITUTIONAL_COMITEE_JURY.getId());
        InstitutionalComitee institutionalComitee = new InstitutionalComitee();
        institutionalComitee.setYear(this.getYear());
        institutionalComitee.setInstitutionalComiteeName(this.getNameInstitutionalComitee());
        // clean and verfiy after doctor gives sql insert values for roles
        institutionalComitee.getLaboratoryEvaluationRole().setLaboratoryEvaluationRoleId(this.getIdRolePiLabEval());

        activity.setInstitutionalComitee(institutionalComitee);
        institutionalComitee.setActivity(activity);
        return activity;
    }

    @Override
    public String getMergingKey() {
        return (this.getCsvActivity().getCsvResearcher().getIdDatabase()
                + "_" + this.getYear()
                + "_" + this.getNameInstitutionalComitee()
                + "_" + this.getIdRolePiLabEval()).toLowerCase();
    }

    @Override
    public String getMergingKey(Activity entity) {
        Collection<Researcher> researchers = entity.getResearcherList();
        Researcher firstResearcher = null;
        if (researchers != null && !researchers.isEmpty()) {
            firstResearcher = researchers.iterator().next(); // prend le premier élément
        }
        Integer researcherId = firstResearcher != null ? firstResearcher.getResearcherId() : null;
        return (researcherId
            + "_" + entity.getInstitutionalComitee().getYear()
            + "_" + entity.getInstitutionalComitee().getInstitutionalComiteeName()
            + "_" + entity.getInstitutionalComitee().getLaboratoryEvaluationRoleId()).toLowerCase();
    }

    @Override
    public void setIdDatabaseFromEntity(Activity entity) {
        this.setIdDatabase(entity.getIdActivity());
    }

    @Override
    public Integer getIdCsv() {
        return this.getIdCsvInstitutionalComitee();
    }
}
