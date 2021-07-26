package fr.varchar.varlib.authenticate.mojang;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.varchar.varlib.exceptions.AuthenticationException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Requester {
    
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    protected static String authRequest(String username, String password) {
        final JsonObject jsonObject = new JsonObject();
        final JsonObject jsonObjectUser = new JsonObject();

        jsonObject.addProperty("name", "Minecraft");
        jsonObject.addProperty("version", 1);
        jsonObjectUser.add("agent", jsonObject);

        jsonObjectUser.addProperty("username", username);
        jsonObjectUser.addProperty("password", password);

        return Requester.GSON.toJson(jsonObjectUser);
    }

    protected static String refreshRequest(String accessToken, String clientToken) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("accessToken", accessToken);
        jsonObject.addProperty("clientToken", clientToken);


        return Requester.GSON.toJson(jsonObject);
    }

    protected static String validateRequest(String accessToken) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("accessToken", accessToken);

        return Requester.GSON.toJson(jsonObject);
    }

    protected static String signOutRequest(String username, String password) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);

        return Requester.GSON.toJson(jsonObject);
    }

    protected static String invalidateRequest(String accessToken, String clientToken) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("accessToken", accessToken);
        jsonObject.addProperty("clientToken", clientToken);

        return Requester.GSON.toJson(jsonObject);
    }

    public static Object sendRequest(String endPoint, String jsonPlayload, Class model) throws AuthenticationException {

        final String url = "https://authserver.mojang.com/" + endPoint;

        try {
            final HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", String.valueOf(jsonPlayload.getBytes(StandardCharsets.UTF_8).length));


            final OutputStream outputStream = connection.getOutputStream();
            outputStream.write(jsonPlayload.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();

            if(connection.getResponseCode() == 403) {
                throw new AuthenticationException("Invalid credentials");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(String.valueOf(connection.getResponseCode()).startsWith("2") ? connection.getInputStream() : connection.getErrorStream()));
            if (model == null) {
                return null;
            } else {
                return Requester.GSON.fromJson(br.readLine(), model);
            }
        } catch (IOException e) {
            throw new AuthenticationException("can't send request", e);
        }
    }


}
