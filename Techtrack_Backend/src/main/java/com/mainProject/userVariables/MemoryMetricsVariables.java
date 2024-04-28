package com.mainProject.userVariables;



public class MemoryMetricsVariables {
    private long timestamp;
    private long FreePhysicalMemory;
    private long TotalVisibleMemorySize;
    private String SystemDirectory;
    private String BuildNumber;

    // Constructors, getters, and setters
    public MemoryMetricsVariables(long timestamp, long FreePhysicalMemory, long TotalVisibleMemorySize, String SystemDirectory, String BuildNumber) {
        this.timestamp = timestamp;
        this.FreePhysicalMemory = FreePhysicalMemory;
        this.TotalVisibleMemorySize = TotalVisibleMemorySize;
        this.SystemDirectory = SystemDirectory;
        this.BuildNumber = BuildNumber;
    }

    public long getFreePhysicalMemory() {
        return FreePhysicalMemory;
    }

    public void setFreePhysicalMemory(long FreePhysicalMemory) {
        this.FreePhysicalMemory = FreePhysicalMemory;
    }

    public long getTotalVisibleMemorySize() {
        return TotalVisibleMemorySize;
    }

    public void setTotalVisibleMemorySize(long TotalVisibleMemorySize) {
        this.TotalVisibleMemorySize = TotalVisibleMemorySize;
    }

    public String getSystemDirectory() {
        return SystemDirectory;
    }

    public void setSystemDirectory(String SystemDirectory) {
        this.SystemDirectory = SystemDirectory;
    }

    public String getBuildNumber() {
        return BuildNumber;
    }

    public void setBuildNumber(String BuildNumber) {
        this.BuildNumber = BuildNumber;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
