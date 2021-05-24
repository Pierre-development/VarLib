package fr.varchar.varlib.launching.types;

import fr.varchar.varlib.GameLauncher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class Type {

    private static final Type DEFAULT = new Type() {

        @Override
        public List<String> getArgs(GameLauncher gameLauncher) {
            final List<String> args = new ArrayList<>();
            args.add("java");
            args.add("-Djava.library.path=" + gameLauncher.getNativesDir());
            args.add("-Dfml.ignoreInvalidMinecraftCertificates=true");
            args.add("-Dfml.ignorePatchDiscrepancies=true");
            args.add("-cp");

            final StringBuilder sb = new StringBuilder();

            final List<String> libs = new ArrayList<>();
            final List<String> libsRemove = new ArrayList<>();
            try (Stream<Path> paths = Files.walk(gameLauncher.getLibrariesDir().toPath())) {
                paths.filter(Files::isRegularFile).forEach(file -> {
                    if (file.toFile().getAbsolutePath().endsWith("jar")) {
                        libs.add(file.toFile().getAbsolutePath() + ";");
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
            libs.forEach(file -> {
                if(gameLauncher.getVersionType() == VersionType.VERSION_1_13_HIGHER && gameLauncher.getType() == FORGE) {
                    if (file.contains("guava") && file.contains("25") || file.contains("20")) {
                        libsRemove.add(file);
                    }

                    if (file.contains("asm")) {
                        if (file.contains("6") && !gameLauncher.getVersion().contains("1.14"))
                            libsRemove.add(file);
                    }
                } else if(gameLauncher.getVersionType() == VersionType.VERSION_1_7_10 && gameLauncher.getType() == FORGE) {
                    if(file.contains("guava") && file.contains("15")) {
                        libsRemove.add(file);
                    }
                }
            });
            libsRemove.forEach(libs::remove);
            libs.forEach(sb::append);
            sb.append(gameLauncher.getMinecraftClient().getAbsolutePath());


            args.add(sb.toString());


            return args;
        }
    };

    public static final Type VANILLA = new Type() {
        @Override
        public List<String> getArgs(GameLauncher gameLauncher) {
            final List<String> args = new ArrayList<>();
            args.addAll(DEFAULT.getArgs(gameLauncher));


            args.add("net.minecraft.client.main.Main");

            return args;
        }
    };

    public static final Type FORGE = new Type() {
        @Override
        public List<String> getArgs(GameLauncher gameLauncher) {
            final List<String> args = new ArrayList<>();
            args.addAll(DEFAULT.getArgs(gameLauncher));
            if (gameLauncher.getVersionType() == VersionType.VERSION_1_8_HIGHER) {
                args.add("net.minecraft.launchwrapper.Launch");
                args.add("--tweakClass");
                args.add("net.minecraftforge.fml.common.launcher.FMLTweaker");

            } else if (gameLauncher.getVersionType() == VersionType.VERSION_1_13_HIGHER) {
                args.add("cpw.mods.modlauncher.Launcher");
            } else if(gameLauncher.getVersionType() == VersionType.VERSION_1_7_10) {
                args.add("net.minecraft.launchwrapper.Launch");
                args.add("--tweakClass");
                args.add("cpw.mods.fml.common.launcher.FMLTweaker");
            }
            return args;
        }
    };

    public abstract List<String> getArgs(GameLauncher gameLauncher);

}
