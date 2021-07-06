package fr.varchar.varlib.launching.types;

import fr.varchar.varlib.authenticate.mojang.GameAuthenticator;
import fr.varchar.varlib.GameLauncher;

import java.util.ArrayList;
import java.util.List;

public abstract class VersionType {
    /*
    public static final VersionType VERSION_1_7_10 = new VersionType() {
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
            args.add(gameLauncher.getDir().toAbsolutePath().toString());

            args.add("--assetsDir");
            args.add(gameLauncher.getAssetsDir().toAbsolutePath().toString());

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

    public static final VersionType VERSION_1_8_HIGHER = new VersionType() {
        @Override
        public List<String> getArgs(GameLauncher gameLauncher, GameAuthenticator gameAuthenticator) {
            final List<String> args = new ArrayList<>();

            args.add("--username=" + gameAuthenticator.getUsername());

            args.add("--accessToken");
            args.add(gameAuthenticator.getAccessToken());

            args.add("--version");
            args.add(gameLauncher.getVersion());

            args.add("--gameDir");
            args.add(gameLauncher.getDir().toAbsolutePath().toString());

            args.add("--assetsDir");
            args.add(gameLauncher.getAssetsDir().toAbsolutePath().toString());

            args.add("--assetIndex");
            args.add(gameLauncher.getVersion());

            args.add("--userProperties");
            args.add("{}");

            args.add("--uuid");
            args.add(gameAuthenticator.getUuId());

            args.add("--userType");
            args.add("legacy");
            if(gameLauncher.getType() == Type.FORGE) {
                if (gameLauncher.getVersionType() == VersionType.VERSION_1_8_HIGHER) {
                    args.add("--tweakClass");
                    args.add("net.minecraftforge.fml.common.launcher.FMLTweaker");
                } else if (gameLauncher.getVersionType() == VersionType.VERSION_1_7_10) {
                    args.add("--tweakClass");
                    args.add("cpw.mods.fml.common.launcher.FMLTweaker");
                }
            }
            return args;
        }
    };

    public static final VersionType VERSION_1_13_HIGHER = new VersionType() {
        @Override
        public List<String> getArgs(GameLauncher gameLauncher, GameAuthenticator gameAuthenticator) {
            final List<String> args = new ArrayList<>();


            args.add("--username");
            args.add(gameAuthenticator.getUsername());

            args.add("--version");
            args.add(gameLauncher.getVersion());

            args.add("--gameDir");
            args.add(gameLauncher.getDir().toAbsolutePath().toString());

            args.add("--assetsDir");
            args.add(gameLauncher.getAssetsDir().toAbsolutePath().toString());

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
                args.add(gameLauncher.getFmlForgeVersion());

                args.add("--fml.mcVersion");
                args.add(gameLauncher.getFmlmcVersion());

                args.add("--fml.forgeGroup");
                args.add("net.minecraftforge");

                args.add("--fml.mcpVersion");
                args.add(gameLauncher.getFmlmcpVersion());
            }
            return args;
        }
    };

    public abstract List<String> getArgs(GameLauncher gameLauncher, GameAuthenticator gameAuthenticator);
*/
}
