[![Build Status](https://api.travis-ci.org/Inkimar/commons_cli_ex.svg?branch=master)](https://api.travis-ci.org/Inkimar/commons_cli_ex)

# What : To export  an Access database (mdb) to csv-files.
every table in the database is exported to one csv-file <br>
csv-files are saved in the output-directory.


# reference URI:s (dependency on this library)
1. https://sourceforge.net/projects/ucanaccess/
2. https://mvnrepository.com/artifact/net.sf.ucanaccess/ucanaccess/3.0.3
3. CLI-programming : https://commons.apache.org/proper/commons-cli/ 

# Internet Archive (IA)
The example database is stored here ( OcurrenceLit.mdb )

1. **'make dl-schema'** : fetches the database from IA


# Makefile
run **'make'**

1. downloads the example-databas  from IA (only if the db does not exist)
2. cleans out *.class-files and the *.csv-files
3. builds the project and creates a executeable access2csv.jar
4. runs the access2csv.jar-file, generates output in the 'output'-directory

logs are created, <timestamp>.log

## Run your own mdb-file.

1. Obs : Put your mdb-file (Your.mdb) in the 'resources'-directory
2. **'make run SCHEMA=Your.mdb'**


