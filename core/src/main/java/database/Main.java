package database;

import database.api.Parameters;
import database.comparator.OptionComparator;
import org.apache.commons.cli.*;
import database.register.ParametersRegister;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import static java.lang.System.Logger.Level.INFO;
import static java.lang.System.getLogger;

public class Main {
    private static final System.Logger logger = getLogger(Main.class.getSimpleName());

    public static void main(String[] args) throws Exception {
        CommandLine cmd = parseArguments(args);

        if (isConfigurationInvalid(cmd)) {
            printHelp();
        }
    }

    protected static CommandLine parseArguments(String[] args) throws ParseException {
        Options options = new Options();
        for (Parameters parameters : ParametersRegister.getParameters()) {
            parameters.getAll().getOptions().forEach(options::addOption);
        }
        return new DefaultParser().parse(options, args);
    }

    protected static boolean isConfigurationInvalid(CommandLine cmd) {
        Options options = new Options();
        Arrays.stream(cmd.getOptions()).forEach(options::addOption);

        boolean noOptionsConfigured = options.getOptions().size() == 0;
        boolean noCommandsConfigured = ParametersRegister.getParameters().stream().noneMatch(p -> p.isConfigured(options));

        return noOptionsConfigured || noCommandsConfigured;
    }

    private static void printHelp() throws IOException {
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            HelpFormatter hf = new HelpFormatter();
            hf.setWidth(120);
            hf.setOptionComparator(new OptionComparator());
            ParametersRegister.getParameters().forEach(p -> {
                hf.printHelp(pw, hf.getWidth(), " ", null, p.getAll(), hf.getLeftPadding(), hf.getDescPadding(), null, true);
                pw.println();
            });
            logger.log(INFO, sw.toString());
        }
    }
}
