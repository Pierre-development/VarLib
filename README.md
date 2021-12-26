<h1 align="center">
  VarLib
</h1>
<p align="center">
    <a href="https://www.java.com/fr/">
        <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Made with Java">
    </a>
    <a href="https://github.com/Pierre-development/VarLib">
        <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white" alt="Uses git">
    </a>
    <br>
    <a href="https://search.maven.org/artifact/fr.varchar-dev/varlib">
        <img src="https://img.shields.io/maven-central/v/fr.varchar-dev/varlib.svg?style=for-the-badge" alt="Uses git">
    </a>
    <br>
    <a href="https://github.com/Asthowen/AlphacodersWallpaperDownloader/stargazers">
        <img src="https://img.shields.io/github/stars/Pierre-development/VarLib?style=for-the-badge" alt="Stars">
    </a>
    <a href="https://github.com/Pierre-development/VarLib/blob/master/LICENSE">
        <img src="https://img.shields.io/github/license/Pierre-development/VarLib?style=for-the-badge" alt="License">
    </a>
</p>
<h3 align="center">
    <strong>Java Library for Minecraft authenticating and launching.</strong>
</h3>

## Installation
### With Gradle
Add mavenCentral in your repositories:
```java
repositories {
    mavenCentral()
}
```
And add VarLib in your dependencies:
```java
dependencies {
    implementation 'fr.varchar-dev:varlib:VERSION'
}
```

### With Maven
```xml
<dependency>
    <groupId>fr.varchar-dev</groupId>
    <artifactId>varlib</artifactId>
    <version>VERSION</version>
</dependency>
```

## Authentication
### Authenticate
Create new object AuthenticateResponse with username and password:
```java
AuthenticateResponse authenticateResponse = Authenticator.authenticate(username, password);
```

### Refresh
Create new object RefreshResponse with accessToken and clientToken:
```java
RefreshResponse refreshResponse = Authenticator.refresh(accessToken, clientToken);
```

### Add information to launch
Create new object GameAuthenticator:
```java
GameAuthenticator gameAuthenticator = new GameAuthenticator(authenticateResponse.getSelectedProfile().getName(), authenticateResponse.getAccessToken(), authenticateResponse.getSelectedProfile().getId());
```

## Launch
## With Minecraft Vanilla
```java
AbstractGameLauncherBuilder launcherBuilder = new GameLauncherVanillaBuilder(folder)
     .setVersion("1.12")
     .setVersionType(VersionType.VERSION_1_8_HIGHER)   
     .setFolderType(folderType)
     .setGameAuthenticator(gameAuthenticator)
```

## With Minecraft Forge
```java
AbstractGameLauncherBuilder gamelauncher = new GameLauncherForgeBuilder(folder)
     .setVersion("1.16")
     .setVersionType(VersionType.VERSION_1_13_HIGHER)   
     .setFolderType(folderType)
     .setGameAuthenticator(gameAuthenticator)
```

if your version equal 1.13 or higher, you have to set FML informations 

### Launch
```java
launcherBuilder.build().launch();
```

## Logger
### Init
Create new object Logger with yourServerName:
```java
Logger logger = new Logger("yourServerName");
```

### Log
```java
logger.log("message");
```

### Log with color
Warning: the import for Color's class is `fr.varchar.varlib.util.logger.Color;`!
```java
logger.log("message", Color.RED);
```

### Error
```java
logger.logError("yourError");
```

## Discord
**You can join our [Discord](https://discord.gg/CjfZQye3GV)!**

## License
**[VarLib](https://github.com/Pierre-development/VarLib/) | [MIT License](https://github.com/Pierre-development/VarLib/blob/master/LICENSE)**