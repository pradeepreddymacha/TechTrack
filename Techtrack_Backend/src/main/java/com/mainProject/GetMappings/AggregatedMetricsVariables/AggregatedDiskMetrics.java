package com.mainProject.GetMappings.AggregatedMetricsVariables;

public class AggregatedDiskMetrics {
    private int year;
    private int hour;
    private String week;
    private int month;
    private double avgFreeSpace;

    private String volumeName;

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    public double getAvgSize() {
        return avgSize;
    }

    public void setAvgSize(double avgSize) {
        this.avgSize = avgSize;
    }

    private double avgSize;

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

    public double getAvgFreeSpace() {
        return avgFreeSpace;
    }

    public void setAvgFreeSpace(double avgFreeSpace) {
        this.avgFreeSpace = avgFreeSpace;
    }
}
