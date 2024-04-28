package com.resource.MainCollectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.util.List;

import com.google.gson.JsonObject;
import com.resource.CommonFunctions.PostAPI;
import com.resource.CommonFunctions.SendToAPI;

import static com.resource.CommonFunctions.CollectMetrics.collectMetrics;
import static com.resource.Constants.Constants.*;

public class DynamicSystemMetricsCollector {

    public static void collectingDymanicSystemMetrics() {
        try {

            diskMetrics();

            memoryMetrics();

            cpuMetric();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void diskMetrics() {
        try {
            List<JsonObject> metrics = collectMetrics(diskPowerShellPath);

            SendToAPI.sendMetricsToApi( Disk_API_URL, metrics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void memoryMetrics(){

        try {
            List<JsonObject> metrics = collectMetrics(memoryPowerShellPath);

            SendToAPI.sendMetricsToApi( Memory_API_URL, metrics);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void cpuMetric(){

        try {
            JsonObject metrics = collectCPULoad();

            PostAPI.postMetricsToAPI( metrics, CPU_API_URL);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static JsonObject collectCPULoad() throws IOException {
        JsonObject jsonObj = null;
        try {
            jsonObj = new JsonObject();
            // Execute the shell command
            Process process = Runtime.getRuntime().exec("wmic cpu get loadpercentage");

            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.trim().equals("LoadPercentage") || line.trim().isEmpty()){
                    continue;
                }
                jsonObj.addProperty("timestamp", System.currentTimeMillis());
                jsonObj.addProperty("LoadPercentage",line.trim());
            }

            // Wait for the process to complete
            process.waitFor();

            reader.close();


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

}
