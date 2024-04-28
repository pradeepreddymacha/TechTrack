package com.mainProject.userVariables;

import java.sql.Timestamp;

public class SystemMetricsVariables {
    private long timestamp;
    private String osName;
    private String osVersion;
    private String hostName;
    private String macAddress;
    private String systemManufacturer;
    private String systemModel;
    private String systemType;
    private long totalPhysicalMemory;


    // Constructors, getters, and setters
    public SystemMetricsVariables() {}

    public SystemMetricsVariables(long timestamp, String osName, String osVersion, String hostName, String macAddress,
                                  String systemManufacturer, String systemModel, String systemType, long totalPhysicalMemory) {
        this.timestamp = timestamp;
        this.osName = osName;
        this.osVersion = osVersion;
        this.hostName = hostName;
        this.macAddress = macAddress;
        this.systemManufacturer = systemManufacturer;
        this.systemModel = systemModel;
        this.systemType = systemType;
        this.totalPhysicalMemory = totalPhysicalMemory;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getSystemManufacturer() {
        return systemManufacturer;
    }

    public void setSystemManufacturer(String systemManufacturer) {
        this.systemManufacturer = systemManufacturer;
    }

    public String getSystemModel() {
        return systemModel;
    }

    public void setSystemModel(String systemModel) {
        this.systemModel = systemModel;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public long getTotalPhysicalMemory() {
        return totalPhysicalMemory;
    }

    public void setTotalPhysicalMemory(long totalPhysicalMemory) {
        this.totalPhysicalMemory = totalPhysicalMemory;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
