package fr.varchar.varlib;

public class FolderType {

    public static final FolderType S_UPDATE = new FolderType("assets", "natives", "libs", "minecraft.jar");
    public static final FolderType FLOW_UPDATER = new FolderType("assets", "natives", "libraries", "client.jar");

    private final String assetsDir;
    private final String nativesDir;
    private final String librariesDir;
    private final String minecraftClient;

    public FolderType(String assetsDir, String nativesDir, String librariesDir, String minecraftClient) {
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
