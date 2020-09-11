package com.bridgelabz.censusanalyser;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CensusAnalyser {
    Map<String, CensusDAO> censusMap;
    public CensusAnalyser() { }
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        censusMap = new CensusLoader().loadCensusData(csvFilePath,IndiaCensusCSV.class);
        return censusMap.size();
    }
    public int loadIndiaStateData(String csvFilePath) throws CensusAnalyserException {
        censusMap =  new CensusLoader().loadCensusData(csvFilePath,IndiaStateCodeCSV.class);
        return censusMap.size();
    }
    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        censusMap =  new  CensusLoader().loadCensusData(csvFilePath,USCensusCSV.class);
        return censusMap.size();
    }

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        List<CensusDAO> censusDAOList = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusComparator, censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.stateCode);
        List<CensusDAO> censusDAOList = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusComparator, censusDAOList);
        String sortedStateCodeJson = new Gson().toJson(censusDAOList);
        return sortedStateCodeJson;
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        List<CensusDAO> censusDAOList = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusComparator.reversed(), censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    public String getDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.populationDensity);
        List<CensusDAO> censusDAOList = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusComparator.reversed(), censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    public String getAreaWiseSortedCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.totalArea);
        List<CensusDAO> censusDAOList = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusComparator.reversed(), censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }
    public String getPopulationWiseSortedUSCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        List<CensusDAO> censusDAOList = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusComparator.reversed(), censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }
    public String getAreaWiseSortedUSCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.total_Area);
        List<CensusDAO> censusDAOList = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusComparator.reversed(), censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }
    public String getPopulationDensityWiseSortedUSCensusData() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.population_Density);
        List<CensusDAO> censusDAOList = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusComparator.reversed(), censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    private static <CensusDAO> List<CensusDAO> sort(Comparator<CensusDAO> censusComparator, List<CensusDAO> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                CensusDAO census1 = list.get(j);
                CensusDAO census2 = list.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    list.set(j, census2);
                    list.set(j + 1, census1);
                }
            }
        }
        return list;
    }
}

