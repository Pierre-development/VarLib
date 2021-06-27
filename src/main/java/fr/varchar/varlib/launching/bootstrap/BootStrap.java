package fr.varchar.varlib.launching.bootstrap;

import fr.varchar.varlib.exceptions.LaunchingException;
import fr.varchar.varlib.util.logger.Logger;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static fr.varchar.varlib.util.logger.Color.GREEN;

public class BootStrap {
    private final Path dir;
    private final Logger logger = new Logger(Logger.DEFAULT);
    private final List<String> allArgs = new ArrayList<>();


    public BootStrap(String mcDir, String bootStrapDir) {
        if (System.getProperty("os.name").startsWith("Win")) {
            this.dir = Paths.get(System.getenv("appdata") + FileSystems.getDefault().getSeparator() + "." + mcDir + FileSystems.getDefault().getSeparator() + bootStrapDir);
        } else {
            this.dir = Paths.get(System.getProperty("user.home") + FileSystems.getDefault().getSeparator() + "." + mcDir + FileSystems.getDefault().getSeparator() + bootStrapDir);
        }

        if(!Files.exists(this.dir)) {
            try {
                Files.createDirectories(this.dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.allArgs.addAll(Args.defaultVmArgs());
        this.allArgs.addAll(Args.defaultArgs(this));
    }



    public void launch() throws LaunchingException {
        final ProcessBuilder processBuilder = new ProcessBuilder(this.allArgs);
        processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

        final StringBuilder sb = new StringBuilder();
        for (String string : this.allArgs) {
            sb.append(string + " ");
        }
        logger.log(sb.toString(), GREEN);

        try {
            processBuilder.start();
        } catch (IOException e) {
            throw new LaunchingException("can't start the process builder", e);
        }
    }

    public Path getDir() {
        return dir;
    }
}
