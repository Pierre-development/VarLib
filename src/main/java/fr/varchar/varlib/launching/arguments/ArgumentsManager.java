package fr.varchar.varlib.launching.arguments;

import fr.varchar.varlib.launching.GameLauncher;
import fr.varchar.varlib.launching.types.Type;
import fr.varchar.varlib.launching.types.VersionType;

import java.util.ArrayList;
import java.util.List;

public class ArgumentsManager extends AbstractArguments {

    @Override
    public List<String> getArgs(GameLauncher gameLauncher) {
        final List<String> args = new ArrayList<>();

        if (gameLauncher.getVersionType() == VersionType.VERSION_1_7_10) {
            args.add("--username=" + gameLauncher.getGameAuthenticator().getUsername());

            args.add("--accessToken");
            args.add(gameLauncher.getGameAuthenticator().getAccessToken());

            args.add("--clientToken");
            args.add(gameLauncher.getGameAuthenticator().getClientToken());


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
            args.add(gameLauncher.getGameAuthenticator().getUuId());

            args.add("--userType");
            args.add("legacy");

            if (gameLauncher.getType() == Type.FORGE) {
                args.add("--tweakClass");
                args.add("cpw.mods.fml.common.launcher.FMLTweaker");
            }
        } else if (gameLauncher.getVersionType() == VersionType.VERSION_1_8_HIGHER) {
            args.add("--username=" + gameLauncher.getGameAuthenticator().getUsername());

            args.add("--accessToken");
            args.add(gameLauncher.getGameAuthenticator().getAccessToken());

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
            args.add(gameLauncher.getGameAuthenticator().getUuId());

            args.add("--userType");
            args.add("legacy");

            if (gameLauncher.getType() == Type.FORGE) {
                args.add("--tweakClass");
                args.add("net.minecraftforge.fml.common.launcher.FMLTweaker");
            }
        } else if(gameLauncher.getVersionType() == VersionType.VERSION_1_13_HIGHER) {
            args.add("--username");
            args.add(gameLauncher.getGameAuthenticator().getUsername());

            args.add("--version");
            args.add(gameLauncher.getVersion());

            args.add("--gameDir");
            args.add(gameLauncher.getDir().toAbsolutePath().toString());

            args.add("--assetsDir");
            args.add(gameLauncher.getAssetsDir().toAbsolutePath().toString());

            args.add("--assetIndex");
            args.add(gameLauncher.getVersion());

            args.add("--uuid");
            args.add(gameLauncher.getGameAuthenticator().getUuId());

            args.add("--accessToken");
            args.add(gameLauncher.getGameAuthenticator().getAccessToken());

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
        }
        return args;
    }
}
