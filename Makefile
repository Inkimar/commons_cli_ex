SCHEMA = OcurrenceLit.mdb
PROGRAM = access2csv.jar

all: dl-schema clean install run

clean : clean-src clean-csv

clean-src:
	mvn clean

clean-csv:
	@echo "removes the csv-files"
	cd output && rm -v *.csv

install:
	@echo "Compiles and creates the access2csv.jar-file "
	mvn install
	sleep 5
run:
	@echo "Testing the project, running an example mdb-file"
	java -jar target/access2csv.jar -f "resources/${SCHEMA}" -d "output/"
	ls -l output

dl-schema:
	@echo "Fetching the example mdb-file if it does not exist"
	cd resources && test -e ${SCHEMA} || wget https://archive.org/download/OcurrenceLit/${SCHEMA}


