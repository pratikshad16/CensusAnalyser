package com.bridgelabz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {

    @CsvBindByName(column = "State Name")
    public String stateName;

    @CsvBindByName(column = "StateCode")
    public String stateCode;

    @Override
    public String toString() {
        return "IndiaCensusCSV{" +
                ", State Name='" + stateName + '\'' +
                ", State Code='" + stateCode + '\'' +
                '}';
    }
}
