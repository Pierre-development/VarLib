package fr.pierre.varlib.util;

import fr.pierre.varlib.launching.GameLauncher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
