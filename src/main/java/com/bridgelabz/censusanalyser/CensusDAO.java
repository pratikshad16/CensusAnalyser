package com.bridgelabz.censusanalyser;

public class CensusDAO {
   public String state;
    public  int population;
    public int populationDensity;
    public double totalArea;
    public String stateName;
    public String stateCode;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        totalArea = indiaCensusCSV.totalArea;
        populationDensity = indiaCensusCSV.populationDensity;
        population = indiaCensusCSV.population;
    }
    public CensusDAO(IndiaStateCodeCSV indiaStateCodeCSV) {
        stateCode = indiaStateCodeCSV.stateCode;
        stateName = indiaStateCodeCSV.stateName;
    }

    public CensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.state;
        stateCode = usCensusCSV.stateId;
//        population = usCensusCSV.population;
//        populationDensity = usCensusCSV.populationDensity;
//        totalArea = usCensusCSV.totalArea;

    }
}
