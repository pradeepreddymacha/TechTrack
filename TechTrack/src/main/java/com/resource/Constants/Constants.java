package com.resource.Constants;


public class Constants {
    public static final String POST_URL = "http://localhost:8080/";

    public static final String Shell_Script_Path = "TechTrack/src/main/resources/static/";

    public static final String Memory_API_URL = POST_URL + "memory_metrics";

    public static final String Disk_API_URL = POST_URL + "Disk_metrics";

    public static final String CPU_API_URL = POST_URL + "cpu_metrics";

    public static final String Network_API_URL = POST_URL + "Network_metrics";

    public static final String Process_API_URL = POST_URL + "process_metrics";

    public static final String Metrics_API_URL = POST_URL + "metrics_new";

    public static final String diskPowerShellPath = Shell_Script_Path + "disk_usage.ps1";

    public static final String memoryPowerShellPath = Shell_Script_Path + "memory_usage.ps1";

    public static final String networkPowerShellPath = Shell_Script_Path + "network_metrics.ps1";

    public static final String processPowerShellPath = Shell_Script_Path + "process_list.ps1";

}
