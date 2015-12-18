package se.zanzibar.example.testingcli;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Testing the Apache Commons cli project
 * http://commons.apache.org/proper/commons-cli/
 *
 * > mvn clean package > java -jar access2csv.jar -t
 *
 * @author ingimar
 */
public class Startup {

    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {

        Startup test = new Startup();
        
        CommandLine cmd = test.getCommand(args);
        if (cmd.hasOption("t")) {
            System.out.println("Date is " + new Date());
        } else {
            System.out.println("No time is given , use the '-t' option");
        }
    }

    private CommandLine getCommand(String[] args) {
        Options options = new Options();
        options.addOption("t", false, "display current time");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException ex) {
            System.err.println("Parsing failed.  Reason: " + ex.getMessage());
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cmd;
    }
}
