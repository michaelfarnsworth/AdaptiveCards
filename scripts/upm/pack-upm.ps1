# Copyright (c) Microsoft Corporation.
# Licensed under the MIT License.
<#
.SYNOPSIS
    Builds the Adaptive Cards .NET Unity Package Manager (UPM) package.
.DESCRIPTION
    Builds UPM packages for the Adaptive Cards .NET.
.PARAMETER ProjectRoot
    The root folder of the project.
.PARAMETER OutputDirectory
    Where should we place the output? Defaults to ".\artifacts"
.PARAMETER Version
    What version of the artifacts should we build?
.PARAMETER BuildNumber
    The fourth digit (revision) for the full version.
#>
param(
    [Parameter(Mandatory = $true)]
    [string]$ProjectRoot,

    [string]$OutputDirectory = "./artifacts/upm",

    [ValidatePattern("\d+\.\d+\.\d+")]
    [string]$Version,

    [ValidatePattern("\d+")]
    [string]$BuildNumber
)

if ( $Verbose ) { $VerbosePreference = 'Continue' }

$ProjectRoot = Resolve-Path -Path $ProjectRoot

if ($PreviewTag) {
    $PreviewTag = "-$PreviewTag"
    Write-Output "Version preview: $PreviewTag"
}

if ($BuildNumber) {
    $BuildNumber = ".$BuildNumber"
}

if (-not (Test-Path $OutputDirectory -PathType Container)) {
    New-Item $OutputDirectory -ItemType Directory | Out-Null
}

$OutputDirectory = Resolve-Path -Path $OutputDirectory
Write-Verbose "OutputDirectory: $OutputDirectory"

$TempDirectory = "$OutputDirectory/AdaptiveCards"
Write-Verbose "TempDirectory: $TempDirectory"

try {
    Push-Location $OutputDirectory

    $parseVersion = -not $Version

    $packages = Get-ChildItem -Path $ProjectRoot/source/dotnet/Library/AdaptiveCards/package.json
    if ($packages.Count -ne 1) {
        Write-Output "Error looking for package at $ProjectRoot/source/dotnet/Library/AdaptiveCards/package.json. Expecting 1 package, found $packages.Count"
        return;
    }

    $package = $packages[0];
    $packagePath = $package.Directory

    $metaFilesRootDirectory = "$ProjectRoot/scripts/upm/meta-files/"

    $folderFullPath = "$packagePath/*"

    $excludelist = "$packagePath\docs", "$packagePath\AdaptiveCards.csproj"

    # Copy the desired package files to a temp directory
    (Get-ChildItem -Path "$folderFullPath") | Where { $excludeList -notcontains $_ } | ForEach-Object {
        if (-not(Test-Path "$TempDirectory")) {
            Write-Verbose "Creating temp folder: $TempDirectory"
            New-Item "$TempDirectory" -ItemType Directory
        }

        Copy-Item -Path $_.FullName -Destination "$TempDirectory" -Force -Recurse
    }

    # Copy the associated .meta files
    (Get-ChildItem -Path "$metaFilesRootDirectory/*" -Filter "*.meta" -Recurse) | ForEach-Object {

        $rootDir = $metaFilesRootDirectory
        $currentDir = $_.Directory.FullName
        $tmp = Get-Location
        Set-Location $rootDir
        $relativePath = Resolve-Path -relative $currentDir
        Copy-Item -Path $_.FullName -Destination "$OutputDirectory/$relativePath" -Force
        Set-Location $tmp
    }

    Write-Output "======================="
    Write-Output "Creating com.microsoft.adaptivecards.net"
    Write-Output "======================="
    npm pack "$TempDirectory"

    Write-Verbose "Cleaning temp files"
    Remove-Item -Path "$TempDirectory" -Recurse -Force
}
finally {
    Pop-Location
}