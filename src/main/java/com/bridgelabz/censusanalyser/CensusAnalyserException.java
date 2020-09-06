package com.bridgelabz.censusanalyser;

public class CensusAnalyserException extends Exception {
      public enum ExceptionType {
          CENSUS_FILE_PROBLEM, FILE_TYPE_OR_DELIMITER_OR_HEADER_PROBLEM
    }

   public  ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
