package com.resource.MainCollectors;

import com.google.gson.JsonObject;
import com.resource.CommonFunctions.SendToAPI;

import java.util.List;

import static com.resource.CommonFunctions.CollectMetrics.collectMetrics;
import static com.resource.Constants.Constants.*;

public class NetworkMetricsCollector {

    public static void collectingNetworkMetrics() {

        try {

            List<JsonObject> metrics = collectMetrics(networkPowerShellPath);

            SendToAPI.sendMetricsToApi(Network_API_URL, metrics);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
