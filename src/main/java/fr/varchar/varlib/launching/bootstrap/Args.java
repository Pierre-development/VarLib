package fr.varchar.varlib.launching.bootstrap;

import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

public class Args {

    public static List<String> defaultVmArgs() {
        final List<String> vmArgs = new ArrayList<>();
        vmArgs.add("java");
        vmArgs.add("-jar");
        return vmArgs;
    }

    public static List<String> defaultArgs(BootStrap bootStrap) {
        final List<String> args = new ArrayList<>();
        args.add(bootStrap.getDir().toAbsolutePath() + FileSystems.getDefault().getSeparator() + "launcher.jar");
        return args;
    }

}
