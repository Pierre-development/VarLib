package fr.varchar.varlib.util;

import fr.varchar.varlib.GameLauncher;

import java.io.FileNotFoundException;
import java.nio.file.Files;

public class Util {

    public static void checkDirs(GameLauncher gameLauncher) throws FileNotFoundException {
        if (!Files.exists(gameLauncher.getAssetsDir())) {
            throw new FileNotFoundException("Assets folder doesn't exist");
        } else if (!Files.exists(gameLauncher.getLibrariesDir())) {
            throw new FileNotFoundException("Libraries folder doesn't exist");
        } else if (!Files.exists(gameLauncher.getNativesDir())) {
            throw new FileNotFoundException("Natives folder doesn't exist");
        } else if (!Files.exists(gameLauncher.getMinecraftClient())) {
            throw new FileNotFoundException("client.jar doesn't exist");
        }
    }
}
