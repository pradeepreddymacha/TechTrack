package com.mainProject.GetMappings.AggregatedMetricsVariables;

public class AggregatedMemoryMetrics {
    private int year;
    private int hour;
    private String day;
    private int month;
    private double avgFreePhysicalMemory;
    private double avgTotalVisibleMemorySize;

    // Constructors, Getters, and Setters

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getAvgFreePhysicalMemory() {
        return avgFreePhysicalMemory;
    }

    public void setAvgFreePhysicalMemory(double avgFreePhysicalMemory) {
        this.avgFreePhysicalMemory = avgFreePhysicalMemory;
    }

    public double getAvgTotalVisibleMemorySize() {
        return avgTotalVisibleMemorySize;
    }

    public void setAvgTotalVisibleMemorySize(double avgTotalVisibleMemorySize) {
        this.avgTotalVisibleMemorySize = avgTotalVisibleMemorySize;
    }
}