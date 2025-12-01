package org.centrale.hceres.dto.csv;

import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.centrale.hceres.dto.csv.utils.*;
import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.ScientificExpertise;
import org.centrale.hceres.items.TypeActivityId;
import org.centrale.hceres.dto.csv.utils.SupportedCsvTemplate;
import org.centrale.hceres.util.RequestParseException;
import org.centrale.hceres.util.RequestParser;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TypeActivity;

@EqualsAndHashCode(callSuper = true)
@Data
public class CsvScientificExpertise extends DependentCsv<Activity, Integer> {

    // important the read field of name id_activity isn't the same
    // id activity in activity.csv
    // to get the id activity use both key:
    // the type of activity and the specific count
    private Integer idCsvScientificExpertise;
    private static final int ID_CSV_SCIENTIFIC_EXPERTISE_ORDER = 0;

    private java.sql.Date startDate;
    private static final int START_DATE_ORDER = 1;
    private Integer idType;
    private static final int ID_TYPE_ORDER = 2;
    private String description;
    private static final int DESCRIPTION_ORDER = 3;
    private java.sql.Date endDate;
    private static final int END_DATE_ORDER = 4;


    // dependency element
    private CsvActivity csvActivity;
    private Map<Integer, CsvActivity> activityIdCsvMap;

    public CsvScientificExpertise(Map<Integer, CsvActivity> activityIdCsvMap) {
        this.activityIdCsvMap = activityIdCsvMap;
    }

    public Integer getIdCsvScientificExpertise() {
        return idCsvScientificExpertise;
    }

    public void setIdCsvScientificExpertise(Integer idCsvScientificExpertise) {
        this.idCsvScientificExpertise = idCsvScientificExpertise;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
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
                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_SCIENTIFIC_EXPERTISE_ORDER,
                        f -> this.setIdCsvScientificExpertise(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(START_DATE_ORDER,
                        f -> this.setStartDate(RequestParser.getAsDateCsvFormat(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(ID_TYPE_ORDER,
                        f -> this.setIdType(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(DESCRIPTION_ORDER,
                        f -> this.setDescription(RequestParser.getAsString(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(END_DATE_ORDER,
                        f -> this.setEndDate(RequestParser.getAsDateCsvFormat(csvData.get(f))))
        );
    }

    @Override
    public void initializeDependencies() throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvDependencyException(ID_CSV_SCIENTIFIC_EXPERTISE_ORDER,
                        this.getIdCsvScientificExpertise(),
                        SupportedCsvTemplate.ACTIVITY,
                        this.activityIdCsvMap.get(this.getIdCsvScientificExpertise()),
                        this::setCsvActivity)
        );
    }

    @Override
    public Activity convertToEntity() {
        Activity activity = this.getCsvActivity().convertToEntity();
        TypeActivity type = new TypeActivity();
        type.setIdTypeActivity(TypeActivityId.SCIENTIFIC_EXPERTISE.getId());
        activity.setTypeActivity(type);
        ScientificExpertise scientificExpertise = new ScientificExpertise();
        scientificExpertise.setStartDate(this.getStartDate());
        // direct use of id saved in database scientific_expertise_type
        scientificExpertise.getScientificExpertiseType().setScientificExpertiseTypeId(this.getIdType());
        scientificExpertise.setDescription(this.getDescription());
        scientificExpertise.setEndDate(this.getEndDate());

        activity.setScientificExpertise(scientificExpertise);
        scientificExpertise.setActivity(activity);
        return activity;
    }

    @Override
    public String getMergingKey() {
        return (this.getCsvActivity().getCsvResearcher().getIdDatabase()
                + "_" + this.getStartDate()
                + "_" + this.getIdType()
                + "_" + this.getDescription()).toLowerCase();
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
                + "_" + entity.getScientificExpertise().getStartDate()
                + "_" + entity.getScientificExpertise().getScientificExpertiseTypeId()
                + "_" + entity.getScientificExpertise().getDescription()).toLowerCase();
    }

    @Override
    public void setIdDatabaseFromEntity(Activity entity) {
        this.setIdDatabase(entity.getIdActivity());
    }

    @Override
    public Integer getIdCsv() {
        return this.getIdCsvScientificExpertise();
    }
}
