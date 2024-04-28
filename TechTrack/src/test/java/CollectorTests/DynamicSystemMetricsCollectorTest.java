package CollectorTests;

import com.google.gson.JsonObject;
import org.junit.Test;

import static com.resource.CommonFunctions.CollectMetrics.collectMetrics;
import static com.resource.MainCollectors.DynamicSystemMetricsCollector.collectCPULoad;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

public class DynamicSystemMetricsCollectorTest {
    private final String diskUsageScript = "src/main/resources/static/disk_usage.ps1";

    private final String memoryUsageScript = "src/main/resources/static/memory_usage.ps1";
    private final List<JsonObject> discMetrics = collectMetrics(diskUsageScript);

    private final List<JsonObject> memoryMetrics = collectMetrics(memoryUsageScript);
    @Test
    public void testDiskMetricsCollection() {

        for(JsonObject json:discMetrics){
            assertTrue(json.has("DeviceID"));
            assertNotNull(json.get("DeviceID"));

            assertTrue(json.has("FreeSpace"));
            assertNotNull(json.get("FreeSpace"));

            assertTrue(json.has("Size"));
            assertNotNull(json.get("Size"));

            assertTrue(json.has("VolumeName"));
            assertNotNull(json.get("VolumeName"));
        }

    }
    @Test
    public void testMemoryMetricsCollection() {
        for(JsonObject json:memoryMetrics){
            assertTrue(json.has("FreePhysicalMemory"));
            assertNotNull(json.get("FreePhysicalMemory"));

            assertTrue(json.has("TotalVisibleMemorySize"));
            assertNotNull(json.get("TotalVisibleMemorySize"));

            assertTrue(json.has("SystemDirectory"));
            assertNotNull(json.get("SystemDirectory"));

            assertTrue(json.has("BuildNumber"));
            assertNotNull(json.get("BuildNumber"));
        }

    }
    @Test
    public void testCPUMetricsCollection() throws IOException {
        JsonObject json = collectCPULoad();

        assertTrue(json.has("LoadPercentage"));
        assertNotNull(json.get("LoadPercentage"));

    }
}
