package org.centrale.hceres.dto.csv;

import java.sql.Date;
import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.centrale.hceres.dto.csv.utils.*;
import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.InvitedSeminar;
import org.centrale.hceres.items.TypeActivityId;
import org.centrale.hceres.dto.csv.utils.SupportedCsvTemplate;
import org.centrale.hceres.util.RequestParser;

import java.util.List;
import java.util.Map;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TypeActivity;

@EqualsAndHashCode(callSuper = true)
@Data
public class CsvInvitedSeminar extends DependentCsv<Activity, Integer> {
    // important the read field of name id_activity isn't the same
    // id activity in activity.csv
    // to get the id activity use both key:
    // the type of activity and the specific count
    private Integer idCsvInvitedSeminar;
    private static final int ID_CSV_INVITED_SEMINAR_ORDER = 0;
    private java.sql.Date date;
    private static final int DATE_ORDER = 1;
    private String titleSeminar;
    private static final int TITLE_SEMINAR_ORDER = 2;
    private String location;
    private static final int LOCATION_ORDER = 3;
    private String invitedBy;
    private static final int INVITED_BY_ORDER = 4;

    // dependency element
    private CsvActivity csvActivity;
    private Map<Integer, CsvActivity> activityIdCsvMap;

    public CsvInvitedSeminar(Map<Integer, CsvActivity> activityIdCsvMap) {
        this.activityIdCsvMap = activityIdCsvMap;
    }

    public Integer getIdCsvInvitedSeminar() {
        return idCsvInvitedSeminar;
    }

    public void setIdCsvInvitedSeminar(Integer idCsvInvitedSeminar) {
        this.idCsvInvitedSeminar = idCsvInvitedSeminar;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitleSeminar() {
        return titleSeminar;
    }

    public void setTitleSeminar(String titleSeminar) {
        this.titleSeminar = titleSeminar;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(String invitedBy) {
        this.invitedBy = invitedBy;
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
                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_INVITED_SEMINAR_ORDER,
                        f -> this.setIdCsvInvitedSeminar(RequestParser.getAsInteger(csvData.get(f)))),
                () -> CsvParserUtil.wrapCsvParseException(DATE_ORDER,
                        f -> this.setDate(RequestParser.getAsDateCsvFormat(csvData.get(f)))),
                () -> CsvParserUtil.wrapCsvParseException(TITLE_SEMINAR_ORDER,
                        f -> this.setTitleSeminar(RequestParser.getAsString(csvData.get(f)))),
                () -> CsvParserUtil.wrapCsvParseException(LOCATION_ORDER,
                        f -> this.setLocation(RequestParser.getAsString(csvData.get(f)))),
                () -> CsvParserUtil.wrapCsvParseException(INVITED_BY_ORDER,
                        f -> this.setInvitedBy(RequestParser.getAsString(csvData.get(f))))
        );
    }

    @Override
    public void initializeDependencies() throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvDependencyException(ID_CSV_INVITED_SEMINAR_ORDER,
                        this.getIdCsvInvitedSeminar(),
                        SupportedCsvTemplate.ACTIVITY,
                        this.activityIdCsvMap.get(this.getIdCsvInvitedSeminar()),
                        this::setCsvActivity)
        );
    }

    @Override
    public Activity convertToEntity() {
        Activity activity = this.getCsvActivity().convertToEntity();
        TypeActivity type = new TypeActivity();
        type.setIdTypeActivity(TypeActivityId.INVITED_SEMINAR.getId());
        activity.setTypeActivity(type);
        InvitedSeminar invitedSeminar = new InvitedSeminar();
        invitedSeminar.setDate(this.getDate());
        invitedSeminar.setTitleSeminar(this.getTitleSeminar());
        invitedSeminar.setLocation(this.getLocation());
        invitedSeminar.setInvitedBy(this.getInvitedBy());
        invitedSeminar.setActivity(activity);
        activity.setInvitedSeminar(invitedSeminar);
        return activity;
    }

    @Override
    public String getMergingKey() {
        return (this.getCsvActivity().getCsvResearcher().getIdDatabase()
                + "_" + this.getDate()
                + "_" + this.getTitleSeminar()
                + "_" + this.getLocation()
                + "_" + this.getInvitedBy()).toLowerCase();
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
                + "_" + entity.getInvitedSeminar().getDate()
                + "_" + entity.getInvitedSeminar().getTitleSeminar()
                + "_" + entity.getInvitedSeminar().getLocation()
                + "_" + entity.getInvitedSeminar().getInvitedBy()).toLowerCase();
    }

    @Override
    public void setIdDatabaseFromEntity(Activity entity) {
        this.setIdDatabase(entity.getIdActivity());
    }

    @Override
    public Integer getIdCsv() {
        return this.getIdCsvInvitedSeminar();
    }
}
