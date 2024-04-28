$diskObjects = Get-WmiObject Win32_LogicalDisk

$diskDetails = @()

# Iterate over each TCP connection
foreach ($connection in $diskObjects) {

    $record = [PSCustomObject]@{
            "DeviceID" = $connection.DeviceID
            "FreeSpace" = $connection.FreeSpace
            "Size" = $connection.Size
            "VolumeName" = $connection.VolumeName
            "break-line" = "next-line"
        }
    $diskDetails += $record
}

$diskDetails