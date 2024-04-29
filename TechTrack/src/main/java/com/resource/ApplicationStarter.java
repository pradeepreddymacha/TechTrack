package com.resource;

import static com.resource.MainCollectors.DynamicSystemMetricsCollector.*;

import static com.resource.MainCollectors.NetworkMetricsCollector.collectingNetworkMetrics;
import static com.resource.MainCollectors.ProcessMetricsCollector.collectingProcessMetrics;
import static com.resource.MainCollectors.SystemMetricsCollector.collectingSystemMetrics;

public class ApplicationStarter {
    public static void main(String[] args) {
        try {
            collectingSystemMetrics();
            while(true){

                collectingDymanicSystemMetrics();

                collectingNetworkMetrics();

                collectingProcessMetrics();

                System.out.println("All metrics Collected");

                Thread.sleep(15000);
            }

        } catch (Exception e) {
            System.out.println("Error is :"+e);
        }
    }
}
