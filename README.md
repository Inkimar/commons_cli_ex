# URI:s
1. https://sourceforge.net/projects/ucanaccess/
2. https://mvnrepository.com/artifact/net.sf.ucanaccess/ucanaccess/3.0.3


#Install *.jar in your local repository
1. mvn install:install-file -Dfile=ucanaccess-3.0.6.jar -DgroupId=net.ucanacess -DartifactId=ucanaccess -Dversion=3.0.6 -Dpackaging=jar
2. mv ucanload.jar ucanload-3.0.6.jar
3. mvn install:install-file -Dfile=ucanload-3.0.6.jar -DgroupId=net.ucanacess -DartifactId=ucanload -Dversion=3.0.6 -Dpackaging=jar

java -jar access2csv.jar -f  "/home/ingimar/ucanaccess/UCanAccess-3.0.3-bin/OcurrenceLit.mdb"  -d "/home/ingimar/Downloads/Testing-new/"

