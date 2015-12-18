package se.nrm.mediaserver.testingcli;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author ingimar
 */
public class Test {

    /**
     * mvn clean package java -jar Testing.jar -t
     *
     * http://commons.apache.org/proper/commons-cli/
     *
     * @param args
     */
    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("t", false, "display current time");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("t")) {
                System.out.println("Date is " + new Date());
            } else {
                System.out.println("No No");
            }
        } catch (ParseException ex) {
            System.err.println("Parsing failed.  Reason: " + ex.getMessage());
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
