package fr.varchar.varlib.launching.builder;

import fr.varchar.varlib.GameLauncher;
import fr.varchar.varlib.launching.types.Type;

public class GameLauncherVanillaBuilder extends AbstractGameLauncherBuilder {

    public GameLauncherVanillaBuilder(String dir) {
        super(dir);
    }

    public GameLauncher build() {
        return new GameLauncher(this.dir, this.version, this.versionType, Type.VANILLA, this.folderType, this.gameAuthenticator);
    }
}
