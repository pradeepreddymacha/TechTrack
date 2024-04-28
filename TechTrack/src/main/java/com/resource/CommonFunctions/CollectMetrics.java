package com.resource.CommonFunctions;

import com.google.gson.JsonObject;

import java.util.List;

public class CollectMetrics {
    public static List<JsonObject> collectMetrics(String path) {

        List<JsonObject> metricsList = null;
        try {
            metricsList = executePowershell.executeShellScript(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metricsList;
    }

}


