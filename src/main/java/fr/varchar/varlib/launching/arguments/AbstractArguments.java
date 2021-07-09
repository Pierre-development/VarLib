package fr.varchar.varlib.launching.arguments;

import fr.varchar.varlib.launching.GameLauncher;

import java.util.List;

public abstract class AbstractArguments {
    public abstract List<String> getArgs(GameLauncher gameLauncher);
}
