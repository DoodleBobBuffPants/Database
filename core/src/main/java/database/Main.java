package database;

import database.api.DatabaseOptions;
import database.comparator.OptionComparator;
import database.register.DatabaseOptionsRegister;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static database.cli.CliDatabaseOptions.HELP;
import static java.lang.System.Logger.Level.INFO;
import static java.lang.System.getLogger;

public class Main {
    private static final System.Logger logger = getLogger(Main.class.getSimpleName());

    public static void main(String[] args) throws Exception {
        CommandLine cmd = parseArguments(args);
        if (cmd.hasOption(HELP)) printHelp();
    }

    static CommandLine parseArguments(String[] args) throws ParseException {
        Options options = new Options();
        for (DatabaseOptions databaseOptions : DatabaseOptionsRegister.get()) databaseOptions.getAll().getOptions().forEach(options::addOption);
        return new DefaultParser().parse(options, args);
    }

    private static void printHelp() throws IOException {
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            HelpFormatter hf = new HelpFormatter();
            hf.setWidth(120);
            hf.setOptionComparator(new OptionComparator());
            DatabaseOptionsRegister.get().forEach(o -> {
                hf.printHelp(pw, hf.getWidth(), " ", null, o.getAll(), hf.getLeftPadding(), hf.getDescPadding(), null, true);
                pw.println();
            });
            logger.log(INFO, sw.toString());
        }
    }
}
