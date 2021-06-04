# Join the discord

[discord-support]: https://discord.gg/CjfZQye3GV
## [discord-support]

# Installation

[version]: https://img.shields.io/maven-central/v/fr.varchar-dev/varlib.svg?label=Version
## ![version]

### To add the library to your Gradle project:

```groovy
repositories {
    mavenCentral()
}
```

```groovy
dependencies {
    implementation 'fr.varchar-dev:varlib:VERSION'
}
```

### To add the library to your Maven project:

```xml
<dependency>
    <groupId>fr.varchar-dev</groupId>
    <artifactId>varlib</artifactId>
    <version>VERSION</version>
</dependency>
```

# Authentication

## For authenticate

```java
AuthenticateResponse authenticateResponse = Authenticator.authenticate(username, password);
```

## For refresh 

```java
RefreshResponse refreshResponse = Authenticator.refresh(accessToken, clientToken);
```

### You can add these informations to a GameAuthenticator

```java
GameAuthenticator gameAuthenticator = new GameAuthenticator(authenticateResponse.getSelectedProfile().getName(), authenticateResponse.getAccessToken(), authenticateResponse.getSelectedProfile().getId());
```

# Launching

## Use a GameLauncher

### Without Forge

```java
GameLauncher gameLauncher = new GameLauncher("YourDir", "1.16", VersionType.VERSION_1_13_HIGHER, Type.VANILLA, FolderType.FLOW_UPDATER);
```

### With Forge 1.13 higher

```java
GameLauncher gameLauncher = new GameLauncher("YourDir", "1.16", VersionType.VERSION_1_13_HIGHER, Type.FORGE, FolderType.FLOW_UPDATER, "34.1.23", "1.16.3", "20200911.084530");
```

## Add vm arguments (optional)

```java
gameLauncher.getVmArgs().addAll(Arrays.asList("YourArg"));
```

## Launch the game

```java
gameLauncher.launch(gameAuthenticator);
```

# Bootstrap 

## Use a bootStrap object

Warning : you have to name your bootstrap file : launcher.jar

```java
BootStrap bootStrap = new BootStrap("yourGameDir", "bootstrapDirOnGameDir");
```

## And to launch it

```java
bootStrap.launch();
```

# Logger (optional)
```
Logger logger = new Logger("yourServerName");
```

## Log

### Normal log

```java
logger.log("message");
```

### Log with color

#### Warning : the import for Color's class is ``fr.varchar.varlib.util.logger.Color;``

```java
logger.log("message", Color.RED);
```

## LogError

```java
logger.logError("yourError");
```

don't hesitate to star the repo and join the discord <3
