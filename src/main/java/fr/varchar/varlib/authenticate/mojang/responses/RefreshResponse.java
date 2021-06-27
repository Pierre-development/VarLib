package fr.varchar.varlib.authenticate.mojang.responses;

import fr.varchar.varlib.authenticate.mojang.Profile;

public class RefreshResponse {

    private String accessToken;
    private String clientToken;
    private Profile selectedProfile;

    public RefreshResponse(String accessToken, String clientToken, Profile selectedProfile) {
        this.accessToken = accessToken;
        this.clientToken = clientToken;
        this.selectedProfile = selectedProfile;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientToken() {
        return clientToken;
    }

    public Profile getSelectedProfile() {
        return selectedProfile;
    }
}
