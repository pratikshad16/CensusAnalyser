package com.bridgelaz.censusanalyzer;

import com.bridgelabz.censusanalyser.CensusAnalyser;
import com.bridgelabz.censusanalyser.CensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = ".\\src\\test\\resources\\IndiaStateCensusData";

    @Test
    public void givenIndianCensusCsvFile_ReturnCorrectRecords() {
        try {
            CensusAnalyser censusAanlyser = new CensusAnalyser();
            int numberOfRecords = censusAanlyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        } catch (CensusAnalyserException e) { }
    }
}

