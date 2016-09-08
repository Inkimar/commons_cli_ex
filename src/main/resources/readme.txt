https://sourceforge.net/projects/ucanaccess/
https://mvnrepository.com/artifact/net.sf.ucanaccess/ucanaccess/3.0.3

if there are problems with the maven-repository ( ucanaccess-3.0.6.jar and ucanload-3.0.6.jar )
it can be solve like this.

install-jars:
	cd dependencies &&  unzip UCanAccess-3.0.6-bin.zip
	cp dependencies/UCanAccess-3.0.6-bin/loader/ucanload.jar dependencies/ucanload-3.0.6.jar
	cp dependencies/UCanAccess-3.0.6-bin/ucanaccess-3.0.6.jar dependencies
	sleep 5
	@echo "mvn install"
	cd dependencies && mvn install:install-file -Dfile=ucanaccess-3.0.6.jar -DgroupId=net.ucanacess -DartifactId=ucanaccess -Dversion=3.0.6 -Dpackaging=jar
	cd dependencies && mvn install:install-file -Dfile=ucanload-3.0.6.jar -DgroupId=net.ucanacess -DartifactId=ucanload -Dversion=3.0.6 -Dpackaging=ja