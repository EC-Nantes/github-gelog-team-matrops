package org.centrale.hceres.dto.csv;

import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.centrale.hceres.dto.csv.utils.*;
import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.SeiIndustrialRDContract;
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
public class CsvSeiIndustrialRDContract extends DependentCsv<Activity, Integer> {
    // important the read field of name id_activity isn't the same
    // id activity in activity.csv
    // to get the id activity use both key:
    // the type of activity and the specific count
    private Integer idCsvSeiIndustrialRDContract;
    private static final int ID_CSV_SEI_INDUSTRIAL_RD_CONTRACT_ORDER = 0;
    private java.sql.Date startDate;
    private static final int START_DATE_ORDER = 1;
    private String nameCompanyInvolved;
    private static final int NAME_COMPANY_INVOLVED_ORDER = 2;
    private String projectTitle;
    private static final int PROJECT_TITLE_ORDER = 3;
    private Integer agreementAmount;
    private static final int AGREEMENT_AMOUNT_ORDER = 4;
    private java.sql.Date endDate;
    private static final int END_DATE_ORDER = 5;


    // dependency element
    private CsvActivity csvActivity;
    private Map<Integer, CsvActivity> activityIdCsvMap;

    public CsvSeiIndustrialRDContract(Map<Integer, CsvActivity> activityIdCsvMap) {
        this.activityIdCsvMap = activityIdCsvMap;
    }

    public Integer getIdCsvSeiIndustrialRDContract() {
        return idCsvSeiIndustrialRDContract;
    }

    public void setIdCsvSeiIndustrialRDContract(Integer idCsvSeiIndustrialRDContract) {
        this.idCsvSeiIndustrialRDContract = idCsvSeiIndustrialRDContract;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public String getNameCompanyInvolved() {
        return nameCompanyInvolved;
    }

    public void setNameCompanyInvolved(String nameCompanyInvolved) {
        this.nameCompanyInvolved = nameCompanyInvolved;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public Integer getAgreementAmount() {
        return agreementAmount;
    }

    public void setAgreementAmount(Integer agreementAmount) {
        this.agreementAmount = agreementAmount;
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
                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_SEI_INDUSTRIAL_RD_CONTRACT_ORDER,
                        f -> this.setIdCsvSeiIndustrialRDContract(RequestParser.getAsInteger(csvData.get(f)))),
                () -> CsvParserUtil.wrapCsvParseException(START_DATE_ORDER,
                        f -> this.setStartDate(RequestParser.getAsDateCsvFormat(csvData.get(f)))),
                () -> CsvParserUtil.wrapCsvParseException(NAME_COMPANY_INVOLVED_ORDER,
                        f -> this.setNameCompanyInvolved(RequestParser.getAsString(csvData.get(f)))),
                () -> CsvParserUtil.wrapCsvParseException(PROJECT_TITLE_ORDER,
                        f -> this.setProjectTitle(RequestParser.getAsString(csvData.get(f)))),
                () -> CsvParserUtil.wrapCsvParseException(AGREEMENT_AMOUNT_ORDER,
                        f -> this.setAgreementAmount(RequestParser.getAsInteger(csvData.get(f)))),
                () -> CsvParserUtil.wrapCsvParseException(END_DATE_ORDER,
                        f -> this.setEndDate(RequestParser.getAsDateCsvFormat(csvData.get(f))))
        );
    }

    @Override
    public void initializeDependencies() throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvDependencyException(ID_CSV_SEI_INDUSTRIAL_RD_CONTRACT_ORDER,
                        this.getIdCsvSeiIndustrialRDContract(),
                        SupportedCsvTemplate.ACTIVITY,
                        this.activityIdCsvMap.get(this.getIdCsvSeiIndustrialRDContract()),
                        this::setCsvActivity)
        );
    }

    @Override
    public Activity convertToEntity() {
        Activity activity = this.getCsvActivity().convertToEntity();
        TypeActivity type = new TypeActivity();
        type.setIdTypeActivity(TypeActivityId.SEI_INDUSTRIAL_R_D_CONTRACT.getId());
        activity.setTypeActivity(type);
        SeiIndustrialRDContract seiIndustrialRDContract = new SeiIndustrialRDContract();
        seiIndustrialRDContract.setStartDate(this.getStartDate());
        seiIndustrialRDContract.setNameCompanyInvolved(this.getNameCompanyInvolved());
        seiIndustrialRDContract.setProjectTitle(this.getProjectTitle());
        seiIndustrialRDContract.setAgreementAmount(this.getAgreementAmount());
        seiIndustrialRDContract.setEndDate(this.getEndDate());

        activity.setSeiIndustrialRDContract(seiIndustrialRDContract);
        seiIndustrialRDContract.setActivity(activity);
        return activity;
    }

    @Override
    public String getMergingKey() {
        return (this.getCsvActivity().getCsvResearcher().getIdDatabase()
                + "_" + this.getStartDate()
                + "_" + this.getNameCompanyInvolved()
                + "_" + this.getProjectTitle()
                + "_" + this.getAgreementAmount()
                + "_" + this.getEndDate()).toLowerCase();
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
                + "_" + entity.getSeiIndustrialRDContract().getStartDate()
                + "_" + entity.getSeiIndustrialRDContract().getNameCompanyInvolved()
                + "_" + entity.getSeiIndustrialRDContract().getProjectTitle()
                + "_" + entity.getSeiIndustrialRDContract().getAgreementAmount()
                + "_" + entity.getSeiIndustrialRDContract().getEndDate()).toLowerCase();
    }

    @Override
    public void setIdDatabaseFromEntity(Activity entity) {
        this.setIdDatabase(entity.getIdActivity());
    }

    @Override
    public Integer getIdCsv() {
        return this.getIdCsvSeiIndustrialRDContract();
    }
}
