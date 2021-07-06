package fr.varchar.varlib.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.varchar.varlib.GameLauncher;

import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public static class FMLInfos {

        private static String json;
        private static String[] strings;

        public static void init(String json) {
            FMLInfos.json = json;
            JsonElement jsonElements = null;
            try {
                jsonElements = JsonParser.parseReader(new FileReader(FMLInfos.json));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            final JsonObject jsonObject = jsonElements.getAsJsonObject();
            final JsonElement jsonElement = jsonObject.get("arguments");
            strings = jsonElement.toString().split("\",\"");
        }

        public static String getFmlForgeVersion() {
            return strings[3];
        }

        /**
         *
         * @return
         */

        public static String getFmlMcVersion() {
            return strings[5];
        }

        /**
         * @return fml mcp version of Forge installer's json file
         */

        public static String getFmlMcpVersion() {
            return strings[9].replaceAll("\"]}", "");
        }
    }
}
