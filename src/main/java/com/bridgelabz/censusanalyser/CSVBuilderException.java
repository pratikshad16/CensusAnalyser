package com.bridgelabz.censusanalyser;

public class CSVBuilderException extends Exception {
     enum ExceptionType {
        CENSUS_FILE_PROBLEM, FILE_TYPE_OR_DELIMITER_OR_HEADER_PROBLEM
    }

    ExceptionType type;

    public CSVBuilderException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}

