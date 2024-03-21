import os
import json
import argparse
import pathlib
import subprocess


def printCvssList(myList):
    # A crude method since no Python Tree structure exists to sort elements that are duplicates
    # Find the highest value in the list, print it then remove it, then repeat and search the reduced list
    print ("CVE            CVSSv3                DateAdded")
    print ("----------------------------------------------")

    while len(myList) > 0:
        highest = float(0.0)
        for  value in myList:
            cve, cvss, datea = value
            cvssAsFloat = float(cvss)
            if cvssAsFloat > highest: 
                highest = cvssAsFloat
                foundRecord = value
        cve, cvss, datea = foundRecord
        print(cve, "  ", cvss, "  ", datea)
        myList.remove(foundRecord)


def printCveDict(myDict):    
    print ("CVE            CVSSv3                DateAdded")
    print ("----------------------------------------------")
    for  index, value in sorted(myDict.items()):
       cve, cvss, datea = value
       print(cve, "  ", cvss, "  ", datea)
  


parser = argparse.ArgumentParser(description='Run a scan and list CVEs found')
parser.add_argument('--sort', choices= ['cve', 'cvss'], required=True,
                    help='field to sort on')
parser.add_argument('--scanpath', dest='scanpath', 
                    required=True, type=pathlib.Path,
                    help='directory path to scan')

args = parser.parse_args()

sort, scanpath = [args.sort, args.scanpath]

# print(os.getcwd())
# os.chdir("PythonScripts")

subprocess.run(["dependency-check.sh", "--out", "results.csv", "--format", "CSV", "--scan", scanpath])

cveFile = open('results.csv', 'r', encoding="utf-8")

# Identify column positions of interest from the header line so the code is not brittle
# with hardcoded positions and subject to future shifts in columns 
CveColumn = -1
Cvssv3Column = -1
DateAddedColumn = -1

firstLine = cveFile.readline()
listOfFields = firstLine.split(",")
i = 0
for field in listOfFields:
    # print (i, field)
    if field == "CVE":
        CveColumn = i
    if field == "DateAdded":
        DateAddedColumn = i
    if field == "CVSSv3_BaseScore":
        Cvssv3Column = i
    i = i + 1
if (CveColumn == -1 or Cvssv3Column == -1 or DateAddedColumn == -1):
    print ("Could not find all column headers")
    exit(1)

myDict = {}
cvssList = []
for line in cveFile:
    # Replace comma's within quotes. A necessary evil when working with the CSV file.
    # This allows the line to be split correctly into the appropriate columns.
    quoteToggle = False
    sanitizedline = ""    
    for i in range(0, len(line)):
 
        if line[i] == "\"":
            if (quoteToggle == True): quoteToggle = False 
            else: quoteToggle = True
        if (quoteToggle == True and line[i] == ","):
            sanitizedline = sanitizedline + " "
        else:
            sanitizedline = sanitizedline + line[i]
            
    field = sanitizedline.split(",")

    previousValue = myDict.get(field[CveColumn])
    if previousValue == None:
       # Put relevant columns in a tuple
       myTuple = (field[CveColumn], field[Cvssv3Column], field[DateAddedColumn] )
       
       # Put tuple in dictionary indexed by the CVE field.
       # Dictionary guarantees unique CVEs
       myDict[field[CveColumn]] = myTuple

       # Also put in CVSS list to sort by CVSS severity. In this case values need not be unique
       # but there is still one per CVE
       cvssList.append( myTuple )


if sort == "cve":
    printCveDict(myDict)
else:
    printCvssList(cvssList)






    

    


