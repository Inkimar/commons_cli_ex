package se.zanzibar.example.testingcli;

import java.util.Date;
import java.util.List;
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

    private final String nameOfApp = "access2csv";

    private final String suffixOfApp = ".jar";
    
    private final String FILE ="file";
    private final String TABLE ="table";
    private final String COLUMN_DELIMITER ="column_delimiter";
    private final String ROW_DELIMITER ="row_delimiter";
    private final String QUOTE_CHARACTER ="quote-character";
    private final String QUOTE_ESCAPE ="quote-escape";
    private final String NO_QUOTING ="no-quoting";
    private final String STRIP ="strip";
    private final String LIST_ALL_TABLES ="list-all-tables";
    private final String SCHEMA ="schema";
    private final String PATH_TO_DIRECTORY ="directory";

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        Startup start = new Startup();

        CommandLine line = start.getCommand(args);
        start.parsing(line);
//        if (line.hasOption("file")) {
//            String optionValue = line.getOptionValue("file");
//            System.out.println("optionValue --file "+optionValue);
//        }

    }
    
    private void parsing(CommandLine line){
        if (line.hasOption(this.FILE)) {
            String optionValue = line.getOptionValue(this.FILE);
            System.out.println("optionValue for --file "+optionValue);
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
           
            options.addOption("f",this.FILE, true, 
                    "the .mdb or .accdb database file"); // has key value
            options.addOption("d",this.PATH_TO_DIRECTORY, true, 
                    "the path to the directory where the .csv-file(s) will be stored"); // has key value
            options.addOption("t","table", false, 
                    "if provided only the specified table will be dumped, the default is to dump all tables except the system tables");
            options.addOption("cd",this.COLUMN_DELIMITER, false, 
                    "default is ,");
            options.addOption("rd",this.ROW_DELIMITER, false, 
                    "default is \\n");
            options.addOption("qc",this.QUOTE_CHARACTER, false, 
                    "default is \" ");
            options.addOption("qe",this.QUOTE_ESCAPE, false, 
                    "default is \\ - used for escaping quote characters that appears within a field");
            options.addOption("nq",this.NO_QUOTING, false, 
                    "activates a mode where text values are not quoted, which all text values are by default");
            options.addOption("st",this.STRIP, false, 
                    "actives a mode where wierd characters are stripped from the output (in Access these can appear for example in MEMO fields)");
            options.addOption("list",this.LIST_ALL_TABLES, false, 
                    "does not dump data into .csv, instead outputs a tab delimited list of all table names, excluding system tables (ie excludes any tables beginning with \"MSys\")");
            options.addOption("s",this.SCHEMA, false, 
                    "outputs the DDL including indexes, relations etc in sqlite DDL sql dialect");

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
