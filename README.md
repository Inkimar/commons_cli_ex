# To export  an Access database (mdb) to csv-files.
every table is export to one csv-file <br>
csv-files are saved in the output-directory.


# reference URI:s
1. https://sourceforge.net/projects/ucanaccess/
2. https://mvnrepository.com/artifact/net.sf.ucanaccess/ucanaccess/3.0.3

# Internet Archive (IA)
2 files.

1. example database : OcurrenceLit.mdb
2. dependencies : UCanAccess-3.0.6-bin.zip 

# Makefile
Workaround for 2 dependencies, due to that those are not in mvn-central

##  dependencies
UCanAccess-3.0.6-bin.zip  has 2 important jar-files.

1. ucanaccess-3.0.6.jar
2. ucanload-3.0.6.jar

## download and install dependencies
You only have to do this one.

**'make jars'** fetches jar-files and installs them in local repo (~/.m2/repository/)

## run the example database schema
**'make'**


