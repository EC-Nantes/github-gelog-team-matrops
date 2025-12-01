package org.centrale.hceres.dto.csv;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.centrale.hceres.dto.csv.utils.*;
import org.centrale.hceres.items.*;
import org.centrale.hceres.dto.csv.utils.SupportedCsvTemplate;
import org.centrale.hceres.util.RequestParseException;
import org.centrale.hceres.util.RequestParser;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class CsvPublication extends DependentCsv<Activity, Integer> {
    // important the read field of name id_activity isn't the same
    // id activity in activity.csv
    // to get the id activity use both key:
    // the type of activity and the specific count
    private Integer idCsvPublication;
    private static final int ID_CSV_PUBLICATION_ORDER = 0;

    private String title;
    private static final int TITLE_ORDER = 1;

    private String authors;
    private static final int AUTHORS_ORDER = 2;

    private String source;
    private static final int SOURCE_ORDER = 3;

    private java.sql.Date publicationDate;
    private static final int PUBLICATION_DATE_ORDER = 4;

    private String pmid;
    private static final int PMID_ORDER = 5;
    private BigDecimal impactFactor;
    private static final int IMPACT_FACTOR_ORDER = 6;
    private Boolean clinic;
    private static final int CLINIC_ORDER = 7;
    private Boolean pdc;
    private static final int PDC_ORDER = 8;
    private Boolean colabInter;
    private static final int COLAB_INTER_ORDER = 9;
    private Boolean colabIntraCrti;
    private static final int COLAB_INTRA_CRTI_ORDER = 10;
    private Integer idChoice;
    private static final int ID_CHOICE_ORDER = 11;

    // dependency element
    private CsvActivity csvActivity;
    private Map<Integer, CsvActivity> activityIdCsvMap;

    private GenericCsv<PublicationType, Integer> csvPublicationType;
    private Map<Integer, GenericCsv<PublicationType, Integer>> publicationTypeIdCsvMap;

    public Integer getIdCsvPublication() {
        return idCsvPublication;
    }

    public void setIdCsvPublication(Integer idCsvPublication) {
        this.idCsvPublication = idCsvPublication;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public java.sql.Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(java.sql.Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public BigDecimal getImpactFactor() {
        return impactFactor;
    }

    public void setImpactFactor(BigDecimal impactFactor) {
        this.impactFactor = impactFactor;
    }

    public Boolean getClinic() {
        return clinic;
    }

    public void setClinic(Boolean clinic) {
        this.clinic = clinic;
    }

    public Boolean getPdc() {
        return pdc;
    }

    public void setPdc(Boolean pdc) {
        this.pdc = pdc;
    }

    public Boolean getColabInter() {
        return colabInter;
    }

    public void setColabInter(Boolean colabInter) {
        this.colabInter = colabInter;
    }

    public Boolean getColabIntraCrti() {
        return colabIntraCrti;
    }

    public void setColabIntraCrti(Boolean colabIntraCrti) {
        this.colabIntraCrti = colabIntraCrti;
    }

    public Integer getIdChoice() {
        return idChoice;
    }

    public void setIdChoice(Integer idChoice) {
        this.idChoice = idChoice;
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

    public GenericCsv<PublicationType, Integer> getCsvPublicationType() {
        return csvPublicationType;
    }

    public void setCsvPublicationType(GenericCsv<PublicationType, Integer> csvPublicationType) {
        this.csvPublicationType = csvPublicationType;
    }

    public Map<Integer, GenericCsv<PublicationType, Integer>> getPublicationTypeIdCsvMap() {
        return publicationTypeIdCsvMap;
    }

    public void setPublicationTypeIdCsvMap(Map<Integer, GenericCsv<PublicationType, Integer>> publicationTypeIdCsvMap) {
        this.publicationTypeIdCsvMap = publicationTypeIdCsvMap;
    }

    public CsvPublication(Map<Integer, CsvActivity> activityIdCsvMap, Map<Integer, GenericCsv<PublicationType, Integer>> publicationTypeIdCsvMap) {
        this.activityIdCsvMap = activityIdCsvMap;
        this.publicationTypeIdCsvMap = publicationTypeIdCsvMap;
    }

    @Override
    public void fillCsvDataWithoutDependency(List<?> csvData) throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_PUBLICATION_ORDER,
                        f -> this.setIdCsvPublication(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(TITLE_ORDER,
                        f -> this.setTitle(RequestParser.getAsString(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(AUTHORS_ORDER,
                        f -> this.setAuthors(RequestParser.getAsString(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(SOURCE_ORDER,
                        f -> this.setSource(RequestParser.getAsString(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(PUBLICATION_DATE_ORDER,
                        f -> this.setPublicationDate(RequestParser.getAsDateCsvFormat(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(PMID_ORDER,
                        f -> this.setPmid(RequestParser.getAsString(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(IMPACT_FACTOR_ORDER,
                        f -> this.setImpactFactor(RequestParser.getAsBigDecimalOrDefault(csvData.get(f), BigDecimal.ZERO))),

                () -> CsvParserUtil.wrapCsvParseException(CLINIC_ORDER,
                        f -> this.setClinic(RequestParser.getAsBoolean(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(PDC_ORDER,
                        f -> this.setPdc(RequestParser.getAsBoolean(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(COLAB_INTER_ORDER,
                        f -> this.setColabInter(RequestParser.getAsBoolean(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(COLAB_INTRA_CRTI_ORDER,
                        f -> this.setColabIntraCrti(RequestParser.getAsBoolean(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(ID_CHOICE_ORDER,
                        f -> this.setIdChoice(RequestParser.getAsInteger(csvData.get(f))))
        );
    }

    @Override
    public void initializeDependencies() throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvDependencyException(ID_CSV_PUBLICATION_ORDER,
                        this.getIdCsvPublication(),
                        SupportedCsvTemplate.ACTIVITY,
                        this.activityIdCsvMap.get(this.getIdCsvPublication()),
                        this::setCsvActivity),

                () -> CsvParserUtil.wrapCsvDependencyException(ID_CHOICE_ORDER,
                        this.getIdChoice(),
                        SupportedCsvTemplate.PUBLICATION_TYPE,
                        this.publicationTypeIdCsvMap.get(this.getIdChoice()),
                        this::setCsvPublicationType)
        );
    }

    @Override
    public Activity convertToEntity() {
        Activity activity = this.getCsvActivity().convertToEntity();
        TypeActivity type = new TypeActivity();
        type.setIdTypeActivity(TypeActivityId.PUBLICATION.getId());
        activity.setTypeActivity(type);
        Publication publication = new Publication();
        publication.setTitle(this.getTitle());
        publication.setAuthors(this.getAuthors());
        publication.setSource(this.getSource());
        publication.setPublicationDate(this.getPublicationDate());
        publication.setPmid(this.getPmid());
        publication.setImpactFactor(this.getImpactFactor());
        publication.getPublicationType().setPublicationTypeId(this.getCsvPublicationType().getIdDatabase());
        activity.setResearcherList(Collections.singletonList(new Researcher(this.getCsvActivity().getCsvResearcher().getIdDatabase())));

        activity.setPublication(publication);
        publication.setActivity(activity);
        return activity;
    }

    @Override
    public String getMergingKey() {
        return (this.getCsvActivity().getCsvResearcher().getIdDatabase()
                + "_" + this.getTitle()
                + "_" + this.getAuthors()
                + "_" + this.getSource()
                + "_" + this.getPublicationDate()
                + "_" + this.getPmid()
                + "_" + this.getImpactFactor()
                + "_" + this.getCsvPublicationType().getIdDatabase()).toLowerCase();
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
                + "_" + entity.getPublication().getTitle()
                + "_" + entity.getPublication().getAuthors()
                + "_" + entity.getPublication().getSource()
                + "_" + entity.getPublication().getPublicationDate()
                + "_" + entity.getPublication().getPmid()
                + "_" + entity.getPublication().getImpactFactor()
                + "_" + entity.getPublication().getPublicationTypeId()).toLowerCase();
    }

    @Override
    public void setIdDatabaseFromEntity(Activity entity) {
        this.setIdDatabase(entity.getIdActivity());
    }

    @Override
    public Integer getIdCsv() {
        return this.getIdCsvPublication();
    }
}
