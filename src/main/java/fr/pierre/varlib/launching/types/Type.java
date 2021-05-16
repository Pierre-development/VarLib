package fr.pierre.varlib.launching.types;

import fr.pierre.varlib.launching.GameLauncher;

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
                if(gameLauncher.getVersionType() == VersionType.V1_13_HIGHER && gameLauncher.getType() == FORGE) {
                    if (file.contains("guava") && file.contains("25") || file.contains("20")) {
                        libsRemove.add(file);
                    }

                    if (file.contains("asm")) {
                        if (file.contains("6") && !gameLauncher.getVersion().contains("1.14"))
                            libsRemove.add(file);
                    }
                } else if(gameLauncher.getVersionType() == VersionType.V1_7_10 && gameLauncher.getType() == FORGE) {
                    if(file.contains("guava") && file.contains("15")) {
                        libsRemove.add(file);
                    }
                }
            });
            libsRemove.forEach(libs::remove);
            libs.forEach(sb::append);
            sb.append(gameLauncher.getMinecraftClient().getAbsolutePath());


            args.add(sb.toString());
            System.out.println(sb);
            //args.add("C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\electronwill\\night-config\\core\\3.6.2\\core-3.6.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\electronwill\\night-config\\toml\\3.6.2\\toml-3.6.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\github\\jponge\\lzma-java\\1.3\\lzma-java-1.3.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\google\\code\\findbugs\\jsr305\\3.0.2\\jsr305-3.0.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\google\\code\\gson\\gson\\2.8.0\\gson-2.8.0.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\google\\errorprone\\error_prone_annotations\\2.1.3\\error_prone_annotations-2.1.3.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\google\\guava\\guava\\21.0\\guava-21.0.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\google\\j2objc\\j2objc-annotations\\1.1\\j2objc-annotations-1.1.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\ibm\\icu\\icu4j\\66.1\\icu4j-66.1.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\mojang\\authlib\\1.6.25\\authlib-1.6.25.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\mojang\\brigadier\\1.0.17\\brigadier-1.0.17.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\mojang\\datafixerupper\\4.0.26\\datafixerupper-4.0.26.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\mojang\\javabridge\\1.0.22\\javabridge-1.0.22.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\mojang\\patchy\\1.1\\patchy-1.1.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\mojang\\text2speech\\1.11.3\\text2speech-1.11.3.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\com\\nothome\\javaxdelta\\2.0.1\\javaxdelta-2.0.1.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\commons-codec\\commons-codec\\1.10\\commons-codec-1.10.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\commons-io\\commons-io\\2.4\\commons-io-2.4.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\commons-io\\commons-io\\2.5\\commons-io-2.5.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\commons-logging\\commons-logging\\1.1.3\\commons-logging-1.1.3.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\cpw\\mods\\grossjava9hacks\\1.3.0\\grossjava9hacks-1.3.0.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\cpw\\mods\\modlauncher\\7.0.1\\modlauncher-7.0.1.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\de\\siegmar\\fastcsv\\1.0.2\\fastcsv-1.0.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\io\\netty\\netty-all\\4.1.25.Final\\netty-all-4.1.25.Final.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\it\\unimi\\dsi\\fastutil\\8.2.1\\fastutil-8.2.1.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\java\\dev\\jna\\jna\\4.4.0\\jna-4.4.0.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\java\\dev\\jna\\platform\\3.4.0\\platform-3.4.0.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\java\\jinput\\jinput\\2.0.5\\jinput-2.0.5.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\java\\jutils\\jutils\\1.0.0\\jutils-1.0.0.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\jodah\\typetools\\0.8.1\\typetools-0.8.1.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\md-5\\SpecialSource\\1.8.5\\SpecialSource-1.8.5.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraft\\client\\1.16.3-20200911.084530\\client-1.16.3-20200911.084530-extra.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraft\\client\\1.16.3-20200911.084530\\client-1.16.3-20200911.084530-slim.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraft\\client\\1.16.3-20200911.084530\\client-1.16.3-20200911.084530-srg.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraftforge\\accesstransformers\\2.2.0-shadowed\\accesstransformers-2.2.0-shadowed.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraftforge\\binarypatcher\\1.0.12\\binarypatcher-1.0.12.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraftforge\\coremods\\3.0.0\\coremods-3.0.0.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraftforge\\eventbus\\3.0.3-service\\eventbus-3.0.3-service.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraftforge\\forge\\1.16.3-34.1.23\\forge-1.16.3-34.1.23-client.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraftforge\\forge\\1.16.3-34.1.23\\forge-1.16.3-34.1.23-universal.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraftforge\\forge\\1.16.3-34.1.23\\forge-1.16.3-34.1.23.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraftforge\\forgespi\\3.1.1\\forgespi-3.1.1.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraftforge\\installertools\\1.1.11\\installertools-1.1.11.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraftforge\\jarsplitter\\1.1.2\\jarsplitter-1.1.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecraftforge\\unsafe\\0.2.0\\unsafe-0.2.0.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\minecrell\\terminalconsoleappender\\1.2.0\\terminalconsoleappender-1.2.0.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\sf\\jopt-simple\\jopt-simple\\4.9\\jopt-simple-4.9.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\sf\\jopt-simple\\jopt-simple\\5.0.3\\jopt-simple-5.0.3.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\sf\\jopt-simple\\jopt-simple\\5.0.4\\jopt-simple-5.0.4.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\net\\sf\\opencsv\\opencsv\\2.3\\opencsv-2.3.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\apache\\commons\\commons-compress\\1.8.1\\commons-compress-1.8.1.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\apache\\commons\\commons-lang3\\3.5\\commons-lang3-3.5.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\apache\\httpcomponents\\httpclient\\4.3.3\\httpclient-4.3.3.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\apache\\httpcomponents\\httpcore\\4.3.2\\httpcore-4.3.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\apache\\logging\\log4j\\log4j-api\\2.11.2\\log4j-api-2.11.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\apache\\logging\\log4j\\log4j-api\\2.8.1\\log4j-api-2.8.1.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\apache\\logging\\log4j\\log4j-core\\2.11.2\\log4j-core-2.11.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\apache\\logging\\log4j\\log4j-core\\2.8.1\\log4j-core-2.8.1.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\apache\\maven\\maven-artifact\\3.6.0\\maven-artifact-3.6.0.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\checkerframework\\checker-qual\\2.0.0\\checker-qual-2.0.0.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\codehaus\\mojo\\animal-sniffer-annotations\\1.14\\animal-sniffer-annotations-1.14.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\jline\\jline\\3.12.1\\jline-3.12.1.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\lwjgl\\lwjgl\\3.2.2\\lwjgl-3.2.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\lwjgl\\lwjgl-glfw\\3.2.2\\lwjgl-glfw-3.2.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\lwjgl\\lwjgl-jemalloc\\3.2.2\\lwjgl-jemalloc-3.2.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\lwjgl\\lwjgl-openal\\3.2.2\\lwjgl-openal-3.2.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\lwjgl\\lwjgl-opengl\\3.2.2\\lwjgl-opengl-3.2.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\lwjgl\\lwjgl-stb\\3.2.2\\lwjgl-stb-3.2.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\lwjgl\\lwjgl-tinyfd\\3.2.2\\lwjgl-tinyfd-3.2.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\ow2\\asm\\asm\\7.2\\asm-7.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\ow2\\asm\\asm-analysis\\7.2\\asm-analysis-7.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\ow2\\asm\\asm-commons\\7.2\\asm-commons-7.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\ow2\\asm\\asm-tree\\7.2\\asm-tree-7.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\ow2\\asm\\asm-util\\7.2\\asm-util-7.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\org\\spongepowered\\mixin\\0.8.2\\mixin-0.8.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\oshi-project\\oshi-core\\1.1\\oshi-core-1.1.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\libraries\\trove\\trove\\1.0.2\\trove-1.0.2.jar;C:\\Users\\Pierre\\AppData\\Roaming\\.ee\\client.jar");


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
            if (gameLauncher.getVersionType() == VersionType.V1_8_HIGHER) {
                args.add("net.minecraft.launchwrapper.Launch");
                args.add("--tweakClass");
                args.add("net.minecraftforge.fml.common.launcher.FMLTweaker");

            } else if (gameLauncher.getVersionType() == VersionType.V1_13_HIGHER) {
                args.add("cpw.mods.modlauncher.Launcher");
            } else if(gameLauncher.getVersionType() == VersionType.V1_7_10) {
                args.add("net.minecraft.launchwrapper.Launch");
                args.add("--tweakClass");
                args.add("cpw.mods.fml.common.launcher.FMLTweaker");
            }
            return args;
        }
    };

    public abstract List<String> getArgs(GameLauncher gameLauncher);

}
