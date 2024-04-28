package com.resource.CommonFunctions;

import com.google.gson.JsonObject;

import java.util.List;

public class SendToAPI {
    public static void sendMetricsToApi(String URL, List<JsonObject> list){
        try {
            for (JsonObject jsonObject : list) {
                PostAPI.postMetricsToAPI(jsonObject,URL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
