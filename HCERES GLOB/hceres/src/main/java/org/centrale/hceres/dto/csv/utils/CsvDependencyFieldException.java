package org.centrale.hceres.dto.csv.utils;

import lombok.Getter;
import org.centrale.hceres.service.csv.util.SupportedCsvTemplate;

@Getter
public class CsvDependencyFieldException extends CsvFieldException {
    private final SupportedCsvTemplate dependency;

    public CsvDependencyFieldException(String message, int fieldNumber, SupportedCsvTemplate dependency) {
        super(message, fieldNumber);
        this.dependency = dependency;
    }
}
