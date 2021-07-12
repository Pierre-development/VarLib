package fr.varchar.varlib.launching.builder;

import fr.varchar.varlib.launching.GameLauncher;
import fr.varchar.varlib.launching.types.Type;

public class GameLauncherVanillaBuilder extends AbstractGameLauncherBuilder {

    /**
     * {@inheritDoc}
     */
    public GameLauncherVanillaBuilder(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    public GameLauncher build() {
        return new GameLauncher(this.name, this.version, this.versionType, Type.VANILLA, this.folderType, this.gameAuthenticator, this.vmArgumentsManager, this.argumentsManager, this.callBackArgument);
    }
}
