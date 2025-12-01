package org.centrale.hceres.dto.csv;

import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.centrale.hceres.dto.csv.utils.*;
import org.centrale.hceres.items.*;
import org.centrale.hceres.dto.csv.utils.SupportedCsvTemplate;
import org.centrale.hceres.util.RequestParseException;
import org.centrale.hceres.util.RequestParser;

import java.util.Date;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class CsvToolProduct extends DependentCsv<Activity, Integer> {

    // important the read field of name id_activity isn't the same
    // id activity in activity.csv
    // to get the id activity use both key:
    // the type of activity and the specific count
    private Integer idCsvToolProduct;
    private static final int ID_CSV_TOOL_PRODUCT_ORDER = 0;
    private String toolProductName;
    private static final int TOOL_PRODUCT_NAME_ORDER = 1;
    private java.sql.Date toolProductCreation;
    private static final int TOOL_PRODUCT_CREATION_ORDER = 2;
    private String toolProductAuthors;
    private static final int TOOL_PRODUCT_AUTHORS_ORDER = 3;
    private String toolProductDescription;
    private static final int TOOL_PRODUCT_DESCRIPTION_ORDER = 4;

    // dependency element
    private CsvActivity csvActivity;
    private Map<Integer, CsvActivity> activityIdCsvMap;


    private ToolProductType ToolProductType;

    public Integer getIdCsvToolProduct() {
        return idCsvToolProduct;
    }

    public void setIdCsvToolProduct(Integer idCsvToolProduct) {
        this.idCsvToolProduct = idCsvToolProduct;
    }

    public String getToolProductName() {
        return toolProductName;
    }

    public void setToolProductName(String toolProductName) {
        this.toolProductName = toolProductName;
    }

    public java.sql.Date getToolProductCreation() {
        return toolProductCreation;
    }

    public void setToolProductCreation(java.sql.Date toolProductCreation) {
        this.toolProductCreation = toolProductCreation;
    }

    public String getToolProductAuthors() {
        return toolProductAuthors;
    }

    public void setToolProductAuthors(String toolProductAuthors) {
        this.toolProductAuthors = toolProductAuthors;
    }

    public String getToolProductDescription() {
        return toolProductDescription;
    }

    public void setToolProductDescription(String toolProductDescription) {
        this.toolProductDescription = toolProductDescription;
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

    public ToolProductType getToolProductType() {
        return ToolProductType;
    }

    public void setToolProductType(ToolProductType ToolProductType) {
        this.ToolProductType = ToolProductType;
    }
    
    public CsvToolProduct(Map<Integer, CsvActivity> activityIdCsvMap,
                      Integer toolProductTypeId) {
        this.activityIdCsvMap = activityIdCsvMap;
        this.ToolProductType.setToolProductTypeId(toolProductTypeId);
    }

   @Override
    public void fillCsvDataWithoutDependency(List<?> csvData) throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_TOOL_PRODUCT_ORDER,
                        f -> this.setIdCsvToolProduct(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(TOOL_PRODUCT_NAME_ORDER,
                        f -> this.setToolProductName(RequestParser.getAsString(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(TOOL_PRODUCT_CREATION_ORDER,
                        f -> {
                            java.util.Date parsedDate = RequestParser.getAsDateCsvFormat(csvData.get(f));
                            this.setToolProductCreation(parsedDate != null ? new java.sql.Date(parsedDate.getTime()) : null);
                        }),

                () -> CsvParserUtil.wrapCsvParseException(TOOL_PRODUCT_AUTHORS_ORDER,
                        f -> this.setToolProductAuthors(RequestParser.getAsString(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(TOOL_PRODUCT_DESCRIPTION_ORDER,
                        f -> this.setToolProductDescription(RequestParser.getAsString(csvData.get(f))))
        );
    }

    @Override
    public void initializeDependencies() throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvDependencyException(
                        ID_CSV_TOOL_PRODUCT_ORDER,
                        this.getIdCsvToolProduct(),
                        SupportedCsvTemplate.ACTIVITY,
                        this.activityIdCsvMap.get(this.getIdCsvToolProduct()),
                        this::setCsvActivity
                )
        );
    }

    @Override
    public Activity convertToEntity() {
        Activity activity = this.getCsvActivity().convertToEntity();

        // Assigner correctement le ToolProductType
        ToolProduct toolProduct = new ToolProduct();
        toolProduct.setToolProductType(this.ToolProductType);
        toolProduct.setToolProductName(this.getToolProductName());
        toolProduct.setToolProductCreation(this.getToolProductCreation());
        toolProduct.setToolProductAuthors(this.getToolProductAuthors());
        toolProduct.setToolProductDescription(this.getToolProductDescription());

        activity.setToolProduct(toolProduct);
        toolProduct.setActivity(activity);

        // Assigner le type d'activité si nécessaire
        if (this.ToolProductType != null) {
            activity.getTypeActivity().setIdTypeActivity((this.ToolProductType.getToolProductTypeId()));
        }

        return activity;
    }

    @Override
    public String getMergingKey() {
        return (this.getCsvActivity().getCsvResearcher().getIdDatabase()
                + "_" + this.getToolProductName()
                + "_" + this.getToolProductCreation()
                + "_" + this.getToolProductAuthors()
                + "_" + this.getToolProductDescription()).toLowerCase();
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
                + "_" + entity.getToolProduct().getToolProductName()
                + "_" + entity.getToolProduct().getToolProductCreation()
                + "_" + entity.getToolProduct().getToolProductAuthors()
                + "_" + entity.getToolProduct().getToolProductDescription()).toLowerCase();
    }

    @Override
    public void setIdDatabaseFromEntity(Activity entity) {
        this.setIdDatabase(entity.getIdActivity());
    }

    @Override
    public Integer getIdCsv() {
        return this.getIdCsvToolProduct();
    }
    }
