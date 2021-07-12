package fr.varchar.varlib.launching.builder;

import fr.varchar.varlib.launching.FolderType;
import fr.varchar.varlib.launching.GameLauncher;
import fr.varchar.varlib.launching.types.Type;
import fr.varchar.varlib.launching.types.VersionType;
import fr.varchar.varlib.util.Util;

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
     * @return
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
        if(this.versionType == VersionType.VERSION_1_13_HIGHER) {
            if(this.folderType == FolderType.FLOW_UPDATER) {
                if(this.autoMode) {
                    Util.FMLInfos.init(this.dir + FileSystems.getDefault().getSeparator() + this.forgeInstallerJson);
                    return new GameLauncher(this.name, this.version, this.versionType, Type.FORGE, this.folderType, this.gameAuthenticator, this.vmArgumentsManager, this.argumentsManager, this.callBackArgument, Util.FMLInfos.getFmlForgeVersion(), Util.FMLInfos.getFmlMcVersion(), Util.FMLInfos.getFmlMcpVersion());
                }
            }
            return new GameLauncher(this.name, this.version, this.versionType, Type.FORGE, this.folderType, this.gameAuthenticator, this.vmArgumentsManager, this.argumentsManager, this.callBackArgument, this.fmlForgeVersion, this.fmlmcVersion, this.fmlmcpVersion);
        } else {
            return new GameLauncher(this.name, this.version, this.versionType, Type.FORGE, this.folderType, this.gameAuthenticator, this.vmArgumentsManager, this.argumentsManager, this.callBackArgument);
        }
    }
}
