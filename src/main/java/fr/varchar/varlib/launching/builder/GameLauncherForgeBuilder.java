package fr.varchar.varlib.launching.builder;

import fr.varchar.varlib.launching.GameLauncher;
import fr.varchar.varlib.launching.types.FolderType;
import fr.varchar.varlib.launching.types.Type;
import fr.varchar.varlib.launching.types.VersionType;
import fr.varchar.varlib.util.Util;
import fr.varchar.varlib.util.logger.Logger;

public class GameLauncherForgeBuilder extends AbstractGameLauncherBuilder {

    private String fmlForgeVersion;
    private String fmlmcVersion;
    private String fmlmcpVersion;
    private boolean autoMode = false;
    private final String forgeInstallerJson;

    /**
     * {@inheritDoc}
     */
    public GameLauncherForgeBuilder(String dir, String forgeInstallerJson) {
        super(dir);
        this.forgeInstallerJson = forgeInstallerJson;
    }

    public GameLauncherForgeBuilder(String dir) {
        this(dir, null);
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
                Util.FMLInfos.init(this.dir.resolve(this.forgeInstallerJson).toString());
                this.fmlForgeVersion = Util.FMLInfos.getFmlForgeVersion();
                this.fmlmcVersion = Util.FMLInfos.getFmlMcVersion();
                this.fmlmcpVersion = Util.FMLInfos.getFmlMcpVersion();
            }
        }
        return new GameLauncher(this.name, this.version, this.versionType, Type.FORGE, this.folderType, this.gameAuthenticator, this.vmArgumentsManager, this.argumentsManager, this.callBackArgument, this.logger, this.fmlForgeVersion, this.fmlmcVersion, this.fmlmcpVersion);
    }
}
