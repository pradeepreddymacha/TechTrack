package com.mainProject.userVariables;

public class CPUVaraiable {
    long timestamp;

    int loadPercentage;

    public CPUVaraiable(long timestamp, int LoadPercentage) {
        this.timestamp = timestamp;
        this.loadPercentage = LoadPercentage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getLoadPercentage() {
        return loadPercentage;
    }

    public void setLoadPercentage(int loadPercentage) {
        this.loadPercentage = loadPercentage;
    }

}
