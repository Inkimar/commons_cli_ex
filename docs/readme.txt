https://www.mkyong.com/maven/create-a-fat-jar-file-maven-assembly-plugin/

creating releases , github/travis
https://help.github.com/articles/creating-releases/

releases via git tag
git tag <tagname>
git push origin --tags
- blir en zip-fil och en tar.gz men de innehåller inte jar-filen

http://stackoverflow.com/questions/28174883/travis-ci-deploy-jar-to-github-master



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

notes on pom.xml ( dependencies in path ) 
<!--            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>2.4</version>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <mainClass>se.zanzibar.example.testingcli.Startup</mainClass>
                            <classpathPrefix>dependency-jars/</classpathPrefix>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <version>2.10</version>
                <executions>
                    <execution>
                        <id>copy-dependencies</id>
                        <phase>package</phase>
                        <goals>
                            <goal>copy-dependencies</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>
                                ${project.build.directory}/dependency-jars/
                            </outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>-->



