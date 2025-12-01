package org.centrale.hceres.dto.csv;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.centrale.hceres.dto.csv.utils.*;
import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.Researcher;
import org.centrale.hceres.items.TypeActivity;
import org.centrale.hceres.dto.csv.utils.SupportedCsvTemplate;
import org.centrale.hceres.util.RequestParser;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class is used only for mapping between activityTemplate.csv and Researcher.csv
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CsvActivity extends DependentCsv<Activity, Integer> {
    // id_type;id_activity;id_researcher;specific_activity_count;activity_name_type
    private Integer idCsvTypeActivity;
    private static final int ID_CSV_TYPE_ACTIVITY_ORDER = 0;
    private GenericCsv<TypeActivity, Integer> csvTypeActivity;
    private final Map<Integer, GenericCsv<TypeActivity, Integer>> typeActivityIdCsvMap;

    private Integer idCsv;
    private static final int ID_CSV_ORDER = 1;

    private Integer idCsvResearcher;
    private static final int ID_CSV_RESEARCHER_ORDER = 2;
    private GenericCsv<Researcher, Integer> csvResearcher;
    private final Map<Integer, GenericCsv<Researcher, Integer>> researcherIdCsvMap;

    // specific count along with idCsvTypeActivity give the id
    // of activity
    private Integer specificActivityCount;
    private static final int SPECIFIC_ACTIVITY_COUNT_ORDER = 3;

    // ignored
    private String activityNameType;
    private static final int ACTIVITY_NAME_TYPE_ORDER = 4;

    public CsvActivity(Map<Integer, GenericCsv<TypeActivity, Integer>> typeActivityIdCsvMap,
                       Map<Integer, GenericCsv<Researcher, Integer>> researcherIdCsvMap) {
        this.typeActivityIdCsvMap = typeActivityIdCsvMap;
        this.researcherIdCsvMap = researcherIdCsvMap;
    }

    public Integer getIdCsvTypeActivity() {
        return idCsvTypeActivity;
    }

    public void setIdCsvTypeActivity(Integer idCsvTypeActivity) {
        this.idCsvTypeActivity = idCsvTypeActivity;
    }

    public GenericCsv<TypeActivity, Integer> getCsvTypeActivity() {
        return csvTypeActivity;
    }

    public void setCsvTypeActivity(GenericCsv<TypeActivity, Integer> csvTypeActivity) {
        this.csvTypeActivity = csvTypeActivity;
    }

    public Integer getIdCsvResearcher() {
        return idCsvResearcher;
    }

    public void setIdCsvResearcher(Integer idCsvResearcher) {
        this.idCsvResearcher = idCsvResearcher;
    }

    public GenericCsv<Researcher, Integer> getCsvResearcher() {
        return csvResearcher;
    }

    public void setCsvResearcher(GenericCsv<Researcher, Integer> csvResearcher) {
        this.csvResearcher = csvResearcher;
    }

    public Integer getSpecificActivityCount() {
        return specificActivityCount;
    }

    public void setSpecificActivityCount(Integer specificActivityCount) {
        this.specificActivityCount = specificActivityCount;
    }

    public String getActivityNameType() {
        return activityNameType;
    }

    public void setActivityNameType(String activityNameType) {
        this.activityNameType = activityNameType;
    }

    public static int getID_CSV_TYPE_ACTIVITY_ORDER() {
        return ID_CSV_TYPE_ACTIVITY_ORDER;
    }

    public Map<Integer, GenericCsv<TypeActivity, Integer>> getTypeActivityIdCsvMap() {
        return typeActivityIdCsvMap;
    }

    public static int getID_CSV_ORDER() {
        return ID_CSV_ORDER;
    }

    public static int getID_CSV_RESEARCHER_ORDER() {
        return ID_CSV_RESEARCHER_ORDER;
    }

    public Map<Integer, GenericCsv<Researcher, Integer>> getResearcherIdCsvMap() {
        return researcherIdCsvMap;
    }

    public static int getSPECIFIC_ACTIVITY_COUNT_ORDER() {
        return SPECIFIC_ACTIVITY_COUNT_ORDER;
    }

    public static int getACTIVITY_NAME_TYPE_ORDER() {
        return ACTIVITY_NAME_TYPE_ORDER;
    }

    public static String getIMPLEMENTATION_ERROR() {
        return IMPLEMENTATION_ERROR;
    }

    public void setIdCsv(Integer idCsv) {
        this.idCsv = idCsv;
    }
    
    @Override
    public void fillCsvDataWithoutDependency(List<?> csvData) throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_TYPE_ACTIVITY_ORDER,
                        f -> this.setIdCsvTypeActivity(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_ORDER,
                        f -> this.setIdCsv(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_RESEARCHER_ORDER,
                        f -> this.setIdCsvResearcher(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(SPECIFIC_ACTIVITY_COUNT_ORDER,
                        f -> this.setSpecificActivityCount(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(ACTIVITY_NAME_TYPE_ORDER,
                        f -> this.setActivityNameType(RequestParser.getAsString(csvData.get(f))))
        );
    }

    @Override
    public void initializeDependencies() throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvDependencyException(ID_CSV_TYPE_ACTIVITY_ORDER,
                        this.getIdCsvTypeActivity(),
                        SupportedCsvTemplate.TYPE_ACTIVITY,
                        this.typeActivityIdCsvMap.get(this.getIdCsvTypeActivity()),
                        this::setCsvTypeActivity),

                () -> CsvParserUtil.wrapCsvDependencyException(ID_CSV_RESEARCHER_ORDER,
                        this.getIdCsvResearcher(),
                        SupportedCsvTemplate.RESEARCHER,
                        this.researcherIdCsvMap.get(this.getIdCsvResearcher()),
                        this::setCsvResearcher)
        );
    }

    private static final String IMPLEMENTATION_ERROR = "Should not be called, convert Specific Activity instead";

    @Override
    public Activity convertToEntity() {
        Activity activity = new Activity();
        activity.setResearcherList(Collections.singletonList(new Researcher(this.csvResearcher.getIdDatabase())));
        return activity;
    }

    @Override
    public String getMergingKey() {
        throw new UnsupportedOperationException(IMPLEMENTATION_ERROR);
    }

    @Override
    public String getMergingKey(Activity entity) {
        throw new UnsupportedOperationException(IMPLEMENTATION_ERROR);
    }

    @Override
    public void setIdDatabaseFromEntity(Activity entity) {
        this.setIdDatabase(entity.getIdActivity());
    }

    @Override
    public Integer getIdCsv() {
        return this.idCsv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.idCsvTypeActivity);
        hash = 29 * hash + Objects.hashCode(this.csvTypeActivity);
        hash = 29 * hash + Objects.hashCode(this.typeActivityIdCsvMap);
        hash = 29 * hash + Objects.hashCode(this.idCsv);
        hash = 29 * hash + Objects.hashCode(this.idCsvResearcher);
        hash = 29 * hash + Objects.hashCode(this.csvResearcher);
        hash = 29 * hash + Objects.hashCode(this.researcherIdCsvMap);
        hash = 29 * hash + Objects.hashCode(this.specificActivityCount);
        hash = 29 * hash + Objects.hashCode(this.activityNameType);
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
        final CsvActivity other = (CsvActivity) obj;
        if (!Objects.equals(this.activityNameType, other.activityNameType)) {
            return false;
        }
        if (!Objects.equals(this.idCsvTypeActivity, other.idCsvTypeActivity)) {
            return false;
        }
        if (!Objects.equals(this.csvTypeActivity, other.csvTypeActivity)) {
            return false;
        }
        if (!Objects.equals(this.typeActivityIdCsvMap, other.typeActivityIdCsvMap)) {
            return false;
        }
        if (!Objects.equals(this.idCsv, other.idCsv)) {
            return false;
        }
        if (!Objects.equals(this.idCsvResearcher, other.idCsvResearcher)) {
            return false;
        }
        if (!Objects.equals(this.csvResearcher, other.csvResearcher)) {
            return false;
        }
        if (!Objects.equals(this.researcherIdCsvMap, other.researcherIdCsvMap)) {
            return false;
        }
        return Objects.equals(this.specificActivityCount, other.specificActivityCount);
    }
    
}
