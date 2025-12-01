package org.centrale.hceres.dto.csv;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.centrale.hceres.dto.csv.utils.*;
import org.centrale.hceres.items.*;
import org.centrale.hceres.dto.csv.utils.JournalCreatorCache;
import org.centrale.hceres.dto.csv.utils.SupportedCsvTemplate;
import org.centrale.hceres.util.RequestParser;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class CsvEditorialActivity extends DependentCsv<Activity, Integer> {
//    id_activity;start_date;end_date;name_journal;impact_factor_journal;id_function

    // important the read field of name id_activity isn't the same
    // id activity in activity.csv
    // to get the id activity use both key:
    // the type of activity and the specific count
    private Integer idCsvEditorialActivity;
    private static final int ID_CSV_EDITORIAL_ACTIVITY_ORDER = 0;

    private java.sql.Date startDate;
    private static final int START_DATE_ORDER = 1;
    private java.sql.Date endDate;
    private static final int END_DATE_ORDER = 2;
    private String nameJournal;
    private static final int NAME_JOURNAL_ORDER = 3;
    private BigDecimal impactFactorJournal;
    private static final int IMPACT_FACTOR_JOURNAL_ORDER = 4;

    private Integer idFunction;
    private static final int ID_FUNCTION_ORDER = 5;

    // dependency element
    private CsvActivity csvActivity;
    private Map<Integer, CsvActivity> activityIdCsvMap;

    private JournalCreatorCache journalCreatorCache;

    public CsvEditorialActivity(Map<Integer, CsvActivity> activityIdCsvMap, JournalCreatorCache journalCreatorCache) {
        this.activityIdCsvMap = activityIdCsvMap;
        this.journalCreatorCache = journalCreatorCache;
    }

    public Integer getIdCsvEditorialActivity() {
        return idCsvEditorialActivity;
    }

    public void setIdCsvEditorialActivity(Integer idCsvEditorialActivity) {
        this.idCsvEditorialActivity = idCsvEditorialActivity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNameJournal() {
        return nameJournal;
    }

    public void setNameJournal(String nameJournal) {
        this.nameJournal = nameJournal;
    }

    public BigDecimal getImpactFactorJournal() {
        return impactFactorJournal;
    }

    public void setImpactFactorJournal(BigDecimal impactFactorJournal) {
        this.impactFactorJournal = impactFactorJournal;
    }

    public Integer getIdFunction() {
        return idFunction;
    }

    public void setIdFunction(Integer idFunction) {
        this.idFunction = idFunction;
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

    public JournalCreatorCache getJournalCreatorCache() {
        return journalCreatorCache;
    }

    public void setJournalCreatorCache(JournalCreatorCache journalCreatorCache) {
        this.journalCreatorCache = journalCreatorCache;
    }

    @Override
    public void fillCsvDataWithoutDependency(List<?> csvData) throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_EDITORIAL_ACTIVITY_ORDER,
                        f -> this.setIdCsvEditorialActivity(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(START_DATE_ORDER,
                        f -> this.setStartDate(RequestParser.getAsDateCsvFormat(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(END_DATE_ORDER,
                        f -> this.setEndDate(RequestParser.getAsDateCsvFormat(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(NAME_JOURNAL_ORDER,
                        f -> this.setNameJournal(RequestParser.getAsString(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(IMPACT_FACTOR_JOURNAL_ORDER,
                        f -> this.setImpactFactorJournal(RequestParser.getAsBigDecimal(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(ID_FUNCTION_ORDER,
                        f -> this.setIdFunction(RequestParser.getAsInteger(csvData.get(f))))
        );
    }

    @Override
    public void initializeDependencies() throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvDependencyException(ID_CSV_EDITORIAL_ACTIVITY_ORDER,
                        this.getIdCsvEditorialActivity(),
                        SupportedCsvTemplate.ACTIVITY,
                        this.activityIdCsvMap.get(this.getIdCsvEditorialActivity()),
                        this::setCsvActivity)
        );
    }
    @Override
    public Activity convertToEntity() {
        Activity activity = this.getCsvActivity().convertToEntity();
        TypeActivity type = new TypeActivity();
        type.setIdTypeActivity(TypeActivityId.EDITORIAL_ACTIVITY.getId());
        activity.setTypeActivity(type);
        EditorialActivity editorialActivity = new EditorialActivity();
        editorialActivity.setStartDate(this.getStartDate());
        editorialActivity.setEndDate(this.getEndDate());
        editorialActivity.setImpactFactor(this.getImpactFactorJournal());

        Journal journal = this.getJournalCreatorCache();
        editorialActivity.setJournal(journal);
        editorialActivity.getJournal().setJournalId(journal.getJournalId());

        // using direct relation to existing function with same id in database
        editorialActivity.getFunctionEditorialActivity().setFunctionEditorialActivityId(this.getIdFunction());

        activity.setEditorialActivity(editorialActivity);
        editorialActivity.setActivity(activity);
        return activity;
    }

    @Override
    public String getMergingKey() {
        return (this.getCsvActivity().getCsvResearcher().getIdDatabase()
                + "_" + this.getStartDate()
                + "_" + this.getEndDate()
                + "_" + this.getNameJournal()
                + "_" + this.getImpactFactorJournal()
                + "_" + this.getIdFunction()).toLowerCase();
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
                + "_" + entity.getEditorialActivity().getStartDate()
                + "_" + entity.getEditorialActivity().getEndDate()
                + "_" + entity.getEditorialActivity().getJournal().getJournalName()
                + "_" + entity.getEditorialActivity().getImpactFactor()
                + "_" + entity.getEditorialActivity().getFunctionEditorialActivityId()).toLowerCase();
    }

    @Override
    public void setIdDatabaseFromEntity(Activity entity) {
        this.setIdDatabase(entity.getIdActivity());
    }

    @Override
    public Integer getIdCsv() {
        return this.getIdCsvEditorialActivity();
    }
}
