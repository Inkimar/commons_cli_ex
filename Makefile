TS := $(shell date '+%Y_%m_%d_%H_%M')
SCHEMA = OcurrenceLit.mdb
#PROGRAM = access2csv.jar
PROGRAM = access2csv-jar-with-dependencies.jar

all: dl-schema clean install run

clean : clean-target

clean-target:
	@echo "using mvn : deletes the target directory"
	mvn clean

clean-csv:
	@echo "removes the csv- and log-files if any"
	#cd output && test -e '*.csv' || rm -v *.csv
	
clean-logs:
	#test -e '*.log' || rm -v *.log

install:
	@echo "using mvn : Compiles and creates the ${PROGRAM}-file "
	mvn install
	sleep 5

# override the schema by running 'make run SCHEMA=<your-name>.mdb , be sure that <your-name>.mdb is in the resources-directory '
run:
	@echo "Parsing the ${SCHEMA} database with ${PROGRAM}"
	java -jar target/${PROGRAM} -f "resources/${SCHEMA}" -d "output/"

	#touch ${TS}.log && echo "Database is ${SCHEMA}" > ${TS}.log && ls -l output >> ${TS}.log

dl-schema:
	@echo "Fetching the example mdb-file if it does not exist"
	cd resources && test -e ${SCHEMA} || wget https://archive.org/download/OcurrenceLit/${SCHEMA}

help: 
	firefox  https://www.gnu.org/software/make/manual/ &

testing:
	@echo "Default Database is ${SCHEMA}"

