package fr.varchar.varlib.util.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import static fr.varchar.varlib.util.logger.Color.*;

public class Logger {
    private String name;

    public static final String DEFAULT = "VarLib";

    public Logger(String name) {
        this.name = name;
    }

    public void log(String message, String color) {
        final String date = String.format("[%s]", new SimpleDateFormat("kk:mm:ss").format(new Date()));
        System.out.println(color == null ? date + String.format(" [%s] ", this.name) + message + RESET : color + date + String.format(" [%s] ", this.name) + message + RESET);
    }

    public void log(String message) {
        this.log(message, null);
    }

    public void logError(String message) {
        this.log(message, RED);
    }

    public void logWarn(String message) {
        this.log(message, YELLOW);
    }

    public void setName(String name) {
        this.name = name;
    }
}
