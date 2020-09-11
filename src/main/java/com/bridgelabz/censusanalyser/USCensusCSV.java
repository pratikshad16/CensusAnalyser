package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {
    @CsvBindByName(column = "State")
    public String state;
    @CsvBindByName(column = "State Id")
    public String stateId;
   @CsvBindByName(column = "Population")
    public int population;
   @CsvBindByName(column = "Total area")
    public double  total_Area;
   @CsvBindByName(column = "Population Density")
    public double population_Density;

    @Override
    public String toString() {
        return "USCensusCSV{" +
                "State='" + state + '\'' +
                ",State Id='" + stateId + '\'' +
              ", Population=" + population +
              ", Total area=" + total_Area +
                ", Population Density=" + population_Density +
                '}';
    }
}
 //   State Id,State,Population,Housing units,Total area,Water area,Land area,Population Density,Housing Density