package fr.varchar.varlib.launching.builder;

import fr.varchar.varlib.authenticate.mojang.GameAuthenticator;
import fr.varchar.varlib.launching.types.FolderType;
import fr.varchar.varlib.launching.GameLauncher;
import fr.varchar.varlib.launching.arguments.ArgumentsManager;
import fr.varchar.varlib.launching.arguments.ICallBackArgument;
import fr.varchar.varlib.launching.arguments.VMArgumentsManager;
import fr.varchar.varlib.launching.types.VersionType;
import fr.varchar.varlib.util.logger.Logger;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public abstract class AbstractGameLauncherBuilder {
    protected final String name;
    protected String version;
    protected VersionType versionType;
    protected FolderType folderType;
    protected GameAuthenticator gameAuthenticator;
    protected Path dir;
    protected final VMArgumentsManager vmArgumentsManager = new VMArgumentsManager();
    protected final ArgumentsManager argumentsManager = new ArgumentsManager();
    protected ICallBackArgument callBackArgument = () -> null;
    protected Logger logger;
    protected String assetsDir, librariesDir, nativesDir, minecraftClient;


    /**
     * @param name specify the name of the game dir
     */
    public AbstractGameLauncherBuilder(String name) {
        this.name = name;
        if (System.getProperty("os.name").startsWith("Win")) {
            this.dir = Paths.get(System.getenv("appdata") + FileSystems.getDefault().getSeparator() + "." + name);
        } else {
            this.dir = Paths.get(System.getProperty("user.home") + FileSystems.getDefault().getSeparator() + "." + name);
        }
        if(!Files.exists(this.dir)) {
            try {
                Files.createDirectory(this.dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set the version
     * @param version this is the name of json indexes for assets - example : "1.12 but not "1.12.2".
     */
    public AbstractGameLauncherBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * Set the {@link VersionType}.
     */
    public AbstractGameLauncherBuilder setVersionType(VersionType versionType) {
        this.versionType = versionType;
        return this;
    }

    /**
     * Set the {@link FolderType} for choose the name of the client file and folders.
     */
    public AbstractGameLauncherBuilder setFolderType(FolderType folderType) {
        this.folderType = folderType;
        return this;
    }

    /**
     * Set the {@link GameAuthenticator}.
     */
    public AbstractGameLauncherBuilder setGameAuthenticator(GameAuthenticator gameAuthenticator) {
        this.gameAuthenticator = gameAuthenticator;
        return this;
    }

    /**
     * Add VM arguments to the launch command.
     */
    public AbstractGameLauncherBuilder addVMArgument(String... args) {
        this.callBackArgument = () -> Arrays.asList(args);
        this.callBackArgument.vmArgs();
        return this;
    }

    public AbstractGameLauncherBuilder setLogger(Logger logger) {
        this.logger = logger;
        return this;
    }

    public AbstractGameLauncherBuilder setJavaPath(String javaPath) {
        this.vmArgumentsManager.javaPath = javaPath;
        return this;
    }

    public Path getDir() {
        return dir;
    }

    /**
     * Method for build.
     * @return the {@link GameLauncher} built.
     */
    public abstract GameLauncher build();

}
