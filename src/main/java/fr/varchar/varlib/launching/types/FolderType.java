package fr.varchar.varlib.launching.types;

public enum FolderType {


    S_UPDATE("assets", "natives", "libs", "minecraft.jar"),
    FLOW_UPDATER("assets", "natives", "libraries", "client.jar");

    private final String assetsDir;
    private final String nativesDir;
    private final String librariesDir;
    private final String minecraftClient;

    FolderType(String assetsDir, String nativesDir, String librariesDir, String minecraftClient) {
        this.assetsDir = assetsDir;
        this.nativesDir = nativesDir;
        this.librariesDir = librariesDir;
        this.minecraftClient = minecraftClient;
    }

    public String getAssetsDir() {
        return assetsDir;
    }

    public String getNativesDir() {
        return nativesDir;
    }

    public String getLibrariesDir() {
        return librariesDir;
    }

    public String getMinecraftClient() {
        return minecraftClient;
    }
}
