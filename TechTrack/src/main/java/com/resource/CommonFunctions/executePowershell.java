package com.resource.CommonFunctions;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class executePowershell {
    public static List<JsonObject> executeShellScript(String scriptName) throws IOException {
        List<JsonObject> metricsArray = new ArrayList<>();
        try {
            // Command to run the PowerShell script
            String command = "powershell.exe -ExecutionPolicy Bypass -File " + scriptName;

            // Create ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);

            // Redirect error stream to standard output
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read output from the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("timestamp", System.currentTimeMillis());
            while ((line = reader.readLine()) != null) {
                if(line.trim().isEmpty()){
                    continue;
                }
                if (line.trim().startsWith("break-line") || line.contains("---")) {
                    metricsArray.add(jsonObject);
                    jsonObject = new JsonObject();
                    jsonObject.addProperty("timestamp", System.currentTimeMillis());
                }
                else{
                    String[] parts = line.split(":",2);
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    if(value.isEmpty()){
                        if(key.isEmpty()){
                            continue;
                        }
                    }

                    jsonObject.addProperty(key, value);
                }
            }

            // Wait for the process to complete
            process.waitFor();

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return metricsArray;
    }
}
