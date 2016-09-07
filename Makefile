SCHEMA = OcurrenceLit.mdb
PROGRAM = access2csv.jar

all: clean-src install run

clean : clean-src clean-output

clean-src:
	mvn clean

clean-output:
	@echo "removes the csv-files"
	cd output && rm -v *.csv

install:
	@echo "Compiles and creates the access2csv.jar-file "
	mvn install
	sleep 5

dl-schema:
	@echo "Fetching the example mdb-file"
	cd resources && wget https://archive.org/download/OcurrenceLit/${SCHEMA}

run:
	@echo "Testing the project, running an example mdb-file"
	java -jar target/access2csv.jar -f "resources/${SCHEMA}" -d "output/"


jars: dl-jars install-jars

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
	cd dependencies && mvn install:install-file -Dfile=ucanload-3.0.6.jar -DgroupId=net.ucanacess -DartifactId=ucanload -Dversion=3.0.6 -Dpackaging=jar

alt:
	@echo " work-in-progress : Just nu så har jar-filen access2csv.jar inte sina dependencies , de finns i katalogen xx"
	#mkdir target	
	#cd target && wget https://archive.org/download/access2csv/${PROGRAM}
	#java -jar target/access2csv.jar -f "resources/${SCHEMA}" -d "output/"
