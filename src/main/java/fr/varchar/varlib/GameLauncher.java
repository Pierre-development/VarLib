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
    private final List<String> vmArgs = new ArrayList<>();
    private final String version;
    private final File assetsDir;
    private final File nativesDir;
    private final File librariesDir;
    private final File minecraftClient;
    private final VersionType versionType;
    private final Type type;

    public GameLauncher(String dir, String version, VersionType versionType, Type type) {
        if(System.getProperty("os.name").startsWith("Win")) {
            this.dir = new File(System.getenv("appdata") + File.separator + "." + dir);
        } else {
            this.dir = new File(System.getProperty("user.home") + File.separator + "." + dir);
        }

        this.assetsDir = new File(this.dir.getAbsolutePath() + File.separator + "assets");
        this.nativesDir = new File(this.dir.getAbsolutePath() + File.separator + "natives");
        this.librariesDir = new File(this.dir.getAbsolutePath() + File.separator + "libraries");
        this.minecraftClient = new File(this.dir.getAbsolutePath() + File.separator + "client.jar");
        this.versionType = versionType;
        this.type = type;
        this.version = version;





    }

    public void launch(GameAuthenticator gameAuthenticator) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(vmArgs);
        processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        Util.checkDirs(this);
        vmArgs.addAll(this.type.getArgs(this));

        vmArgs.addAll(this.versionType.getArgs(this, gameAuthenticator));

        final StringBuilder sb = new StringBuilder();
        for(String string : vmArgs) {
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
}
