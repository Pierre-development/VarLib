package fr.varchar.varlib.authenticate;

import fr.varchar.varlib.authenticate.requests.Requester;
import fr.varchar.varlib.authenticate.responses.AuthenticateResponse;
import fr.varchar.varlib.authenticate.responses.RefreshResponse;
import fr.varchar.varlib.exceptions.AuthenticationException;

public class Authenticator {

    public AuthenticateResponse authenticate(String username, String password) throws AuthenticationException {
        return (AuthenticateResponse) Requester.sendRequest("authenticate", Requester.authRequest(username, password), AuthenticateResponse.class);
    }

    public RefreshResponse refresh(String accessToken, String clientToken) throws AuthenticationException {
        return (RefreshResponse) Requester.sendRequest("refresh", Requester.refreshRequest(accessToken, clientToken), RefreshResponse.class);
    }

    public void validate(String accessToken) throws AuthenticationException {
        Requester.sendRequest("validate", Requester.validateRequest(accessToken), null);
    }

    public void signOut(String username, String password) throws AuthenticationException {
        Requester.sendRequest("signout", Requester.signOutRequest(username, password), null);
    }

    public void invalidate(String accessToken, String clientToken) throws AuthenticationException {
        Requester.sendRequest("invalidate", Requester.invalidateRequest(accessToken, clientToken), null);
    }

}
