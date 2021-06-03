import fr.varchar.varlib.FolderType;
import fr.varchar.varlib.GameLauncher;
import fr.varchar.varlib.launching.types.Type;
import fr.varchar.varlib.launching.types.VersionType;

public class Main {
    public static void main(String[] args) {
        GameLauncher gameLauncher =  new GameLauncher("zzzzddgb", "1", VersionType.VERSION_1_13_HIGHER, Type.FORGE, FolderType.FLOW_UPDATER);
    }
}
