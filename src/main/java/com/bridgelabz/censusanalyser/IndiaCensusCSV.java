package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {
    @CsvBindByName(column = "State")
    public String state;

    @CsvBindByName(column = "Population")
    public int population;

    @CsvBindByName(column = "AreaInSqKm")
    public long totalArea;

    @CsvBindByName(column = "DensityPerSqKm")
    public long populationDensity;

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                "State='" + state + '\'' +
                ", Population='" + population + '\'' +
                ", AreaInSqKm='" + totalArea + '\'' +
                ", DensityPerSqKm='" + populationDensity + '\'' +
                '}';
    }
}
