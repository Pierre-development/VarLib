package fr.varchar.varlib.launching;

import fr.varchar.varlib.authenticate.mojang.GameAuthenticator;
import fr.varchar.varlib.exceptions.LaunchingException;
import fr.varchar.varlib.launching.arguments.ArgumentsManager;
import fr.varchar.varlib.launching.arguments.ICallBackArgument;
import fr.varchar.varlib.launching.arguments.VMArgumentsManager;
import fr.varchar.varlib.launching.types.FolderType;
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
    private final String version;
    private final Path assetsDir;
    private final Path nativesDir;
    private final Path librariesDir;
    private final Path minecraftClient;
    private final VersionType versionType;
    private final Type type;
    private final GameAuthenticator gameAuthenticator;
    private final String fmlForgeVersion;
    private final String fmlMcVersion;
    private final String fmlMcpVersion;
    private final VMArgumentsManager vmArgumentsManager;
    private final ArgumentsManager argumentsManager;
    private Process process;
    private final ICallBackArgument callBackArgument;
    private final Logger logger;

    /**
     * Constructor should be build with a builder : {@link fr.varchar.varlib.launching.builder.GameLauncherVanillaBuilder} or {@link fr.varchar.varlib.launching.builder.GameLauncherForgeBuilder}
     * arguments for newer forge's version.
     * @param fmlForgeVersion FML Forge version can be get in Forge's json installer.
     * @param fmlmcVersion FML Minecraft version can be get in Forge's json installer.
     * @param fmlmcpVersion FML mcp version can be get in Forge's json installer.
     */
    public GameLauncher(String dir, String version, VersionType versionType, Type type, FolderType folderType, GameAuthenticator gameAuthenticator, VMArgumentsManager vmArgs, ArgumentsManager args, ICallBackArgument callBackArgument, Logger logger, String fmlForgeVersion, String fmlmcVersion, String fmlmcpVersion) {
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

        this.assetsDir = this.dir.resolve(folderType.getAssetsDir());
        this.nativesDir = this.dir.resolve(folderType.getNativesDir());
        this.librariesDir = this.dir.resolve(folderType.getLibrariesDir());
        this.minecraftClient = this.dir.resolve(folderType.getMinecraftClient());

        this.versionType = versionType;
        this.type = type;
        this.version = version;
        this.gameAuthenticator = gameAuthenticator;
        this.vmArgumentsManager = vmArgs;
        this.argumentsManager = args;
        this.callBackArgument = callBackArgument;
        this.logger = logger;
        this.fmlForgeVersion = fmlForgeVersion;
        this.fmlMcVersion = fmlmcVersion;
        this.fmlMcpVersion = fmlmcpVersion;
    }

    public GameLauncher(String dir, String version, VersionType versionType, Type type, FolderType folderType, GameAuthenticator gameAuthenticator, VMArgumentsManager vmArgs, ArgumentsManager args, ICallBackArgument callBackArgument, Logger logger) {
        this(dir, version, versionType, type, folderType, gameAuthenticator, vmArgs, args, callBackArgument, logger, null, null, null);
    }

    /**
     * Method for launch the game.
     * @throws LaunchingException when the game can't be launched.
     */

    public void launch() throws LaunchingException {
        this.logger.log("This library was created by VarChar | the discord: https://discord.com/invite/CjfZQye3GV (THIS IS NOT AN ERROR)", Color.RED);
        final ProcessBuilder processBuilder = new ProcessBuilder(this.allArgs);
        processBuilder.directory(this.dir.toFile());
        processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectErrorStream(true);

        try {
            Util.checkDirs(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        this.allArgs.addAll(this.vmArgumentsManager.getVMArgs(this, this.callBackArgument));
        this.allArgs.addAll(this.vmArgumentsManager.getClassPath(this));
        this.allArgs.addAll(this.argumentsManager.getArgs(this));

        final StringBuilder sb = new StringBuilder();
        for (String string : this.allArgs) {
            sb.append(string + " ");
        }

        this.logger.log(sb.toString(), Color.GREEN);
        try {
            this.process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
            throw new LaunchingException("can't start Minecraft client", e);
        }

    }

    public Path getDir() {
        return this.dir;
    }

    public Path getNativesDir() {
        return this.nativesDir;
    }

    public String getVersion() {
        return this.version;
    }

    public Path getAssetsDir() {
        return this.assetsDir;
    }

    public Path getLibrariesDir() {
        return this.librariesDir;
    }

    public Path getMinecraftClient() {
        return this.minecraftClient;
    }

    public Type getType() {
        return this.type;
    }

    public VersionType getVersionType() {
        return this.versionType;
    }

    public String getFmlForgeVersion() {
        return this.fmlForgeVersion;
    }

    public String getFmlMcVersion() {
        return this.fmlMcVersion;
    }

    public String getFmlMcpVersion() {
        return this.fmlMcpVersion;
    }

    public GameAuthenticator getGameAuthenticator() {
        return this.gameAuthenticator;
    }

    public VMArgumentsManager getVmArgumentsManager() {
        return this.vmArgumentsManager;
    }

    public ArgumentsManager getArgumentsManager() {
        return this.argumentsManager;
    }

    public Process getProcess() {
        return this.process;
    }
}
