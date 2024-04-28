# Get a list of processes
$processes = Get-Process

$processArray = @()

# Output each property
foreach ($line in $processes) {
    $memoryUsage = if ($line.WS) { ($line.WS / 1MB) } else { $line.WS }
    $startTime = $line.StartTime
    $process = [PSCustomObject]@{
        "processId" = $line.Id
        "name" = $line.ProcessName
        "sessionId" =  $line.SI
        "memoryUsage" =  $memoryUsage
        "cpuTime" = $line.CPU
        "startTime" = if($startTime) {$line.StartTime.ToString("yyyy-MM-dd HH:mm:ss")} else { $startTime }
        "responding" = $line.Responding
        "break-line" = "-----"
    }

    $processArray += $process
}
$processArray