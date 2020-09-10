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
    HashMap<String, CensusDAO> map ;

    public CensusAnalyser() {

        this.map = new HashMap<>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            Iterable<IndiaCensusCSV> censusCSVIterable = () -> csvFileIterator;
            StreamSupport.stream(censusCSVIterable.spliterator(), false)
                    .forEach(csvState -> map.put(csvState.state, new CensusDAO(csvState)));
            return this.map.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public int loadIndiaStateData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            Iterable<IndiaStateCodeCSV> censusCSVIterable = () -> csvFileIterator;
            StreamSupport.stream(censusCSVIterable.spliterator(), false)
                    .forEach(csvStateCode -> map.put(csvStateCode.stateCode, new CensusDAO(csvStateCode)));

            return this.map.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }
    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<USCensusCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, USCensusCSV.class);
            Iterable<USCensusCSV> censusCSVIterable = () -> csvFileIterator;
            StreamSupport.stream(censusCSVIterable.spliterator(), false)
                    .forEach(csvState -> this.map.put(csvState.state, new CensusDAO(csvState)));
            return this.map.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }


    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (map == null || map.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.state);
        List<CensusDAO> censusDAOList = map.values().stream().collect(Collectors.toList());
        this.sort(censusComparator, censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
        if (map == null || map.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.stateCode);
        List<CensusDAO> censusDAOList = map.values().stream().collect(Collectors.toList());
        this.sort(censusComparator, censusDAOList);
        String sortedStateCodeJson = new Gson().toJson(censusDAOList);
        return sortedStateCodeJson;
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (map == null || map.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        List<CensusDAO> censusDAOList = map.values().stream().collect(Collectors.toList());
        this.sort(censusComparator.reversed(), censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    public String getDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (map == null || map.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.populationDensity);
        List<CensusDAO> censusDAOList = map.values().stream().collect(Collectors.toList());
        this.sort(censusComparator.reversed(), censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    public String getAreaWiseSortedCensusData() throws CensusAnalyserException {
        if (map == null || map.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.totalArea);
        List<CensusDAO> censusDAOList = map.values().stream().collect(Collectors.toList());
        this.sort(censusComparator.reversed(), censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }
    public String getPopulationWiseSortedUSCensusData() throws CensusAnalyserException {
        if (map == null || map.size() == 0) {
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(census -> census.population);
        List<CensusDAO> censusDAOList = map.values().stream().collect(Collectors.toList());
        this.sort(censusComparator.reversed(), censusDAOList);
        String sortedStateCensusJson = new Gson().toJson(censusDAOList);
        return sortedStateCensusJson;
    }

    private static <E> List<E> sort(Comparator<E> censusComparator, List<E> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                E census1 = list.get(j);
                E census2 = list.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    list.set(j, census2);
                    list.set(j + 1, census1);
                }
            }
        }
        return list;
    }
}

