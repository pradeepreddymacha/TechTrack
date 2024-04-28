package com.mainProject.userVariables;



    public class ProcessMetricsVariables {
        private long timestamp;
        private String processId;
        private String name;
        private String sessionId;
        private double memoryUsage;
        private double cpuTime;
        private String startTime; // Changed data type to String
        private boolean responding;

        // Constructors, getters, and setters
        public ProcessMetricsVariables() {
        }

        public ProcessMetricsVariables(long timestamp, String processId, String name, String sessionId,
                                       double memoryUsage, double cpuTime, String startTime, boolean responding) { // Changed parameter type to String
            this.timestamp = timestamp;
            this.processId = processId;
            this.name = name;
            this.sessionId = sessionId;
            this.memoryUsage = memoryUsage;
            this.cpuTime = cpuTime;
            this.startTime = startTime;
            this.responding = responding;
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

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) { // Changed parameter type to String
            this.startTime = startTime;
        }

        public boolean isResponding() {
            return responding;
        }

        public void setResponding(boolean responding) {
            this.responding = responding;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }


