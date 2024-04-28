import CollectorTests.DynamicSystemMetricsCollectorTest;
import CollectorTests.NetworkMetricsCollectorTest;
import CollectorTests.ProcessMetricsCollectorTest;
import CollectorTests.SystemMetricsCollectorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({SystemMetricsCollectorTest.class, ProcessMetricsCollectorTest.class, NetworkMetricsCollectorTest.class, DynamicSystemMetricsCollectorTest.class,APIConnectionTests.class})
public class TestSuite {
}
