package fr.varchar.varlib.authenticate.mojang.requests;

import com.google.gson.Gson;
import fr.varchar.varlib.exceptions.AuthenticationException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Requester {
    
    private static final Gson GSON = new Gson();
    
    public static String authRequest(String username, String password) {
        final Map<String, Object> map = new HashMap<>();
        final Map<String, Object> map2 = new HashMap<>();
        final Map<String, Object> map3 = new HashMap<>();

        map.put("name", "Minecraft");
        map.put("version", 1);
        map2.put("agent", map);

        map3.putAll(map2);
        map3.put("username", username);
        map3.put("password", password);

        return Requester.GSON.toJson(map3);
    }

    public static String refreshRequest(String accessToken, String clientToken) {
        final Map<String, String> map = new HashMap<>();

        map.put("accessToken", accessToken);
        map.put("clientToken", clientToken);


        return Requester.GSON.toJson(map);
    }

    public static String validateRequest(String accessToken) {
        final Map<String, String> map = new HashMap<>();

        map.put("accessToken", accessToken);

        return Requester.GSON.toJson(map);
    }

    public static String signOutRequest(String username, String password) {
        final Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        return Requester.GSON.toJson(map);
    }

    public static String invalidateRequest(String accessToken, String clientToken) {
        final Map<String, String> map = new HashMap<>();

        map.put("accessToken", accessToken);
        map.put("clientToken", clientToken);

        return Requester.GSON.toJson(map);
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
