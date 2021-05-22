package fr.varchar.varlib;

import fr.varchar.varlib.authenticate.GameAuthenticator;
import fr.varchar.varlib.launching.types.Type;
import fr.varchar.varlib.launching.types.VersionType;
import fr.varchar.varlib.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameLauncher {

    private final File dir;
    private final List<String> args = new ArrayList<>();
    private final String version;
    private final File assetsDir;
    private final File nativesDir;
    private final File librariesDir;
    private final File minecraftClient;
    private final VersionType versionType;
    private final Type type;
    private String fmlForgeVersion;
    private String fmlmcVersion;
    private String fmlmcpVersion;


    public GameLauncher(String dir, String version, VersionType versionType, Type type, FolderType folderType) {
        if (System.getProperty("os.name").startsWith("Win")) {
            this.dir = new File(System.getenv("appdata") + File.separator + "." + dir);
        } else {
            this.dir = new File(System.getProperty("user.home") + File.separator + "." + dir);
        }

        this.assetsDir = new File(this.dir.getAbsolutePath() + File.separator + folderType.getAssetsDir());
        this.nativesDir = new File(this.dir.getAbsolutePath() + File.separator + folderType.getNativesDir());
        this.librariesDir = new File(this.dir.getAbsolutePath() + File.separator + folderType.getLibrariesDir());
        this.minecraftClient = new File(this.dir.getAbsolutePath() + File.separator + folderType.getMinecraftClient());

        this.versionType = versionType;
        this.type = type;
        this.version = version;
    }

    public GameLauncher(String dir, String version, VersionType versionType, Type type, FolderType folderType, String fmlForgeVersion, String fmlmcVersion, String fmlmcpVersion) {
        this(dir, version, versionType, type, folderType);
        this.fmlForgeVersion = fmlForgeVersion;
        this.fmlmcVersion = fmlmcVersion;
        this.fmlmcpVersion = fmlmcpVersion;
    }

    public void launch(GameAuthenticator gameAuthenticator) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(args);
        processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        Util.checkDirs(this);
        args.addAll(this.type.getArgs(this));

        args.addAll(this.versionType.getArgs(this, gameAuthenticator));

        final StringBuilder sb = new StringBuilder();
        for (String string : args) {
            sb.append(string + " ");
        }

        System.out.println(sb);
        processBuilder.start();

    }

    public File getDir() {
        return this.dir;
    }

    public File getNativesDir() {
        return this.nativesDir;
    }

    public String getVersion() {
        return this.version;
    }

    public File getAssetsDir() {
        return assetsDir;
    }

    public File getLibrariesDir() {
        return librariesDir;
    }

    public File getMinecraftClient() {
        return minecraftClient;
    }

    public Type getType() {
        return type;
    }

    public VersionType getVersionType() {
        return versionType;
    }

    public String getFmlForgeVersion() {
        return fmlForgeVersion;
    }

    public String getFmlmcVersion() {
        return fmlmcVersion;
    }

    public String getFmlmcpVersion() {
        return fmlmcpVersion;
    }
}
