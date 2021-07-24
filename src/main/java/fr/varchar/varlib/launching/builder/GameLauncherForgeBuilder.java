package fr.varchar.varlib.launching.builder;

import fr.varchar.varlib.launching.FolderType;
import fr.varchar.varlib.launching.GameLauncher;
import fr.varchar.varlib.launching.types.Type;
import fr.varchar.varlib.launching.types.VersionType;
import fr.varchar.varlib.util.Util;
import fr.varchar.varlib.util.logger.Logger;

import java.nio.file.FileSystems;

public class GameLauncherForgeBuilder extends AbstractGameLauncherBuilder {

    private String fmlForgeVersion;
    private String fmlmcVersion;
    private String fmlmcpVersion;
    private boolean autoMode;
    private String forgeInstallerJson;

    /**
     * {@inheritDoc}
     */
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

    /**
     * @param forgeInstallerJson
     */
    public GameLauncherForgeBuilder setAutoMode(boolean autoMode, String forgeInstallerJson) {
        this.autoMode = autoMode;
        this.forgeInstallerJson = forgeInstallerJson;
        return this;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GameLauncher build() {
        if (this.logger == null) {
            this.logger = new Logger(Logger.DEFAULT);
        }
        if (this.versionType == VersionType.VERSION_1_13_HIGHER) {
            if (this.folderType == FolderType.FLOW_UPDATER) {
                if (this.autoMode) {
                    Util.FMLInfos.init(this.dir + FileSystems.getDefault().getSeparator() + this.forgeInstallerJson);
                    this.fmlForgeVersion = Util.FMLInfos.getFmlForgeVersion();
                    this.fmlmcVersion = Util.FMLInfos.getFmlMcVersion();
                    this.fmlmcpVersion = Util.FMLInfos.getFmlMcpVersion();
                }
            }
        }
        return new GameLauncher(this.name, this.version, this.versionType, Type.FORGE, this.folderType, this.gameAuthenticator, this.vmArgumentsManager, this.argumentsManager, this.callBackArgument, this.logger, this.fmlForgeVersion, this.fmlmcVersion, this.fmlmcpVersion);
    }
}
