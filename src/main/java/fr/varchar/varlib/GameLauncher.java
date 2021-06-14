package fr.varchar.varlib;

import fr.varchar.varlib.authenticate.mojang.GameAuthenticator;
import fr.varchar.varlib.exceptions.LaunchingException;
import fr.varchar.varlib.launching.types.Type;
import fr.varchar.varlib.launching.types.VersionType;
import fr.varchar.varlib.util.Util;
import fr.varchar.varlib.util.logger.Color;
import fr.varchar.varlib.util.logger.Logger;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameLauncher {

    private final File dir;
    private final List<String> allArgs = new ArrayList<>();
    private final List<String> args = new ArrayList<>();
    private final List<String> vmArgs = new ArrayList<>();
    private final List<String> classpath = new ArrayList<>();
    private final String version;
    private final File assetsDir;
    private final File nativesDir;
    private final File librariesDir;
    private final File minecraftClient;
    private final VersionType versionType;
    private final Type type;
    private String fmlForgeVersion;
    private String fmlmcVersion;
    private String fmlmcpVersion;
    private final Logger logger = new Logger(Logger.DEFAULT);
    
     /**
     * Generate the game directory of the current OS by the given
     * server name, like the default of Minecraft. Code from OpenLauncherLib
     *
     * @param serverName The server name that will be the directory
     *                   name.
     * @return The generated game directory
     */
    public static File createGameDir(String serverName)
    {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
            return new File(System.getProperty("user.home") + "\\AppData\\Roaming\\." + serverName);
        else if (os.contains("mac"))
            return new File(System.getProperty("user.home") + "/Library/Application Support/" + serverName);
        else
            return new File(System.getProperty("user.home") + "/.local/share/." + serverName);
    }

    public GameLauncher(String dir, String version, VersionType versionType, Type type, FolderType folderType) {
        logger.log("Cette librairie a \u00E9t\u00E9 cr\u00E9\u00E9e par VarChar | le discord: https://discord.com/invite/CjfZQye3GV (THIS IS NOT AN ERROR)", Color.RED);
        this.dir = createGameDir(dir);


        if(!this.dir.exists()) {
            this.dir.mkdirs();
        }

        this.assetsDir = new File(this.dir.getAbsolutePath() + File.separator + folderType.getAssetsDir());
        this.nativesDir = new File(this.dir.getAbsolutePath() + File.separator + folderType.getNativesDir());
        this.librariesDir = new File(this.dir.getAbsolutePath() + File.separator + folderType.getLibrariesDir());
        this.minecraftClient = new File(this.dir.getAbsolutePath() + File.separator + folderType.getMinecraftClient());

        this.versionType = versionType;
        this.type = type;
        this.version = version;

        this.vmArgs.addAll(this.type.getArgs(this));
        this.args.add(this.getType().getMainClass(this));

    }

    public GameLauncher(String dir, String version, VersionType versionType, Type type, FolderType folderType, String fmlForgeVersion, String fmlmcVersion, String fmlmcpVersion) {
        this(dir, version, versionType, type, folderType);
        this.fmlForgeVersion = fmlForgeVersion;
        this.fmlmcVersion = fmlmcVersion;
        this.fmlmcpVersion = fmlmcpVersion;
    }

    public void launch(GameAuthenticator gameAuthenticator) throws LaunchingException {
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
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
            throw new LaunchingException("can't start Minecraft client", e);
        }

    }

    public File getDir() {
        return this.dir;
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

    public File getNativesDir() {
        return this.nativesDir;
    }

    public String getVersion() {
        return this.version;
    }

    public File getAssetsDir() {
        return assetsDir;
    }

    public File getLibrariesDir() {
        return librariesDir;
    }

    public File getMinecraftClient() {
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
