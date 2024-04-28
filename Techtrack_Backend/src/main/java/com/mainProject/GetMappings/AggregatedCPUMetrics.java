package com.mainProject.GetMappings;

public class AggregatedCPUMetrics {
    private int year;
    private int hour;
    private String week;
    private int month;
    private double avgCPULoad;

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

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getAvgCPULoad() {
        return avgCPULoad;
    }

    public void setAvgCPULoad(double avgCPULoad) {
        this.avgCPULoad = avgCPULoad;
    }
}
