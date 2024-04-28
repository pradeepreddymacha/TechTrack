package com.mainProject.userVariables;


public class NetworkMetricsVariables {
    private long timestamp;
    private String connectionName;
    private long receivedBytes;
    private long unicastPackets;
    private long sentBytes;
    private long sentUnicastPackets;

    // Constructors, getters, and setters
    public NetworkMetricsVariables() {}

    public NetworkMetricsVariables(long timestamp, String connectionName, long receivedBytes, long unicastPackets, long sentBytes, long sentUnicastPackets) {
        this.timestamp = timestamp;
        this.connectionName = connectionName;
        this.receivedBytes = receivedBytes;
        this.unicastPackets = unicastPackets;
        this.sentBytes = sentBytes;
        this.sentUnicastPackets = sentUnicastPackets;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public long getReceivedBytes() {
        return receivedBytes;
    }

    public void setReceivedBytes(long receivedBytes) {
        this.receivedBytes = receivedBytes;
    }

    public long getUnicastPackets() {
        return unicastPackets;
    }

    public void setUnicastPackets(long unicastPackets) {
        this.unicastPackets = unicastPackets;
    }

    public long getSentBytes() {
        return sentBytes;
    }

    public void setSentBytes(long sentBytes) {
        this.sentBytes = sentBytes;
    }

    public long getSentUnicastPackets() {
        return sentUnicastPackets;
    }

    public void setSentUnicastPackets(long sentUnicastPackets) {
        this.sentUnicastPackets = sentUnicastPackets;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

