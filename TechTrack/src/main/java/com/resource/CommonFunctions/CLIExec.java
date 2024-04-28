package com.resource.CommonFunctions;

import java.io.IOException;
import java.util.Scanner;

public class CLIExec {
    public static String executeCommand(String command) {
        String type = "";
        try {
            Process process = Runtime.getRuntime().exec(command);
            try (Scanner scanner = new Scanner(process.getInputStream())) {
                if (scanner.hasNext()) {
                    scanner.next(); // Skip header
                    if (scanner.hasNext()) {
                        type = scanner.next();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type.trim();
    }
}
