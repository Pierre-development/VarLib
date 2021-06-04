package fr.varchar.varlib.launching.bootstrap;

import fr.varchar.varlib.exceptions.LaunchingException;
import fr.varchar.varlib.util.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static fr.varchar.varlib.util.logger.Color.GREEN;

public class BootStrap {
    private final File dir;
    private final Logger logger = new Logger(Logger.DEFAULT);
    private final List<String> allArgs = new ArrayList<>();


    public BootStrap(String mcDir, String bootStrapDir) {
        if (System.getProperty("os.name").startsWith("Win")) {
            this.dir = new File(System.getenv("appdata") + File.separator + "." + mcDir, bootStrapDir);
        } else {
            this.dir = new File(System.getProperty("user.home") + File.separator + "." + mcDir, bootStrapDir);
        }
        if(!this.dir.exists()) {
            this.dir.mkdirs();
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

    public File getDir() {
        return dir;
    }
}
