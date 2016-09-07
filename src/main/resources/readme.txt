https://sourceforge.net/projects/ucanaccess/
https://mvnrepository.com/artifact/net.sf.ucanaccess/ucanaccess/3.0.3

Install *.jar in your local repository
mvn install:install-file -Dfile=ucanaccess-3.0.6.jar -DgroupId=net.ucanacess -DartifactId=ucanaccess -Dversion=3.0.6 -Dpackaging=jar
renamed ucanload.jar to ucanload-3.0.6.jar
mvn install:install-file -Dfile=ucanload-3.0.6.jar -DgroupId=net.ucanacess -DartifactId=ucanload -Dversion=3.0.6 -Dpackaging=jar
