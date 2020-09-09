package com.bridgelaz.censusanalyzer;

import com.bridgelabz.censusanalyser.CensusAnalyser;
import com.bridgelabz.censusanalyser.CensusAnalyserException;
import com.bridgelabz.censusanalyser.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.IndiaStateCodeCSV;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = ".\\src\\test\\resources\\IndiaStateCensusData.CSV";
    private static final String WRONG_CSV_FILE_PATH = ".\\src\\main\\resources\\IndiaStateCensusData.CSV";
    private static final String WRONG_CSV_FILE_TYPE_PATH = ".\\src\\test\\resources\\file.txt";
    private static final String WRONG_DELIMITER_FILE_PATH = ".\\src\\test\\resources\\WrongDelimiter.CSV";
    private static final String CSV_FILE_WITH_WRONG_HEADER_PATH = ".\\src\\test\\resources\\InvalidHeader.CSV";
    private static final String INDIA_STATE_CODE_CSV_FILE_PATH = ".\\src\\test\\resources\\IndiaStateCode.CSV";
    private static final String INDIA_STATE_CODE_WRONG_CSV_FILE_PATH = ".\\src\\main\\resources\\IndiaStatCode.csv";
    private static final String INDIA_STATE_CODE_WRONG_DELIMITER_FILE_PATH =
            ".\\src\\test\\resources\\IndiaStateCodeWithWrongDelimiter.CSV";
    private static final String INDIA_STATE_CODE_WRONG_HEADER_FILE_PATH =
            ".\\src\\test\\resources\\IndiaStateCodeWithWrongHeader.CSV";

    @Test
    public void givenIndianCensusCsvFile_WhenCheckRecords_ReturnCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numberOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_OR_DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_DELIMITER_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_OR_DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(CSV_FILE_WITH_WRONG_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_OR_DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenCheckRecords_ReturnCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numberOfRecords = censusAnalyser.loadIndiaStateData(INDIA_STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaStateCodeData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateData(INDIA_STATE_CODE_WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeData_WithWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateData(WRONG_CSV_FILE_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_OR_DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateData(INDIA_STATE_CODE_WRONG_DELIMITER_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_OR_DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeData_WithWrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateData(INDIA_STATE_CODE_WRONG_HEADER_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_OR_DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_whenSortedByState_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_OR_DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeData_whenSortedByStateCode_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaStateData(INDIA_STATE_CODE_CSV_FILE_PATH);
            String stateCodeWiseSortedCensusData = censusAnalyser.getStateCodeWiseSortedCensusData();
            IndiaStateCodeCSV[] censusCSV = new Gson().fromJson(stateCodeWiseSortedCensusData, IndiaStateCodeCSV[].class);
            Assert.assertEquals("AD", censusCSV[0].stateCode);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_OR_DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }
    @Test
    public void givenIndianCensusData_whenSortedByPopulation_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(607688, censusCSV[0].population);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_OR_DELIMITER_OR_HEADER_PROBLEM, e.type);
        }
    }
}