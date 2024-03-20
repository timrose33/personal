#!/bin/bash
usage() {
   echo "Usage:"
   echo "unique_cves.sh <pathname>"
   echo "    Where pathname is the directory to be scanned"
}

if [ -z "$1" ]; then 
    # missing pathname parameter
    usage 
    exit 1 
else 
    test -d $1 
    if [ $? -ne 0 ]; then
       echo "$1 is not a valid directory"
       exit 1
    fi
fi

dependency-check.sh --out results.csv --format CSV --scan $1 > null
if [ $? -eq 0 ]; then
   echo "These unique CVEs were found in the scan:"
   # The CVEs will appear on either column 12 or 13 of the dependency checker output
  awk -F, '{if ($12 ~ /CVE-([0-9])/) arr[$12]++} {if ($13 ~ /CVE-([0-9])/) arr[$13]++} END {for (i in arr){print	i} }' results.csv	
  rm results.csv
else
     echo "Error running dependency-check.sh. Scan did not run."
     exit 1
fi




