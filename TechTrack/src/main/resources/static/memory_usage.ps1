# Retrieve operating system information
$CPUObjects = Get-WmiObject Win32_OperatingSystem | Select-Object FreePhysicalMemory, TotalVisibleMemorySize, SystemDirectory, BuildNumber

# Add a break-line
$outputObjects = [PSCustomObject]@{
            "FreePhysicalMemory" = $CPUObjects.FreePhysicalMemory
            "TotalVisibleMemorySize" = $CPUObjects.TotalVisibleMemorySize
            "SystemDirectory" = $CPUObjects.SystemDirectory
            "BuildNumber" = $CPUObjects.BuildNumber
            "break-line" = "next-line"
        }

# Output the information
$outputObjects
