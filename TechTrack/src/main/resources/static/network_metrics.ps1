# Get all TCP connections
$tcpConnections = Get-NetAdapterStatistics | Where-Object { $_.OwningProcess -ne 0 }

# Initialize a hashtable to store network traffic per process
$networkTraffic = @()

# Iterate over each TCP connection
foreach ($connection in $tcpConnections) {

    $record = [PSCustomObject]@{
            "connectionName" = $connection.Name
            "receivedBytes" = $connection.ReceivedBytes
            "unicastPackets" = $connection.ReceivedUnicastPackets
            "sentBytes" = $connection.SentBytes
            "sentUnicastPackets" = $connection.SentUnicastPackets
            "break-line" = "next-line"
        }
    $networkTraffic += $record
}

$networkTraffic
