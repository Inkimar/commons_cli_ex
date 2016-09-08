https://www.mkyong.com/maven/create-a-fat-jar-file-maven-assembly-plugin/


https://sourceforge.net/projects/ucanaccess/

if there are problems with the maven-repository ( ucanaccess-3.0.6.jar and ucanload-3.0.6.jar )
it can be solve like this.
Targets in Makefile :
dl-jars: 
	@echo "Fetching the zip-file w. 2 jar-files needed for this project [not in maven-repo at this point in time]"
	cd dependencies &&  wget https://archive.org/download/OcurrenceLit/UCanAccess-3.0.6-bin.zip

install-jars:
	cd dependencies &&  unzip UCanAccess-3.0.6-bin.zip
	cp dependencies/UCanAccess-3.0.6-bin/loader/ucanload.jar dependencies/ucanload-3.0.6.jar
	cp dependencies/UCanAccess-3.0.6-bin/ucanaccess-3.0.6.jar dependencies
	sleep 5
	@echo "mvn install"
	cd dependencies && mvn install:install-file -Dfile=ucanaccess-3.0.6.jar -DgroupId=net.ucanacess -DartifactId=ucanaccess -Dversion=3.0.6 -Dpackaging=jar
	cd dependencies && mvn install:install-file -Dfile=ucanload-3.0.6.jar -DgroupId=net.ucanacess -DartifactId=ucanload -Dversion=3.0.6 -Dpackaging=ja


alt:
	@echo " work-in-progress : Just nu så har jar-filen access2csv.jar inte sina dependencies , de finns i katalogen xx"
	#mkdir target	
	#cd target && wget https://archive.org/download/access2csv/${PROGRAM}
	#java -jar target/access2csv.jar -f "resources/${SCHEMA}" -d "output/"

notes on .travis.yml
language: java
jdk:
- oraclejdk7
- openjdk6
