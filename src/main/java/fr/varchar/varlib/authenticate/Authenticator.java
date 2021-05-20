package fr.varchar.varlib.authenticate;

import fr.varchar.varlib.authenticate.responses.AuthenticateResponse;
import fr.varchar.varlib.authenticate.requests.Requester;
import fr.varchar.varlib.authenticate.responses.RefreshResponse;

import java.io.IOException;

public class Authenticator {

    public AuthenticateResponse authenticate(String username, String password) throws IOException {
        return (AuthenticateResponse) Requester.sendRequest("authenticate", Requester.authRequest(username, password), AuthenticateResponse.class);
    }

    public RefreshResponse refresh(String accessToken, String clientToken) throws IOException {
        return (RefreshResponse) Requester.sendRequest("refresh", Requester.refreshRequest(accessToken, clientToken), RefreshResponse.class);
    }

    public void validate(String accessToken) throws IOException {
        Requester.sendRequest("validate", Requester.validateRequest(accessToken), null);
    }

    public void signOut(String username, String password) throws IOException {
        Requester.sendRequest("signout", Requester.signOutRequest(username, password), null);
    }

    public void invalidate(String accessToken, String clientToken) throws IOException {
        Requester.sendRequest("invalidate", Requester.invalidateRequest(accessToken, clientToken), null);
    }

}
