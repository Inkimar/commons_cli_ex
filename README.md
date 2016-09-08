# What : To export  an Access database (mdb) to csv-files.
every table in the database is exported to one csv-file <br>
csv-files are saved in the output-directory.


# reference URI:s (dependency on this library)
1. https://sourceforge.net/projects/ucanaccess/
2. https://mvnrepository.com/artifact/net.sf.ucanaccess/ucanaccess/3.0.3

# Internet Archive (IA)
The example database is stored here ( OcurrenceLit.mdb )

1. **'make dl-schema'** : fetches the database from IA


# Makefile
## Run the example, following 2 targets 

2. **'make dl-schema'**
3. **'make'**

## Run your own mdb-file.

1. put your mdb-file (Your.mdb) in the 'resources'-directory
2. edit the Makefile, change the variable 'SCHEMA=OcurrenceLit.mdb' to 'SCHEMA=Your.mdb'.
3. **'make'***


