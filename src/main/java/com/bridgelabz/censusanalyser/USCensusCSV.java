package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {
    @CsvBindByName(column = "State")
    public String state;
    @CsvBindByName(column = "State Id")
    public String stateId;
//    @CsvBindByName(column = "StateId")
//    public int population;
//    @CsvBindByName(column = "Total Area")
//    public double totalArea;
//    @CsvBindByName(column = "Population Density")
//    public int populationDensity;

    @Override
    public String toString() {
        return "USCensusCSV{" +
                "state='" + state + '\'' +
                ", state Id='" + stateId + '\'' +
//                ", population=" + population +
//                ", totalArea=" + totalArea +
//                ", populationDensity=" + populationDensity +
                '}';
    }
}
