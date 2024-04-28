import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.resource.CommonFunctions.PostAPI;
import org.junit.Assert;
import org.junit.Test;


public class APIConnectionTests {

    private static final String SystemMetrics_API_URL = "http://localhost:8080/metrics_new";

    private static final String networkAPIUrl = "http://localhost:8080/Network_metrics";

    private static final String process_API_URL = "http://localhost:8080/process_metrics";

    private static final String CPU_API_URL = "http://localhost:8080/cpu_metrics";

    private static final String Memory_API_URL = "http://localhost:8080/memory_metrics";

    private static final String Disk_API_URL = "http://localhost:8080/Disk_metrics";

    @Test
    public void testSystemMetricsAPI() {
        String jsonString = "{\"timestamp\":1713583837809,\"osName\":\"Windows 11\",\"osVersion\":\"10.0\",\"hostName\":\"Pradeep\",\"macAddress\":\"DC-46-28-B2-04-FD\",\"systemManufacturer\":\"LENOVO\",\"systemModel\":\"82H8\",\"systemType\":\"64-bit\",\"totalPhysicalMemory\":8362713088}\n";
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        Assert.assertEquals(PostAPI.postMetricsToAPI(jsonObject,SystemMetrics_API_URL),200);
    }

    @Test
    public void testProcessMetricsAPI() {
        String jsonString = "{\"timestamp\":1713583403868,\"processId\":\"8272\",\"name\":\"AggregatorHost\",\"sessionId\":\"0\",\"memoryUsage\":\"6.828125\",\"cpuTime\":\"\",\"startTime\":\"\",\"responding\":\"True\"}";
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        Assert.assertEquals(PostAPI.postMetricsToAPI(jsonObject,process_API_URL),200);
    }

    @Test
    public void testNetworkMetricsAPI() {
        String jsonString = "{\"timestamp\":1713583841558,\"connectionName\":\"WiFi\",\"receivedBytes\":\"1974652561\",\"unicastPackets\":\"1584997\",\"sentBytes\":\"49786245\",\"sentUnicastPackets\":\"136707\"}";
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        Assert.assertEquals(PostAPI.postMetricsToAPI(jsonObject,networkAPIUrl),200);
    }

    @Test
    public void testCPUloadMetricsAPI() {
        String jsonString = "{\"timestamp\":1713583841533,\"LoadPercentage\":\"5\"}";
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        Assert.assertEquals(PostAPI.postMetricsToAPI(jsonObject,CPU_API_URL),200);
    }

    @Test
    public void testmemoryMetricsAPI() {
        String jsonString = "{\"timestamp\":1713583840112,\"FreePhysicalMemory\":\"919804\",\"TotalVisibleMemorySize\":\"8166712\",\"SystemDirectory\":\"C:\\\\Windows\\\\system32\",\"BuildNumber\":\"22631\"}";
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        Assert.assertEquals(PostAPI.postMetricsToAPI(jsonObject,Memory_API_URL),200);
    }

    @Test
    public void testdiscMetricsAPI() {
        String jsonString = "{\"timestamp\":1713583839832,\"DeviceID\":\"C:\",\"FreeSpace\":\"248464302080\",\"Size\":\"510770802688\",\"VolumeName\":\"Windows-SSD\"}";
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        Assert.assertEquals(PostAPI.postMetricsToAPI(jsonObject,Disk_API_URL),200);
    }

}
