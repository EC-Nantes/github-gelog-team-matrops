package org.centrale.hceres.dto.csv;

import java.sql.Date;
import java.util.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.centrale.hceres.dto.csv.utils.*;
import org.centrale.hceres.items.Activity;
import org.centrale.hceres.items.Book;
import org.centrale.hceres.items.TypeActivityId;
import org.centrale.hceres.dto.csv.utils.LanguageCreatorCache;
import org.centrale.hceres.dto.csv.utils.SupportedCsvTemplate;
import org.centrale.hceres.util.RequestParser;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.centrale.hceres.items.Researcher;

@EqualsAndHashCode(callSuper = true)
@Data
public class CsvBook extends DependentCsv<Activity, Integer> {
    // important the read field of name id_activity isn't the same
    // id activity in activity.csv
    // to get the id activity use both key:
    // the type of activity and the specific count
    private Integer idCsvBook;
    private static final int ID_CSV_BOOK_ORDER = 0;

    private java.sql.Date publicationDate;
    private static final int PUBLICATION_DATE_ORDER = 1;
    private String title;
    private static final int TITLE_ORDER = 2;
    private String editor;
    private static final int EDITOR_ORDER = 3;
    private String authors;
    private static final int AUTHORS_ORDER = 4;
    private String language;
    private static final int LANGUAGE_ORDER = 5;

    public Integer getIdCsvBook() {
        return idCsvBook;
    }

    public void setIdCsvBook(Integer idCsvBook) {
        this.idCsvBook = idCsvBook;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public LanguageCreatorCache getLanguageCreatorCache() {
        return languageCreatorCache;
    }

    public void setLanguageCreatorCache(LanguageCreatorCache languageCreatorCache) {
        this.languageCreatorCache = languageCreatorCache;
    }
    
    


    // dependency element
    private CsvActivity csvActivity;
    private Map<Integer, CsvActivity> activityIdCsvMap;
    private LanguageCreatorCache languageCreatorCache;

    public CsvBook(Map<Integer, CsvActivity> activityIdCsvMap, LanguageCreatorCache languageCreatorCache) {
        this.activityIdCsvMap = activityIdCsvMap;
        this.languageCreatorCache = languageCreatorCache;
    }

    @Override
    public void fillCsvDataWithoutDependency(List<?> csvData) throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvParseException(ID_CSV_BOOK_ORDER,
                        f -> this.setIdCsvBook(RequestParser.getAsInteger(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(PUBLICATION_DATE_ORDER,
                        f -> this.setPublicationDate(RequestParser.getAsDateCsvFormat(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(TITLE_ORDER,
                        f -> this.setTitle(RequestParser.getAsString(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(EDITOR_ORDER,
                        f -> this.setEditor(RequestParser.getAsString(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(AUTHORS_ORDER,
                        f -> this.setAuthors(RequestParser.getAsString(csvData.get(f)))),

                () -> CsvParserUtil.wrapCsvParseException(LANGUAGE_ORDER,
                        f -> this.setLanguage(RequestParser.getAsString(csvData.get(f))))
        );
    }

    @Override
    public void initializeDependencies() throws CsvAllFieldExceptions {
        CsvParserUtil.wrapCsvAllFieldExceptions(
                () -> CsvParserUtil.wrapCsvDependencyException(ID_CSV_BOOK_ORDER,
                        this.getIdCsvBook(),
                        SupportedCsvTemplate.ACTIVITY,
                        this.activityIdCsvMap.get(this.getIdCsvBook()),
                        this::setCsvActivity)
        );
    }

    @Override
    public Activity convertToEntity() {
        Activity activity = this.csvActivity.convertToEntity();
        activity.getTypeActivity().setIdTypeActivity(TypeActivityId.BOOK.getId());

        Book book = new Book();
        book.setPublicationDate(this.getPublicationDate());
        book.setTitle(this.getTitle());
        book.setEditor(this.getEditor());
        book.setAuthors(this.getAuthors());
        book.getLanguage().setLanguageName(this.languageCreatorCache.getLanguageName());
        book.getLanguage().setLanguageId(this.languageCreatorCache.getLanguageId());
        book.setActivity(activity);

        activity.setBook(book);
        return activity;
    }


    @Override
    public String getMergingKey() {
        return (this.getCsvActivity().getCsvResearcher().getIdDatabase()
                + "_" + this.getPublicationDate()
                + "_" + this.getTitle()
                + "_" + this.getEditor()
                + "_" + this.getAuthors()
                + "_" + this.getLanguage()).toLowerCase();
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
                + "_" + entity.getBook().getPublicationDate()
                + "_" + entity.getBook().getTitle()
                + "_" + entity.getBook().getEditor()
                + "_" + entity.getBook().getAuthors()
                + "_" + entity.getBook().getLanguage().getLanguageName()).toLowerCase();
    }

    @Override
    public void setIdDatabaseFromEntity(Activity entity) {
        this.setIdDatabase(entity.getIdActivity());
    }

    @Override
    public Integer getIdCsv() {
        return this.getIdCsvBook();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idCsvBook);
        hash = 67 * hash + Objects.hashCode(this.publicationDate);
        hash = 67 * hash + Objects.hashCode(this.title);
        hash = 67 * hash + Objects.hashCode(this.editor);
        hash = 67 * hash + Objects.hashCode(this.authors);
        hash = 67 * hash + Objects.hashCode(this.language);
        hash = 67 * hash + Objects.hashCode(this.csvActivity);
        hash = 67 * hash + Objects.hashCode(this.activityIdCsvMap);
        hash = 67 * hash + Objects.hashCode(this.languageCreatorCache);
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
        final CsvBook other = (CsvBook) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.editor, other.editor)) {
            return false;
        }
        if (!Objects.equals(this.authors, other.authors)) {
            return false;
        }
        if (!Objects.equals(this.language, other.language)) {
            return false;
        }
        if (!Objects.equals(this.idCsvBook, other.idCsvBook)) {
            return false;
        }
        if (!Objects.equals(this.publicationDate, other.publicationDate)) {
            return false;
        }
        if (!Objects.equals(this.csvActivity, other.csvActivity)) {
            return false;
        }
        if (!Objects.equals(this.activityIdCsvMap, other.activityIdCsvMap)) {
            return false;
        }
        return Objects.equals(this.languageCreatorCache, other.languageCreatorCache);
    }
    
}
