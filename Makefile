SCHEMA = OcurrenceLit.mdb
PROGRAM = access2csv.jar
TS := $(shell date '+%Y_%m_%d_%H_%M')

all: dl-schema clean install run

clean : clean-src clean-csv

clean-src:
	mvn clean

clean-csv:
	@echo "removes the csv- and log-files if any"
	cd output && test -e || rm -v *.csv
	
clean-logs:
	test -e '*.log' || rm -v *.log

install:
	@echo "Compiles and creates the access2csv.jar-file "
	mvn install
	sleep 5

# override the schema by running 'make run SCHEMA=<your-name>.mdb , be sure that <your-name>.mdb is in the resources-directory '
run:
	@echo "Parsing the ${SCHEMA} database"
	java -jar target/access2csv.jar -f "resources/${SCHEMA}" -d "output/"
	touch ${TS}.log && echo "Database is ${SCHEMA}" > ${TS}.log && ls -l output >> ${TS}.log

dl-schema:
	@echo "Fetching the example mdb-file if it does not exist"
	cd resources && test -e ${SCHEMA} || wget https://archive.org/download/OcurrenceLit/${SCHEMA}

help: 
	firefox  https://www.gnu.org/software/make/manual/ &

testing:
	@echo "Database is ${SCHEMA}"

