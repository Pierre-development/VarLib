package fr.varchar.varlib.util.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import static fr.varchar.varlib.util.logger.Color.*;

public class Logger {
    private final String name;

    public static final String DEFAULT = "VarLib";

    /**
     * Constructor of the logger.
     * @param name set the name of the logger.
     */
    public Logger(String name) {
        this.name = name;
    }

    /**
     *
     * @param color set the color's message with {@link Color}
     */
    public void log(String message, String color) {
        final String date = String.format("[%s]", new SimpleDateFormat("kk:mm:ss").format(new Date()));
        System.out.println(color == null ? date + String.format(" [%s] ", this.name) + message + RESET : color + date + String.format(" [%s] ", this.name) + message + RESET);
    }

    /**
     * log without colors.
     */
    public void log(String message) {
        this.log(message, null);
    }

    /**
     * log used for errors in red.
     */
    public void logError(String message) {
        this.log(message, RED);
    }

    /**
     * log used for warns in yellow.
     */
    public void logWarn(String message) {
        this.log(message, YELLOW);
    }
}
