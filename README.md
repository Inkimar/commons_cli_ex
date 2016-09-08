# What : To export  an Access database (mdb) to csv-files.
every table is export to one csv-file <br>
csv-files are saved in the output-directory.


# reference URI:s
1. https://sourceforge.net/projects/ucanaccess/
2. https://mvnrepository.com/artifact/net.sf.ucanaccess/ucanaccess/3.0.3

# Internet Archive (IA)
2 files are stored there that are used in the project

1. example database : OcurrenceLit.mdb (**'make dl-schema'**)
2. dependencies : UCanAccess-3.0.6-bin.zip (**'make jars'**)

UCanAccess-3.0.6-bin.zip  has 2 important jar-files.

1. ucanaccess-3.0.6.jar
2. ucanload-3.0.6.jar

# Makefile
Run the 3 targets 
1. **'make jars'** 
2. **'make dl-schema'**
3. **'make'**

