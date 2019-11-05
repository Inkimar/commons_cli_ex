[![Build Status](https://api.travis-ci.org/Inkimar/commons_cli_ex.svg?branch=master)](https://travis-ci.org/Inkimar/commons_cli_ex)

# What : To export  an Access database (mdb) to csv-files.
every table in the database is exported to one csv-file <br>
csv-files are saved in the output-directory.


# The artifact(s) created (jar)
Control of which artifacts are created is configured in the pom.xml with the 'shade'-plugin. <br>

2 binaries created in the 'target'-directory :

1. thin-jar : access2csv.jar (dependencies in directory )
2. fat-jar  : access2csv-jar-with-dependencies.jar (standalone runnable file)

https://archive.org/download/access2csv

**Tested with :**

1. Java version: 1.7.0_111 
2. Apache Maven 3.0.5


# Example database : 'OcurrenceLit.mdb'

The example database 'OcurrenceLit.mdb' is stored [here](https://archive.org/download/OcurrenceLit/OcurrenceLit.mdb)

1. **'make dl-schema'** : fetches the database from IA


# Makefile
### (A) run **'make'** (parses the default database)

1. downloads the example-databas  from IA (only if the db does not exist)
2. cleans out *.class-files and the *.csv-files
3. builds the project and creates a executeable access2csv.jar
4. runs the access2csv.jar-file, generates output in the 'output'-directory

### (B) run **'make run SCHEMA=Your.mdb'** <br>
where 'Your.mdb' is your schema, this will override the settings 'SCHEMA = OcurrenceLit.mdb' in the Makefile

prerequistite :

1. **'make install'** <br>
2. the schema (i.e Your.mdb) has to be in the 'resources'-directory


logs are created, <timestamp>.log

# additional docker-project
see -> https://github.com/bioatlas/access2csv-docker


# notes on travis.
https://docs.travis-ci.com/user/languages/java/


# reference URI:s (dependency on this library)

1. https://sourceforge.net/projects/ucanaccess/
2. https://mvnrepository.com/artifact/net.sf.ucanaccess/ucanaccess/3.0.3
3. CLI-programming : https://commons.apache.org/proper/commons-cli/ 


