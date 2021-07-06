package fr.varchar.varlib.launching.arguments;

import fr.varchar.varlib.GameLauncher;
import fr.varchar.varlib.authenticate.mojang.GameAuthenticator;

import java.util.List;

public abstract class AbstractArguments {
    public abstract List<String> getArgs(GameLauncher gameLauncher);
}
