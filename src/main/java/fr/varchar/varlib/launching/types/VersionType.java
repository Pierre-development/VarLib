package fr.varchar.varlib.launching.types;

import fr.varchar.varlib.authenticate.GameAuthenticator;
import fr.varchar.varlib.GameLauncher;

import java.util.ArrayList;
import java.util.List;

public abstract class VersionType {

    public static final VersionType V1_7_10 = new VersionType() {
        @Override
        public List<String> getArgs(GameLauncher gameLauncher, GameAuthenticator gameAuthenticator) {
            final List<String> args = new ArrayList<>();

            args.add("--username=" + gameAuthenticator.getUsername());

            args.add("--accessToken");
            args.add(gameAuthenticator.getAccessToken());

            args.add("--clientToken");
            args.add(gameAuthenticator.getClientToken());


            args.add("--version");
            args.add(gameLauncher.getVersion());

            args.add("--gameDir");
            args.add(gameLauncher.getDir().getAbsolutePath());

            args.add("--assetsDir");
            args.add(gameLauncher.getAssetsDir().getAbsolutePath());

            args.add("--assetIndex");
            args.add(gameLauncher.getVersion());

            args.add("--userProperties");
            args.add("{}");

            args.add("--uuid");
            args.add(gameAuthenticator.getUuId());

            args.add("--userType");
            args.add("legacy");
            return args;
        }
    };

    public static final VersionType V1_8_HIGHER = new VersionType() {
        @Override
        public List<String> getArgs(GameLauncher gameLauncher, GameAuthenticator gameAuthenticator) {
            final List<String> args = new ArrayList<>();

            args.add("--username=" + gameAuthenticator.getUsername());

            args.add("--accessToken");
            args.add(gameAuthenticator.getAccessToken());

            args.add("--version");
            args.add(gameLauncher.getVersion());

            args.add("--gameDir");
            args.add(gameLauncher.getDir().getAbsolutePath());

            args.add("--assetsDir");
            args.add(gameLauncher.getAssetsDir().getAbsolutePath());

            args.add("--assetIndex");
            args.add(gameLauncher.getVersion());

            args.add("--userProperties");
            args.add("{}");

            args.add("--uuid");
            args.add(gameAuthenticator.getUuId());

            args.add("--userType");
            args.add("legacy");
            return args;
        }
    };

    public static final VersionType V1_13_HIGHER = new VersionType() {
        @Override
        public List<String> getArgs(GameLauncher gameLauncher, GameAuthenticator gameAuthenticator) {
            final List<String> args = new ArrayList<>();


            args.add("--username");
            args.add(gameAuthenticator.getUsername());

            args.add("--version");
            args.add(gameLauncher.getVersion());

            args.add("--gameDir");
            args.add(gameLauncher.getDir().getAbsolutePath());

            args.add("--assetsDir");
            args.add(gameLauncher.getDir().getAbsolutePath());

            args.add("--assetIndex");
            args.add(gameLauncher.getVersion());

            args.add("--uuid");
            args.add(gameAuthenticator.getUuId());

            args.add("--accessToken");
            args.add(gameAuthenticator.getAccessToken());

            args.add("--userType");
            args.add("mojang");

            args.add("--versionType");
            args.add("release");
            if (gameLauncher.getType() == Type.FORGE) {
                args.add("--launchTarget");
                args.add("fmlclient");

                args.add("--fml.forgeVersion");
                args.add("34.1.23");

                args.add("--fml.mcVersion");
                args.add("1.16.3");

                args.add("--fml.forgeGroup");
                args.add("net.minecraftforge");

                args.add("--fml.mcpVersion");
                args.add("20200911.084530");
            }
            return args;
        }
    };

    public abstract List<String> getArgs(GameLauncher gameLauncher, GameAuthenticator gameAuthenticator);

}
