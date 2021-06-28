package fr.varchar.varlib.launching.builder;

import fr.varchar.varlib.FolderType;
import fr.varchar.varlib.GameLauncher;
import fr.varchar.varlib.launching.types.Type;
import fr.varchar.varlib.launching.types.VersionType;
import fr.varchar.varlib.util.Util;

import java.io.File;
import java.nio.file.FileSystems;

public class GameLauncherForgeBuilder extends AbstractGameLauncherBuilder {

    private String fmlForgeVersion;
    private String fmlmcVersion;
    private String fmlmcpVersion;
    private boolean autoMode;
    private String forgeInstallerJson;


    public GameLauncherForgeBuilder(String dir) {
        super(dir);
    }

    public GameLauncherForgeBuilder setFmlForgeVersion(String fmlForgeVersion) {
        this.fmlForgeVersion = fmlForgeVersion;
        return this;
    }

    public GameLauncherForgeBuilder setFmlmcVersion(String fmlmcVersion) {
        this.fmlmcVersion = fmlmcVersion;
        return this;
    }

    public GameLauncherForgeBuilder setFmlmcpVersion(String fmlmcpVersion) {
        this.fmlmcpVersion = fmlmcpVersion;
        return this;
    }

    public GameLauncherForgeBuilder setAutoMode(boolean autoMode, String forgeInstallerJson) {
        this.autoMode = autoMode;
        this.forgeInstallerJson = forgeInstallerJson;
        return this;
    }

    @Override
    public GameLauncher build() {
        if(this.versionType == VersionType.VERSION_1_13_HIGHER) {
            if(this.folderType == FolderType.FLOW_UPDATER) {
                if(this.autoMode) {
                    Util.FMLInfos.init(this.absoluteDir + FileSystems.getDefault().getSeparator() + this.forgeInstallerJson);
                    return new GameLauncher(this.dir, this.version, this.versionType, Type.FORGE, this.folderType, this.gameAuthenticator, Util.FMLInfos.getFmlForgeVersion(), Util.FMLInfos.getFmlMcVersion(), Util.FMLInfos.getFmlMcpVersion());
                }
            }
            return new GameLauncher(this.dir, this.version, this.versionType, Type.FORGE, this.folderType, this.gameAuthenticator, this.fmlForgeVersion, this.fmlmcVersion, this.fmlmcpVersion);
        } else {
            return new GameLauncher(this.dir, this.version, this.versionType, Type.FORGE, this.folderType, this.gameAuthenticator);
        }
    }
}
