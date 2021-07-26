package fr.varchar.varlib.authenticate.mojang;

import fr.varchar.varlib.authenticate.mojang.responses.AuthenticateResponse;
import fr.varchar.varlib.authenticate.mojang.responses.RefreshResponse;
import fr.varchar.varlib.exceptions.AuthenticationException;

public class Authenticator {

    public static AuthenticateResponse authenticate(String username, String password) throws AuthenticationException {
        return (AuthenticateResponse) Requester.sendRequest("authenticate", Requester.authRequest(username, password), AuthenticateResponse.class);
    }

    public static RefreshResponse refresh(String accessToken, String clientToken) throws AuthenticationException {
        return (RefreshResponse) Requester.sendRequest("refresh", Requester.refreshRequest(accessToken, clientToken), RefreshResponse.class);
    }

    public static void validate(String accessToken) throws AuthenticationException {
        Requester.sendRequest("validate", Requester.validateRequest(accessToken), null);
    }

    public static void signOut(String username, String password) throws AuthenticationException {
        Requester.sendRequest("signout", Requester.signOutRequest(username, password), null);
    }

    public void invalidate(String accessToken, String clientToken) throws AuthenticationException {
        Requester.sendRequest("invalidate", Requester.invalidateRequest(accessToken, clientToken), null);
    }

}
