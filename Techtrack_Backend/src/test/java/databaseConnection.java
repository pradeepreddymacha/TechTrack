import com.mainProject.DatabaseConnector.DatabaseManager;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class databaseConnection {
    @Test
    public void testConnectionEstablishment() {
        try {
            // Attempt to establish a connection
            Connection connection = DatabaseManager.getConnection();

            // Assert that the connection is not null
            assertNotNull(connection);

            // Optionally, you can check if the connection is valid
            assertTrue(connection.isValid(5)); // Check if the connection is valid for at least 5 seconds

            // Close the connection after testing (optional)
            connection.close();
        } catch (SQLException e) {
            // Fail the test if an exception occurs during connection establishment
            fail("Failed to establish a connection: " + e.getMessage());
        }
    }
}
