package se.zanzibar.example.testingcli;

import java.util.Date;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
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

    private String nameOfApp = "access2csv";

    private String suffixOfApp = ".jar";

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        Startup start = new Startup();

        CommandLine line = start.getCommand(args);
        if (line.hasOption("t")) {
            System.out.println("Date is " + new Date());
        }

    }

    private CommandLine getCommand(String[] arguments) {
        String header = "This is ".concat(nameOfApp).concat(", a command to convert MS access data to .csv. \n\n")
                .concat("Mimimal use example:\n")
                .concat("java -jar ").concat(nameOfApp+suffixOfApp).concat(" my.accessdb\n\n")
                .concat("This will dump all tables in the my.accessdb in the current directory,\n")
                .concat("generating one .csv file per table.\n\n")
                .concat("Available options:");

        String footer = "\nPlease report issues at http://http://dina-project.net/wiki/";
        CommandLine cmd = null;

        try {
            Options options = new Options();
           
            options.addOption("f","file", false, "the .mdb or .accdb database file");
            options.addOption("t","table", false, "if provided only the specified table will be dumped, the default is to dump all tables except the system tables");
            options.addOption("cd","column_delimiter", false, "default is ,");
            options.addOption("rd","row_delimiter", false, "default is \\n");
            options.addOption("qc","quote-character", false, "default is \" ");
            options.addOption("qe","quote-escape", false, "default is \\ - used for escaping quote characters that appears within a field");
            options.addOption("nq","no-quoting", false, "activates a mode where text values are not quoted, which all text values are by default");
            options.addOption("st","strip", false, "actives a mode where wierd characters are stripped from the output (in Access these can appear for example in MEMO fields)");
            options.addOption("list","list-all-tables", false, "does not dump data into .csv, instead outputs a tab delimited list of " +
"all table names, excluding system tables (ie excludes any tables beginning with \"MSys\")");
            options.addOption("s","schema", false, "outputs the DDL including indexes, relations etc in sqlite DDL sql dialect");

            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(nameOfApp+suffixOfApp, header, options, footer, true);

            CommandLineParser parser = new DefaultParser();
            cmd = parser.parse(options, arguments);
        } catch (ParseException ex) {
            System.err.println("Parsing failed.  Reason: " + ex.getMessage());
        }

        return cmd;
    }
}
