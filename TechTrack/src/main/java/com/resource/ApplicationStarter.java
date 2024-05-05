package com.resource;

import static com.resource.MainCollectors.DynamicSystemMetricsCollector.collectingDymanicSystemMetrics;
import static com.resource.MainCollectors.NetworkMetricsCollector.collectingNetworkMetrics;
import static com.resource.MainCollectors.ProcessMetricsCollector.collectingProcessMetrics;
import static com.resource.MainCollectors.SystemMetricsCollector.collectingSystemMetrics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ApplicationStarter {
    private static boolean isMetricsCollectionOn = true;

    public static void main(String[] args) {
        try {

            // Create a frame (window)
            JFrame frame = new JFrame("Metrics Collection Control");

            // Create a button
            JButton toggleButton = new JButton("Metrics Collection (Click here to On or Off)");
            toggleButton.addActionListener(e -> toggleMetricsCollection());

            // Add button to frame
            frame.getContentPane().add(toggleButton);

            // Set frame properties
            frame.setSize(300, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            collectingSystemMetrics();
            while (true) {
                if (isMetricsCollectionOn) {
                    collectingDymanicSystemMetrics();
                    collectingNetworkMetrics();
                    collectingProcessMetrics();
                    System.out.println("All metrics Collected");
                }
                Thread.sleep(15000);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to toggle metrics collection
    private static void toggleMetricsCollection() {
        isMetricsCollectionOn = !isMetricsCollectionOn;
        JOptionPane.showMessageDialog(null, "Metrics Collection is now " + (isMetricsCollectionOn ? "ON" : "OFF"));
    }
}
