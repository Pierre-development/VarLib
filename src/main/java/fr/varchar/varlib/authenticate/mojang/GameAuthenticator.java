package fr.varchar.varlib.authenticate.mojang;

public class GameAuthenticator {

    private String username;
    private String accessToken;
    private String clientToken;
    private String uuId;

    public GameAuthenticator(String username, String accessToken, String uuId) {
        this.username = username;
        this.accessToken = accessToken;
        this.uuId = uuId;
    }

    public GameAuthenticator(String username, String accessToken, String clientToken, String uuId) {
        this.username = username;
        this.accessToken = accessToken;
        this.clientToken = clientToken;
        this.uuId = uuId;
    }

    public String getUsername() {
        return username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientToken() {
        return clientToken;
    }

    public String getUuId() {
        return uuId;
    }
}
