package com.bridgelabz.censusanalyser;

import com.bridegelabz.censusanalysersecond.CSVBuilderException;
import com.bridegelabz.censusanalysersecond.CSVBuilderFactory;
import com.bridegelabz.censusanalysersecond.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    HashMap<String, CensusDAO> censusMap;
    public CensusAnalyser() {
        this.censusMap = new HashMap<>();
    }

    public<E> int loadCensusData(String csvFilePath, Class<E> censusCSVClass) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            Iterable<E> censusCSVIterable = () -> csvFileIterator;
            if (censusCSVClass.getName().equals("com.bridgelabz.censusanalyser.IndiaCensusCSV")) {
                        StreamSupport.stream(censusCSVIterable.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(csvState -> censusMap.put(csvState.state, new CensusDAO(csvState)));
            }  else if (censusCSVClass.getName().equals("com.bridgelabz.censusanalyser.IndiaStateCodeCSV")) {
                StreamSupport.stream(censusCSVIterable.spliterator(), false)
                        .map(IndiaStateCodeCSV.class::cast)
                        .forEach(csvStateCode -> censusMap.put(csvStateCode.stateCode, new CensusDAO(csvStateCode)));
            } else if (censusCSVClass.getName().equals("com.bridgelabz.censusanalyser.USCensusCSV")) {
                StreamSupport.stream(censusCSVIterable.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(csvState -> censusMap.put(csvState.state, new CensusDAO(csvState)));
            }
            return this.censusMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        return this.loadCensusData(csvFilePath,IndiaCensusCSV.class);
    }
    public int loadIndiaStateData(String csvFilePath) throws CensusAnalyserException {
        return this.loadCensusData(csvFilePath,IndiaStateCodeCSV.class);
    }
    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        return this.loadCensusData(csvFilePath,USCensusCSV.class);
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

