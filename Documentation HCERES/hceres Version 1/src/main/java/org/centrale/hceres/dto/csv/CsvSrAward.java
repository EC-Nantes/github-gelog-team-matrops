package org.centrale.hceres.dto.csv;

import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.centrale.hceres.dto.csv.utils.*;
import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.SrAward;
import org.centrale.hceres.items.TypeActivityId;
import org.centrale.hceres.dto.csv.utils.SupportedCsvTemplate;
import org.centrale.hceres.util.RequestParseException;
import org.centrale.hceres.util.RequestParser;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TypeActivity;

@EqualsAndHashCode(callSuper = true)
@Data
public class CsvSrAward extends DependentCsv<Activity, Integer> {

    // important the read field of name id_activity isn't the same
    // id activity in activity.csv
    // to get the id activity use both key:
    // the type of activity and the specific count
    private Integer idCsvSrAward;
    private static final int ID_CSV_SR_AWARD_ORDER = 0;

    private java.sql.Date awardDate;
    private static final int AWARD_DATE_ORDER = 1;
    private String awardeeName;
    private static final int AWARD_NAME_ORDER = 2;
    private String description;
    private static final int DESCRIPTION_ORDER = 3;

    // dependency element
    private CsvActivity csvActivity;
    private Map<Integer, CsvActivity> activityIdCsvMap;

    public CsvSrAward(Map<Integer, CsvActivity> activityIdCsvMap) {
        this.activityIdCsvMap = activityIdCsvMap;
    }

    public Integer getIdCsvSrAward() {
        return idCsvSrAward;
    }

    public void setIdCsvSrAward(Integer idCsvSrAward) {
        this.idCsvSrAward = idCsvSrAward;
    }

    public java.sql.Date getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(java.sql.Date awardDate) {
        this.awardDate = awardDate;
    }

    public String getAwardeeName() {
        return awardeeName;
    }

    public void setAwardeeName(String awardeeName) {
        this.awardeeName = awardeeName;
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
                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_SR_AWARD_ORDER,
                        f -> this.setIdCsvSrAward(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(AWARD_DATE_ORDER,
                        f -> this.setAwardDate(RequestParser.getAsDateCsvFormat(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(AWARD_NAME_ORDER,
                        f -> this.setAwardeeName(RequestParser.getAsString(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(DESCRIPTION_ORDER,
                        f -> this.setDescription(RequestParser.getAsString(csvData.get(f))))
        );
    }

    @Override
    public void initializeDependencies() throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvDependencyException(ID_CSV_SR_AWARD_ORDER,
                        this.getIdCsvSrAward(),
                        SupportedCsvTemplate.ACTIVITY,
                        this.activityIdCsvMap.get(this.getIdCsvSrAward()),
                        this::setCsvActivity)
        );
    }
    @Override
    public Activity convertToEntity() {
        Activity activity = this.getCsvActivity().convertToEntity();
        TypeActivity type = new TypeActivity();
        type.setIdTypeActivity(TypeActivityId.SR_AWARD.getId());
        activity.setTypeActivity(type);
        SrAward srAward = new SrAward();
        srAward.setAwardDate(this.getAwardDate());
        srAward.setAwardeeName(this.getAwardeeName());
        srAward.setDescription(this.getDescription());
        activity.setSrAward(srAward);
        srAward.setActivity(activity);
        return activity;
    }

    @Override
    public String getMergingKey() {
        return (this.getCsvActivity().getCsvResearcher().getIdDatabase()
                + "_" + this.getAwardDate()
                + "_" + this.getAwardeeName()
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
                + "_" + entity.getSrAward().getAwardDate()
                + "_" + entity.getSrAward().getAwardeeName()
                + "_" + entity.getSrAward().getDescription()).toLowerCase();
    }

    @Override
    public void setIdDatabaseFromEntity(Activity entity) {
        this.setIdDatabase(entity.getIdActivity());
    }

    @Override
    public Integer getIdCsv() {
        return this.getIdCsvSrAward();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idCsvSrAward);
        hash = 67 * hash + Objects.hashCode(this.awardDate);
        hash = 67 * hash + Objects.hashCode(this.awardeeName);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.csvActivity);
        hash = 67 * hash + Objects.hashCode(this.activityIdCsvMap);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CsvSrAward other = (CsvSrAward) obj;
        if (!Objects.equals(this.awardeeName, other.awardeeName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.idCsvSrAward, other.idCsvSrAward)) {
            return false;
        }
        if (!Objects.equals(this.awardDate, other.awardDate)) {
            return false;
        }
        if (!Objects.equals(this.csvActivity, other.csvActivity)) {
            return false;
        }
        return Objects.equals(this.activityIdCsvMap, other.activityIdCsvMap);
    }
    
}
