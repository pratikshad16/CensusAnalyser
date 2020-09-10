package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {
    @CsvBindByName(column = "State")
    public String state;
    @CsvBindByName(column = "StateId")
    public String stateId;
    @CsvBindByName(column = "StateId")
    public int population;
    @CsvBindByName(column = "Total Area")
    public double totalArea;
    @CsvBindByName(column = "Population Density")
    public int populationDensity;
}
