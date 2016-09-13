package se.zanzibar.example.testingcli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import se.zanzibar.example.testingcli.util.FileHelper;

/**
 * Testing the Apache Commons cli project
 * http://commons.apache.org/proper/commons-cli/
 * https://sourceforge.net/projects/ucanaccess/
 *
 *
 * @author ingimar
 */
public class Startup {

    private final String APPLICATION_NAME = "access2csv";

    private final String APPLICATION_SUFFIX = ".jar";

    private final String FILE = "file";

    private final String PATH_TO_DIRECTORY = "dir";

    private final String TABLE = "table";

    private final String COLUMN_DELIMITER = "column_delimiter";

    private final String ROW_DELIMITER = "row_delimiter";

    private final String QUOTE_CHARACTER = "quote-character";

    private final String QUOTE_ESCAPE = "quote-escape";

    private final String NO_QUOTING = "no-quoting";

    private final String STRIP = "strip";

    private final String LIST_ALL_TABLES = "list-all-tables";

    private final String SCHEMA = "schema";

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Startup start = new Startup();
        CommandLine line = start.getCommand(args);
        start.parsing(line);
    }

    private void parsing(CommandLine line) {
        String pathToDatabaseFile = "empty";
        if (line.hasOption(this.FILE)) {
            pathToDatabaseFile = line.getOptionValue(this.FILE);
            System.out.print("\n--file " + pathToDatabaseFile);
            checkFile(pathToDatabaseFile);
        }
        String pathToDirectory = "empty";
        if (line.hasOption(this.PATH_TO_DIRECTORY)) {
            pathToDirectory = line.getOptionValue(this.PATH_TO_DIRECTORY);
            System.out.print("\n--dir " + pathToDirectory);
            checkDirectory(pathToDirectory);
        }

        String url = "jdbc:ucanaccess://".concat(pathToDatabaseFile);
        this.accessDatabase(url, pathToDirectory);
    }

    private CommandLine getCommand(String[] arguments) {
        String header = "This is ".concat(APPLICATION_NAME).concat(", a command to convert MS access data to .csv. \n\n")
                .concat("Mimimal use example:\n")
                .concat("java -jar ").concat(APPLICATION_NAME + APPLICATION_SUFFIX).concat(" my.accessdb\n\n")
                .concat("This will dump all tables in the my.accessdb in the current directory,\n")
                .concat("generating one .csv file per table.\n\n")
                .concat("Available options:");

        String footer = "\nPlease report issues at http://http://dina-project.net/wiki/";
        CommandLine cmd = null;

        try {
            Options options = new Options();

            options.addOption("f", this.FILE, true,
                    "the .mdb or .accdb database file"); // has key value
            options.addOption("d", this.PATH_TO_DIRECTORY, true,
                    "the path to the directory where the .csv-file(s) will be stored"); // has key value
            options.addOption("t", "table", false,
                    "if provided only the specified table will be dumped, the default is to dump all tables except the system tables");
            options.addOption("cd", this.COLUMN_DELIMITER, false,
                    "default is ,");
            options.addOption("rd", this.ROW_DELIMITER, false,
                    "default is \\n");
            options.addOption("qc", this.QUOTE_CHARACTER, false,
                    "default is \" ");
            options.addOption("qe", this.QUOTE_ESCAPE, false,
                    "default is \\ - used for escaping quote characters that appears within a field");
            options.addOption("nq", this.NO_QUOTING, false,
                    "activates a mode where text values are not quoted, which all text values are by default");
            options.addOption("st", this.STRIP, false,
                    "actives a mode where wierd characters are stripped from the output (in Access these can appear for example in MEMO fields)");
            options.addOption("list", this.LIST_ALL_TABLES, false,
                    "does not dump data into .csv, instead outputs a tab delimited list of all table names, excluding system tables (ie excludes any tables beginning with \"MSys\")");
            options.addOption("s", this.SCHEMA, false,
                    "outputs the DDL including indexes, relations etc in sqlite DDL sql dialect");

            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(APPLICATION_NAME + APPLICATION_SUFFIX, header, options, footer, true);

            CommandLineParser parser = new DefaultParser();
            cmd = parser.parse(options, arguments);
        } catch (ParseException ex) {
            System.err.println("Parsing failed.  Reason: " + ex.getMessage());
        }

        return cmd;
    }

    private void accessDatabase(String url, String path) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conn = DriverManager.getConnection(url);
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            List<String> tables = new ArrayList<>();

            Statement stmt = conn.createStatement();
            ResultSet rsColumns = null;
            DatabaseMetaData meta = conn.getMetaData();
            Map<String, Integer> abc = new HashMap<>();
            while (rs.next()) {
                String table = rs.getString(3);
                rsColumns = meta.getColumns(null, null, table, null);
                int count = 0;
                while (rsColumns.next()) {
                    count++;
                }
                tables.add(table);
                abc.put(table, count);
//                System.out.println("Number of Columns " + count);
            }

            for (Map.Entry<String, Integer> entrySet : abc.entrySet()) {
                String table = entrySet.getKey();
                Integer value = entrySet.getValue();
                String absolutePath = createFile(path + table);
                handlingTable(stmt, table, value, absolutePath);

            }

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("exception" + ex.getMessage());
        }
    }

    private static void handlingTable(Statement stmt, String table, int nrOfColumns, String absolutePath) {
        try {
            ResultSet rask = stmt.executeQuery("SELECT * FROM " + table);
            int post = 0;
            while (rask.next()) {
                post++;
                String row = "";
                for (int i = 1; i < nrOfColumns + 1; i++) {
                    String wash = simpleWash(rask.getString(i));
                    row = row + wash + ",";

                }
                row = row.substring(0, row.length() - 1);
                row = row + "\n";

                write(absolutePath, row);
            }
        } catch (SQLException ex) {
            System.err.println("SQLException" + ex.getMessage());
        }
    }

    private static String simpleWash(String row) {
        final String CONSTANT = "\n";
        char CHAR_CONSTANT = CONSTANT.charAt(0);
        
        if (row == null) {
            return row;
        } else if (row.contains(CONSTANT)) {
            String replace = row;
            replace = row.replace(CHAR_CONSTANT, '$');
            return replace;
        }

        return row;
    }

    private static void write(String absPath, String content) {
        try {
            Files.write(Paths.get(absPath), content.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
        }
    }

    private boolean checkFile(String absolutePath) {
        return FileHelper.checkFile(absolutePath);
    }

    private boolean checkDirectory(String value) {
        return FileHelper.checkDirectory(value);
    }

    private static String createFile(String absoluteFilename) {
        return FileHelper.createOutputFile(absoluteFilename);
    }
}
