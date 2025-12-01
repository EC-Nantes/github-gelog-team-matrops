package org.centrale.hceres.dto.csv;

import java.sql.Date;
import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.centrale.hceres.dto.csv.utils.*;
import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.EducationalOutput;
import org.centrale.hceres.items.TypeActivityId;
import org.centrale.hceres.dto.csv.utils.SupportedCsvTemplate;
import org.centrale.hceres.util.RequestParser;

import java.util.List;
import java.util.Map;
import org.centrale.hceres.items.Education;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TypeActivity;

@EqualsAndHashCode(callSuper = true)
@Data
public class CsvEducationalOutput extends DependentCsv<Activity, Integer> {
//    id_activity;completion_date;id_type;description

    // important the read field of name id_activity isn't the same
    // id activity in activity.csv
    // to get the id activity use both key:
    // the type of activity and the specific count
    private Integer idCsvEducationalOutput;
    private static final int ID_CSV_EDUCATIONAL_OUTPUT_ORDER = 0;

    private java.sql.Date completionDate;
    private static final int COMPLETION_DATE_ORDER = 1;
    private Integer idType;
    private static final int ID_TYPE_ORDER = 2;
    private String description;
    private static final int DESCRIPTION_ORDER = 3;

    // dependency element
    private CsvActivity csvActivity;
    private Map<Integer, CsvActivity> activityIdCsvMap;

    public CsvEducationalOutput(Map<Integer, CsvActivity> activityIdCsvMap) {
        this.activityIdCsvMap = activityIdCsvMap;
    }

    public Integer getIdCsvEducationalOutput() {
        return idCsvEducationalOutput;
    }

    public void setIdCsvEducationalOutput(Integer idCsvEducationalOutput) {
        this.idCsvEducationalOutput = idCsvEducationalOutput;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
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
                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_EDUCATIONAL_OUTPUT_ORDER,
                        f -> this.setIdCsvEducationalOutput(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(COMPLETION_DATE_ORDER,
                        f -> this.setCompletionDate(RequestParser.getAsDateCsvFormat(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(ID_TYPE_ORDER,
                        f -> this.setIdType(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(DESCRIPTION_ORDER,
                        f -> this.setDescription(RequestParser.getAsString(csvData.get(f))))
        );
    }

    @Override
    public void initializeDependencies() throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvDependencyException(ID_CSV_EDUCATIONAL_OUTPUT_ORDER,
                        this.getIdCsvEducationalOutput(),
                        SupportedCsvTemplate.ACTIVITY,
                        this.activityIdCsvMap.get(this.getIdCsvEducationalOutput()),
                        this::setCsvActivity)
        );
    }

    @Override
    public Activity convertToEntity() {
        Activity activity = this.getCsvActivity().convertToEntity();
        TypeActivity type = new TypeActivity();
        type.setIdTypeActivity(TypeActivityId.EDUCATIONAL_OUTPUT.getId());
        activity.setTypeActivity(type);
        Education education = new Education();
        education.setEducationCompletion(this.getCompletionDate());
        education.getEducationLevelId().setEducationLevelId(this.getIdType());
        education.setEducationFormation(this.getDescription());
        activity.setEducation(education);
        education.setActivity(activity);
        return activity;
    }

    @Override
    public String getMergingKey() {
        return (this.getCsvActivity().getCsvResearcher().getIdDatabase()
                + "_" + this.getCompletionDate()
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
                + "_" + entity.getEducation().getEducationCompletion()
                + "_" + entity.getEducation().getEducationLevelId()
                + "_" + entity.getEducation().getEducationFormation()).toLowerCase();
    }

    @Override
    public void setIdDatabaseFromEntity(Activity entity) {
        this.setIdDatabase(entity.getIdActivity());
    }

    @Override
    public Integer getIdCsv() {
        return this.getIdCsvEducationalOutput();
    }
}
