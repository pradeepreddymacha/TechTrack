package com.resource.MainCollectors;

import com.google.gson.JsonObject;
import com.resource.CommonFunctions.CLIExec;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

import java.io.IOException;
import java.net.*;
import java.util.*;

import static com.resource.CommonFunctions.PostAPI.postMetricsToAPI;
import static com.resource.Constants.Constants.*;


public class SystemMetricsCollector {

    public static void collectingSystemMetrics() {
        try {
            postMetricsToAPI(collectSystemMetrics(),Metrics_API_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JsonObject collectSystemMetrics() {
        // Create a JSON object to store system metrics
        JsonObject json = new JsonObject();

        // Add system metrics to JSON object
        json.addProperty("timestamp", System.currentTimeMillis());
        json.addProperty("osName", System.getProperty("os.name"));
        json.addProperty("osVersion", System.getProperty("os.version"));
        json.addProperty("hostName", gethostName());
        json.addProperty("macAddress", getMacAddress());
        json.addProperty("systemManufacturer", getSystemManufacturer());
        json.addProperty("systemModel", getSystemModel());
        json.addProperty("systemType", getSystemType());
        json.addProperty("totalPhysicalMemory", getTotalPhysicalMemory());

        return json;
    }

    public static String getMacAddress(){
        String macAddress = "";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
            byte[] macBytes = networkInterface.getHardwareAddress();
            StringBuilder macStringBuilder = new StringBuilder();
            for (int i = 0; i < macBytes.length; i++) {
                macStringBuilder.append(String.format("%02X%s", macBytes[i], (i < macBytes.length - 1) ? "-" : ""));
            }
            macAddress = macStringBuilder.toString();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
        return macAddress;
    }

    public static long getTotalPhysicalMemory(){
        // Total Physical Memory
        long totalPhysicalMemory = 0;
        try{
            OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            totalPhysicalMemory = osBean.getTotalMemorySize();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return totalPhysicalMemory;
    }

    public static String gethostName(){
        // Hostname and MAC Address
        String hostName = "";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            hostName = localHost.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }

    public static String getSystemManufacturer() {
        String command = "wmic baseboard get manufacturer";
        return CLIExec.executeCommand(command);
    }

    public static String getSystemType() {
        String command = "wmic os get osarchitecture";
        return CLIExec.executeCommand(command);
    }

    public static String getSystemModel() {
        String model = "";
        try {
            Process process = Runtime.getRuntime().exec("systeminfo");
            try (Scanner scanner = new Scanner(process.getInputStream())) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.startsWith("System Model:")) {
                        model = line.substring("System Model:".length()).trim();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }
}
