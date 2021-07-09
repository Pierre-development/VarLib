package fr.varchar.varlib.launching.arguments;

import fr.varchar.varlib.launching.GameLauncher;
import fr.varchar.varlib.launching.types.Type;
import fr.varchar.varlib.launching.types.VersionType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class VMArgumentsManager {


    private List<String> vmArgs(GameLauncher gameLauncher, CallBackArgument callBackArgument) {
        final List<String> args = new ArrayList<>();
        args.add("java");
        args.add("-Djava.library.path=" + gameLauncher.getNativesDir());
        args.add("-Dfml.ignoreInvalidMinecraftCertificates=true");
        args.add("-Dfml.ignorePatchDiscrepancies=true");
        if(callBackArgument.vmArgs() != null) {
            args.addAll(callBackArgument.vmArgs());
        }

        return args;
    }

    private List<String> classPath(GameLauncher gameLauncher) {
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
            if (gameLauncher.getVersionType() == VersionType.VERSION_1_13_HIGHER && gameLauncher.getType() == Type.FORGE) {
                if (file.contains("guava") && (file.contains("25") || file.contains("20"))) {
                    libsRemove.add(file);
                }

                if (file.contains("asm") && file.contains("6") && !gameLauncher.getVersion().contains("1.14")) {
                    libsRemove.add(file);
                }
            } else if (gameLauncher.getVersionType() == VersionType.VERSION_1_7_10 && gameLauncher.getType() == Type.FORGE && file.contains("guava") && file.contains("15")) {
                libsRemove.add(file);
            }
        });
        libsRemove.forEach(libs::remove);
        libs.forEach(sb::append);
        sb.append(gameLauncher.getMinecraftClient().toAbsolutePath());


        classpath.add(sb.toString());

        if(gameLauncher.getType() == Type.FORGE) {
            if (gameLauncher.getVersionType() == VersionType.VERSION_1_8_HIGHER) {
                classpath.add("net.minecraft.launchwrapper.Launch");
            } else if (gameLauncher.getVersionType() == VersionType.VERSION_1_13_HIGHER) {
                classpath.add("cpw.mods.modlauncher.Launcher");
            } else if (gameLauncher.getVersionType() == VersionType.VERSION_1_7_10) {
                classpath.add("net.minecraft.launchwrapper.Launch");
            }
        } else {
            classpath.add("net.minecraft.client.main.Main");
        }

        return classpath;
    }

    public List<String> getVMArgs(GameLauncher gameLauncher, CallBackArgument callBackArgument) {
        return this.vmArgs(gameLauncher, callBackArgument);
    }

    public List<String> getClassPath(GameLauncher gameLauncher) {
        return this.classPath(gameLauncher);
    }
}
