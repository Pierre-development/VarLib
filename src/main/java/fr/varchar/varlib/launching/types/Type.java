package fr.varchar.varlib.launching.types;

import fr.varchar.varlib.GameLauncher;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
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

            return args;
        }


        @Override
        public String getMainClass(GameLauncher gameLauncher) {
            return null;
        }
    };

    public static final Type VANILLA = new Type() {
        @Override
        public List<String> getArgs(GameLauncher gameLauncher) {
            final List<String> args = new ArrayList<>();
            args.addAll(DEFAULT.getArgs(gameLauncher));
            return args;
        }

        @Override
        public String getMainClass(GameLauncher gameLauncher) {
            return "net.minecraft.client.main.Main";
        }
    };

    public static final Type FORGE = new Type() {
        @Override
        public List<String> getArgs(GameLauncher gameLauncher) {
            final List<String> args = new ArrayList<>();
            args.addAll(DEFAULT.getArgs(gameLauncher));
            return args;
        }

        @Override
        public String getMainClass(GameLauncher gameLauncher) {
            if (gameLauncher.getVersionType() == VersionType.VERSION_1_8_HIGHER) {
                return "net.minecraft.launchwrapper.Launch";
            } else if (gameLauncher.getVersionType() == VersionType.VERSION_1_13_HIGHER) {
                return "cpw.mods.modlauncher.Launcher";
            } else if (gameLauncher.getVersionType() == VersionType.VERSION_1_7_10) {
                return "net.minecraft.launchwrapper.Launch";
            }
            return null;
        }
    };

    private List<String> classpath(GameLauncher gameLauncher) {
        final List<String> classpath = new ArrayList<>();
        classpath.add("-cp");
        final StringBuilder sb = new StringBuilder();

        final List<String> libs = new ArrayList<>();
        final List<String> libsRemove = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(gameLauncher.getLibrariesDir().toAbsolutePath())) {
            paths.filter(Files::isRegularFile).forEach(file -> {
                if (file.toFile().getAbsolutePath().endsWith("jar")) {
                    libs.add(file.toFile().getAbsolutePath() + File.pathSeparator);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        libs.forEach(file -> {
            if (gameLauncher.getVersionType() == VersionType.VERSION_1_13_HIGHER && gameLauncher.getType() == FORGE) {
                if (file.contains("guava") && (file.contains("25") || file.contains("20"))) {
                    libsRemove.add(file);
                }

                if (file.contains("asm") && file.contains("6") && !gameLauncher.getVersion().contains("1.14")) {
                    libsRemove.add(file);
                }
            } else if (gameLauncher.getVersionType() == VersionType.VERSION_1_7_10 && gameLauncher.getType() == FORGE && file.contains("guava") && file.contains("15")) {
                libsRemove.add(file);
            }
        });
        libsRemove.forEach(libs::remove);
        libs.forEach(sb::append);
        sb.append(gameLauncher.getMinecraftClient().toAbsolutePath());


        classpath.add(sb.toString());
        return classpath;
    }

    public List<String> getClasspath(GameLauncher gameLauncher) {
        return this.classpath(gameLauncher);
    }

    public abstract List<String> getArgs(GameLauncher gameLauncher);

    public abstract String getMainClass(GameLauncher gameLauncher);

}
