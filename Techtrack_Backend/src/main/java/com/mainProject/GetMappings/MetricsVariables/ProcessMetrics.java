package com.mainProject.GetMappings.MetricsVariables;

import java.sql.Timestamp;

public class ProcessMetrics {

    private Timestamp timestamp;
    private String processId;
    private String name;
    private String sessionId;
    private double memoryUsage;
    private double cpuTime;
    private Timestamp startTime;
    private boolean responding;

    // Constructor
    public ProcessMetrics() {
    }

    // Getters and setters
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public double getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public double getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(double cpuTime) {
        this.cpuTime = cpuTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public boolean isResponding() {
        return responding;
    }

    public void setResponding(boolean responding) {
        this.responding = responding;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "ProcessMetrics{" +
                "timestamp=" + timestamp +
                ", processId='" + processId + '\'' +
                ", name='" + name + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", memoryUsage=" + memoryUsage +
                ", cpuTime=" + cpuTime +
                ", startTime=" + startTime +
                ", responding=" + responding +
                '}';
    }
}

