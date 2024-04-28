package com.mainProject.DatabaseConnector;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest {

    @Test
    public void testConnectionEstablishment() {
        try {
            // Attempt to establish a connection
            Connection connection = DatabaseManager.getConnection();

            // Assert that the connection is not null
            assertNotNull(connection);

            // Check if the connection is valid for at least 5 seconds
            assertTrue(connection.isValid(5));

            // Close the connection after testing
            connection.close();
        } catch (SQLException e) {
            // Fail the test if an exception occurs during connection establishment
            fail("Failed to establish a connection: " + e.getMessage());
        }
    }
}
