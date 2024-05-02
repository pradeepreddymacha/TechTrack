package CollectorTests;

import com.google.gson.JsonObject;
import com.resource.CommonFunctions.CollectMetrics;
import org.junit.Test;

import static com.resource.Constants.Constants.networkPowerShellPath;
import static org.junit.Assert.*;

import java.util.List;

public class NetworkMetricsCollectorTest {

    private final List<JsonObject> metrics = CollectMetrics.collectMetrics(networkPowerShellPath);

    @Test
    public void testBytesSentCollection(){
        for(JsonObject json:metrics){
            assertTrue(json.has("receivedBytes"));
            assertNotNull(json.get("receivedBytes"));
        }
    }
    @Test
    public void testBytesReceivedCollection() {
        for(JsonObject json:metrics){
            assertTrue(json.has("receivedBytes"));
            assertNotNull(json.get("receivedBytes"));
        }
    }
    @Test
    public void testUnicastPacketsSentCollection() {
        for(JsonObject json:metrics){
            assertTrue(json.has("sentBytes"));
            assertNotNull(json.get("sentBytes"));
        }

    }
    @Test
    public void testUnicastPacketsReceivedCollection() {
        for(JsonObject json:metrics){
            assertTrue(json.has("sentUnicastPackets"));
            assertNotNull(json.get("sentUnicastPackets"));
        }

    }
}