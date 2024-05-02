package com.resource.CommonFunctions;

import com.google.gson.JsonObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class executePowershell {
    public static List<JsonObject> executeShellScript(String scriptName) {
        List<JsonObject> metricsArray = new ArrayList<>();
        try {
            // Obtain the InputStream for the script file
            InputStream inputStream = executePowershell.class.getResourceAsStream(scriptName);

            if (inputStream == null) {
                System.err.println("Script file not found: " + scriptName);
                return metricsArray;
            }

            // Create a temporary file to store the script content
            File tempFile = File.createTempFile("tempScript", ".ps1");
            tempFile.deleteOnExit(); // Delete the temporary file on exit

            // Copy the content of the script from InputStream to the temporary file
            try (OutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            // Command to run the PowerShell script
            String command = "powershell.exe -ExecutionPolicy Bypass -File \"" + tempFile.getAbsolutePath() + "\"";

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
                    if(parts.length<2){
                        System.out.println("empty-Metrics");
                        continue;
                    }
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
            System.out.println("Exception : " + e);
        }
        return metricsArray;
    }
}
