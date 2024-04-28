package com.mainProject.userVariables;

public class DiskMetricsVariables {
    private long timestamp;
    private String DeviceID;
    private long FreeSpace;
    private long Size;
    private String VolumeName;



    public DiskMetricsVariables(long timestamp, String DeviceID, long FreeSpace, long Size, String VolumeName) {
        this.timestamp = timestamp;
        this.DeviceID = DeviceID;
        this.FreeSpace =  FreeSpace;
        this.Size = Size;
        this.VolumeName = VolumeName;
    }

    public String getDeviceID() {
        return this.DeviceID;
    }

    public void setDeviceID(String DeviceID) {
        this.DeviceID = DeviceID;
    }

    public long getFreeSpace() {
        return FreeSpace;
    }

    public void setFreeSpace(long FreeSpace) {
        this.FreeSpace = FreeSpace;
    }

    public long getSize() {
        return Size;
    }

    public void setSize(long Size) {
        this.Size = Size;
    }

    public String getVolumeName() {
        return VolumeName;
    }

    public void setVolumeName(String VolumeName) {
        this.VolumeName = VolumeName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
