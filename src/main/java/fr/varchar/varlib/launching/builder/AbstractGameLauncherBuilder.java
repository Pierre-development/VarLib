package fr.varchar.varlib.launching.builder;

import fr.varchar.varlib.FolderType;
import fr.varchar.varlib.GameLauncher;
import fr.varchar.varlib.authenticate.mojang.GameAuthenticator;
import fr.varchar.varlib.launching.types.VersionType;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractGameLauncherBuilder {
    protected final String dir;
    protected String version;
    protected VersionType versionType;
    protected FolderType folderType;
    protected GameAuthenticator gameAuthenticator;
    protected Path absoluteDir;

    public AbstractGameLauncherBuilder(String dir) {
        this.dir = dir;
        if (System.getProperty("os.name").startsWith("Win")) {
            this.absoluteDir = Paths.get(System.getenv("appdata") + FileSystems.getDefault().getSeparator() + "." + this.dir);
        } else {
            this.absoluteDir = Paths.get(System.getProperty("user.home") + FileSystems.getDefault().getSeparator() + "." + this.dir);
        }
    }

    public AbstractGameLauncherBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    public AbstractGameLauncherBuilder setVersionType(VersionType versionType) {
        this.versionType = versionType;
        return this;
    }

    public AbstractGameLauncherBuilder setFolderType(FolderType folderType) {
        this.folderType = folderType;
        return this;
    }

    public AbstractGameLauncherBuilder setGameAuthenticator(GameAuthenticator gameAuthenticator) {
        this.gameAuthenticator = gameAuthenticator;
        return this;
    }

    public abstract GameLauncher build();
}
