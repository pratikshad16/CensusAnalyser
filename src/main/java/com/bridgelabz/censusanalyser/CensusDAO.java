package com.bridgelabz.censusanalyser;

public class CensusDAO {
   public String state;
    public  int population;
    public double population_Density;
    public long populationDensity;
    public long totalArea;
    public String stateName;
    public String stateCode;
    public double total_Area;



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
       population = usCensusCSV.population;
       population_Density = usCensusCSV.population_Density;
        total_Area = usCensusCSV.total_Area;

    }
}
