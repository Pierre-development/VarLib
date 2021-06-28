package fr.varchar.varlib;

import fr.varchar.varlib.authenticate.mojang.GameAuthenticator;
import fr.varchar.varlib.exceptions.LaunchingException;
import fr.varchar.varlib.launching.types.Type;
import fr.varchar.varlib.launching.types.VersionType;
import fr.varchar.varlib.util.Util;
import fr.varchar.varlib.util.logger.Color;
import fr.varchar.varlib.util.logger.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameLauncher {

    private final Path dir;
    private final List<String> allArgs = new ArrayList<>();
    private final List<String> args = new ArrayList<>();
    private final List<String> vmArgs = new ArrayList<>();
    private final List<String> classpath = new ArrayList<>();
    private final String version;
    private final Path assetsDir;
    private final Path nativesDir;
    private final Path librariesDir;
    private final Path minecraftClient;
    private final VersionType versionType;
    private final Type type;
    private final GameAuthenticator gameAuthenticator;
    private String fmlForgeVersion;
    private String fmlmcVersion;
    private String fmlmcpVersion;
    private final Logger logger = new Logger(Logger.DEFAULT);


    public GameLauncher(String dir, String version, VersionType versionType, Type type, FolderType folderType, GameAuthenticator gameAuthenticator) {
        if (System.getProperty("os.name").startsWith("Win")) {
            this.dir = Paths.get(System.getenv("appdata") + FileSystems.getDefault().getSeparator() + "." + dir);
        } else {
            this.dir = Paths.get(System.getProperty("user.home") + FileSystems.getDefault().getSeparator() + "." + dir);
        }


        if (!Files.exists(this.dir)) {
            try {
                Files.createDirectories(this.dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.assetsDir = Paths.get(this.dir.toAbsolutePath() + FileSystems.getDefault().getSeparator() + folderType.getAssetsDir());
        this.nativesDir = Paths.get(this.dir.toAbsolutePath() + FileSystems.getDefault().getSeparator() + folderType.getNativesDir());
        this.librariesDir = Paths.get(this.dir.toAbsolutePath() + FileSystems.getDefault().getSeparator() + folderType.getLibrariesDir());
        this.minecraftClient = Paths.get(this.dir.toAbsolutePath() + FileSystems.getDefault().getSeparator() + folderType.getMinecraftClient());

        this.versionType = versionType;
        this.type = type;
        this.version = version;
        this.gameAuthenticator = gameAuthenticator;

        this.vmArgs.addAll(this.type.getArgs(this));
        this.args.add(this.getType().getMainClass(this));
    }

    public GameLauncher(String dir, String version, VersionType versionType, Type type, FolderType folderType, GameAuthenticator gameAuthenticator, String fmlForgeVersion, String fmlmcVersion, String fmlmcpVersion) {
        this(dir, version, versionType, type, folderType, gameAuthenticator);
        this.fmlForgeVersion = fmlForgeVersion;
        this.fmlmcVersion = fmlmcVersion;
        this.fmlmcpVersion = fmlmcpVersion;
    }

    public void launch() throws LaunchingException {
        logger.log("This library was created by VarChar | the discord: https://discord.com/invite/CjfZQye3GV (THIS IS NOT AN ERROR)", Color.RED);
        final ProcessBuilder processBuilder = new ProcessBuilder(this.allArgs);
        processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        try {
            Util.checkDirs(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.args.addAll(this.versionType.getArgs(this, gameAuthenticator));
        this.allArgs.addAll(this.vmArgs);
        this.classpath.addAll(this.type.getClasspath(this));
        this.allArgs.addAll(this.classpath);
        this.allArgs.addAll(this.args);


        final StringBuilder sb = new StringBuilder();
        for (String string : this.allArgs) {
            sb.append(string + " ");
        }

        System.out.println(sb);
        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new LaunchingException("can't start Minecraft client", e);
        }
    }

    public Path getDir() {
        return dir;
    }

    public List<String> getArgs() {
        return args;
    }

    public List<String> getVmArgs() {
        return vmArgs;
    }

    public List<String> getClasspath() {
        return classpath;
    }

    public Path getNativesDir() {
        return nativesDir;
    }

    public String getVersion() {
        return this.version;
    }

    public Path getAssetsDir() {
        return assetsDir;
    }

    public Path getLibrariesDir() {
        return librariesDir;
    }

    public Path getMinecraftClient() {
        return minecraftClient;
    }

    public Type getType() {
        return type;
    }

    public VersionType getVersionType() {
        return versionType;
    }

    public String getFmlForgeVersion() {
        return fmlForgeVersion;
    }

    public String getFmlmcVersion() {
        return fmlmcVersion;
    }

    public String getFmlmcpVersion() {
        return fmlmcpVersion;
    }


}
