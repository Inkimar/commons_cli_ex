all: clean-src install run

clean-src:
	mvn clean

clean-csv:
	cd output && rm -v *.csv

install:
	mvn install
	sleep 5

run:
	java -jar target/access2csv.jar -f "resources/OcurrenceLit.mdb" -d "output/"

dl :
	cd resources && wget https://archive.org/download/OcurrenceLit/OcurrenceLit.mdb
