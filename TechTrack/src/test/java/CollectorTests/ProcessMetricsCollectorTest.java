package CollectorTests;

import com.google.gson.JsonObject;
import com.resource.CommonFunctions.CollectMetrics;


import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
public class ProcessMetricsCollectorTest {

    private final String ProcessMetricsScript = "src/main/resources/static/process_list.ps1";
    private final List<JsonObject> metrics = CollectMetrics.collectMetrics(ProcessMetricsScript);
    @Test
    public void testProcessIDCollection() {
        for(JsonObject js:metrics){
            assertTrue(js.has("processId"));
            assertNotNull(js.get("processId"));
        }
    }
    @Test
    public void testProcessNameCollection() {
        for(JsonObject js:metrics){
            assertTrue(js.has("name"));
            assertNotNull(js.get("name"));
        }
    }
    @Test
    public void testMemoryUsageCollection() {
        for(JsonObject js:metrics){
            assertTrue(js.has("memoryUsage"));
            assertNotNull(js.get("memoryUsage"));
        }
    }
    @Test
    public void testCPUUsageCollection() {
        for(JsonObject js:metrics){
            assertTrue(js.has("cpuTime"));
        }


    }
    @Test
    public void testProcesstimeCollection() {
        for(JsonObject js:metrics){
            assertTrue(js.has("startTime"));
        }

    }
}
