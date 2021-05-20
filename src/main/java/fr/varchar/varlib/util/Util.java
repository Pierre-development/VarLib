package fr.varchar.varlib.util;

import fr.varchar.varlib.GameLauncher;

import java.io.FileNotFoundException;

public class Util {

    public static void checkDirs(GameLauncher gameLauncher) throws FileNotFoundException {
        if (!gameLauncher.getAssetsDir().exists()) {
            throw new FileNotFoundException("Assets folder doesn't exist");
        } else if (!gameLauncher.getLibrariesDir().exists()) {
            throw new FileNotFoundException("Libraries folder doesn't exist");
        } else if (!gameLauncher.getNativesDir().exists()) {
            throw new FileNotFoundException("Natives folder doesn't exist");
        } else if (!gameLauncher.getMinecraftClient().exists()) {
            throw new FileNotFoundException("client.jar doesn't exist");
        }
    }


}
