package org.centrale.hceres.dto.csv;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.centrale.hceres.dto.csv.utils.*;
import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.Journal;
import org.centrale.hceres.items.ReviewArticle;
import org.centrale.hceres.items.TypeActivityId;
import org.centrale.hceres.dto.csv.utils.JournalCreatorCache;
import org.centrale.hceres.dto.csv.utils.SupportedCsvTemplate;
import org.centrale.hceres.util.RequestParseException;
import org.centrale.hceres.util.RequestParser;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TypeActivity;

@EqualsAndHashCode(callSuper = true)
@Data
public class CsvReviewArticle extends DependentCsv<Activity, Integer> {
    // important the read field of name id_activity isn't the same
    // id activity in activity.csv
    // to get the id activity use both key:
    // the type of activity and the specific count
    private Integer idCsvReviewArticle;
    private static final int ID_CSV_REVIEW_ARTICLE_ORDER = 0;
    private Integer year;
    private static final int YEAR_ORDER = 1;
    private String nameJournal;
    private static final int NAME_JOURNAL_ORDER = 2;
    private Integer nbReviewedArticles;
    private static final int NB_REVIEWED_ARTICLES_ORDER = 3;
    private BigDecimal impactFactorJournal;
    private static final int IMPACT_FACTOR_JOURNAL_ORDER = 4;


    // dependency element
    private CsvActivity csvActivity;
    private Map<Integer, CsvActivity> activityIdCsvMap;

    private JournalCreatorCache journalCreatorCache;

    public Integer getIdCsvReviewArticle() {
        return idCsvReviewArticle;
    }

    public void setIdCsvReviewArticle(Integer idCsvReviewArticle) {
        this.idCsvReviewArticle = idCsvReviewArticle;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getNameJournal() {
        return nameJournal;
    }

    public void setNameJournal(String nameJournal) {
        this.nameJournal = nameJournal;
    }

    public Integer getNbReviewedArticles() {
        return nbReviewedArticles;
    }

    public void setNbReviewedArticles(Integer nbReviewedArticles) {
        this.nbReviewedArticles = nbReviewedArticles;
    }

    public BigDecimal getImpactFactorJournal() {
        return impactFactorJournal;
    }

    public void setImpactFactorJournal(BigDecimal impactFactorJournal) {
        this.impactFactorJournal = impactFactorJournal;
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


    public CsvReviewArticle(Map<Integer, CsvActivity> activityIdCsvMap, JournalCreatorCache journalCreatorCache) {
        this.activityIdCsvMap = activityIdCsvMap;
        this.journalCreatorCache = journalCreatorCache;
    }

    @Override
    public void fillCsvDataWithoutDependency(List<?> csvData) throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_REVIEW_ARTICLE_ORDER,
                        f -> this.setIdCsvReviewArticle(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(YEAR_ORDER,
                        f -> this.setYear(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(NAME_JOURNAL_ORDER,
                        f -> this.setNameJournal(RequestParser.getAsString(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(NB_REVIEWED_ARTICLES_ORDER,
                        f -> this.setNbReviewedArticles(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(IMPACT_FACTOR_JOURNAL_ORDER,
                        f -> this.setImpactFactorJournal(RequestParser.getAsBigDecimal(csvData.get(f))))
        );
    }

    @Override
    public void initializeDependencies() throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvDependencyException(ID_CSV_REVIEW_ARTICLE_ORDER,
                        this.getIdCsvReviewArticle(),
                        SupportedCsvTemplate.ACTIVITY,
                        this.activityIdCsvMap.get(this.getIdCsvReviewArticle()),
                        this::setCsvActivity)
        );
    }

    @Override
    public Activity convertToEntity() {
        Activity activity = this.getCsvActivity().convertToEntity();
        TypeActivity type = new TypeActivity();
        type.setIdTypeActivity(TypeActivityId.REVIEWING_JOURNAL_ARTICLES.getId());
        activity.setTypeActivity(type);
        ReviewArticle reviewArticle = new ReviewArticle();
        reviewArticle.setYear(this.getYear());
        reviewArticle.setImpactFactor(this.getImpactFactorJournal());
        reviewArticle.setNbReviewedArticles(this.getNbReviewedArticles());
        Journal journal = this.journalCreatorCache;
        reviewArticle.getJournal().setJournalId(journal.getJournalId());
        reviewArticle.setJournal(journal);
        activity.setReviewArticle(reviewArticle);
        reviewArticle.setActivity(activity);
        return activity;
    }

    @Override
    public String getMergingKey() {
        return (this.getCsvActivity().getCsvResearcher().getIdDatabase()
                + "_" + this.getYear()
                + "_" + this.getNameJournal()
                + "_" + this.getNbReviewedArticles()
                + "_" + this.getImpactFactorJournal()).toLowerCase();
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
                + "_" + entity.getReviewArticle().getYear()
                + "_" + entity.getReviewArticle().getJournal().getJournalName()
                + "_" + entity.getReviewArticle().getNbReviewedArticles()
                + "_" + entity.getReviewArticle().getImpactFactor()).toLowerCase();
    }

    @Override
    public void setIdDatabaseFromEntity(Activity entity) {
        this.setIdDatabase(entity.getIdActivity());
    }

    @Override
    public Integer getIdCsv() {
        return this.getIdCsvReviewArticle();
    }
}
