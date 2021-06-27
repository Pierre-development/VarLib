package fr.varchar.varlib.authenticate.mojang.responses;

import fr.varchar.varlib.authenticate.mojang.Profile;

public class AuthenticateResponse {

    private String clientToken;
    private String accessToken;
    private Profile selectedProfile;
    private Profile[] availableProfiles;

    public AuthenticateResponse(String clientToken, String accessToken, Profile selectedProfile, Profile[] availableProfiles) {
        this.clientToken = clientToken;
        this.accessToken = accessToken;
        this.selectedProfile = selectedProfile;
        this.availableProfiles = availableProfiles;
    }

    public String getClientToken() {
        return clientToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Profile getSelectedProfile() {
        return selectedProfile;
    }

    public Profile[] getAvailableProfiles() {
        return availableProfiles;
    }
}
