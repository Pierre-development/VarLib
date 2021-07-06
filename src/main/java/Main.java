import fr.varchar.varlib.FolderType;
import fr.varchar.varlib.GameLauncher;
import fr.varchar.varlib.authenticate.mojang.GameAuthenticator;
import fr.varchar.varlib.exceptions.LaunchingException;
import fr.varchar.varlib.launching.VersionType;
import fr.varchar.varlib.launching.builder.AbstractGameLauncherBuilder;
import fr.varchar.varlib.launching.builder.GameLauncherForgeBuilder;

public class Main {

    private static AbstractGameLauncherBuilder gameLauncherBuilder = new GameLauncherForgeBuilder("tejjjjjjstpbbbett").setFolderType(FolderType.S_UPDATE).setVersion("1.8").setVersionType(VersionType.VERSION_1_8_HIGHER);


    private static void auth() {
        gameLauncherBuilder.setGameAuthenticator(new GameAuthenticator("test", "a", "b"));
    }



    private static void launch() throws LaunchingException {
        gameLauncherBuilder.addVMArgument("-Xmx6G");
        GameLauncher gameLauncher = gameLauncherBuilder.build();
        gameLauncher.launch();

    }

    public static void main(String[] args) throws Exception {
        auth();
        launch();
    }
}
