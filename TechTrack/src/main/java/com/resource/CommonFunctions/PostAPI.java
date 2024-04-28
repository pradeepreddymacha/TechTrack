package com.resource.CommonFunctions;

import com.google.gson.JsonObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostAPI {
    public static int postMetricsToAPI(JsonObject json,String API_URL) {
        int responseCode = 0;
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String metrics = json.toString();

            //Write JSON data to the connection's output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = metrics.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get response code
            responseCode = connection.getResponseCode();
            if(responseCode != 200){
                System.out.println("Response Code: " + responseCode + " " + metrics);
            }
            // Close connection
            connection.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
        return responseCode;
    }
}
