package com.bridgelabz.censusanalyser;

import customcsv.util.ICSVBuilder;
import customcsv.util.OpenCSVBuilder;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilder();
    }
}
