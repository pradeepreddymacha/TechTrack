package CollectorTests;

import com.google.gson.JsonObject;
import com.resource.MainCollectors.SystemMetricsCollector;
import org.junit.Test;
import static org.junit.Assert.*;
public class SystemMetricsCollectorTest {
    // Call collectSystemMetrics
    JsonObject metrics = SystemMetricsCollector.collectSystemMetrics();

    @Test
    public void testCollectSystemMetrics() {
        // Assert that metrics is not null and contains required fields
        assertNotNull(metrics);
        assertTrue(metrics.has("timestamp"));
        assertTrue(metrics.has("osName"));
        assertTrue(metrics.has("osVersion"));
        assertTrue(metrics.has("hostName"));
        assertTrue(metrics.has("macAddress"));
        assertTrue(metrics.has("systemManufacturer"));
        assertTrue(metrics.has("systemModel"));
        assertTrue(metrics.has("systemType"));
        assertTrue(metrics.has("totalPhysicalMemory"));
    }
    @Test
    public void testGetSystemManufacturer() {
        // Test getSystemManufacturer method
        String manufacturer = SystemMetricsCollector.getSystemManufacturer();

        // Assert that manufacturer is not null or empty
        assertNotNull(manufacturer);
        assertFalse(manufacturer.isEmpty());
    }
    @Test
    public void testGetSystemModel() {
        // Test getSystemModel method
        String model = SystemMetricsCollector.getSystemModel();

        // Assert that model is not null or empty
        assertNotNull(model);
        assertFalse(model.isEmpty());
    }
    @Test
    public void testGetSystemType() {
        // Test getSystemType method
        String type = SystemMetricsCollector.getSystemType();

        // Assert that type is not null or empty
        assertNotNull(type);
        assertFalse(type.isEmpty());
    }

    @Test
    public void testGetOsName() {
        assertNotNull(metrics.get("osName"));
        assertFalse(metrics.get("osName").toString().isEmpty());
    }

    @Test
    public void testGetOsVersion() {
        assertNotNull(metrics.get("osVersion"));
        assertFalse(metrics.get("osVersion").toString().isEmpty());
    }

    @Test
    public void testGetHostName() {
        assertNotNull(metrics.get("hostName"));
        assertFalse(metrics.get("hostName").toString().isEmpty());
    }

    @Test
    public void testGetMacAddress() {
        assertNotNull(metrics.get("macAddress"));
        assertFalse(metrics.get("macAddress").toString().isEmpty());
    }

    @Test
    public void testGetTotalPhysicalMemory() {
        assertNotNull(metrics.get("totalPhysicalMemory"));
        assertFalse(metrics.get("totalPhysicalMemory").toString().isEmpty());
    }
}