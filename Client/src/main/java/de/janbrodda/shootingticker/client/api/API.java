package de.janbrodda.shootingticker.client.api;

import java.util.List;

import com.google.gson.Gson;

import de.janbrodda.shootingticker.client.settings.Settings;
import de.janbrodda.shootingticker.client.api.WebRequest.Method;
import de.janbrodda.shootingticker.client.data.Competition;
import java.io.IOException;

public class API {

    private static API instance;
    private static Gson gson = new Gson();

    private Settings settings;

    private API(Settings settings) {
        this.settings = settings;
    }

    public static API get() {
        if (instance == null) {
            Settings settings = Settings.get();
            instance = new API(settings);
        }
        return instance;
    }

    public List<Competition> loadAllRemoteCompetitions() throws IOException {
        WebRequest request = new WebRequest(Method.GET, settings.apiUrl + "/api/get");

        String resultJson = request.load();
        Response response = gson.fromJson(resultJson, Response.class);

        if (response != null) {
            if (response.status != Response.Status.Success) {
                throw new RemoteApiException(response.message);
            }
            return response.data.competitions;
        } else {
            return null;
        }

    }

    public Competition loadSingleRemoteCompetition(long competitionId) throws IOException {
        WebRequest request = new WebRequest(Method.GET, settings.apiUrl + "/api/get/" + competitionId);

        String resultJson = request.load();
        Response response = gson.fromJson(resultJson, Response.class);

        if (response.status != Response.Status.Success) {
            throw new RemoteApiException(response.message);
        }

        return response.data.competitions.get(0);
    }

    public void saveCompetition(Competition competition) throws IOException {
        WebRequest request = new WebRequest(Method.POST, settings.apiUrl + "/api/put");
        request.parameters.put("apikey", settings.apiKey);
        request.parameters.put("competition", gson.toJson(competition));

        String resultJson = request.load();
        Response response = gson.fromJson(resultJson, Response.class);

        if (response.status != Response.Status.Success) {
            throw new RemoteApiException(response.message);
        }
    }

    public void deleteCompetition(Competition Competition) throws IOException {
        WebRequest request = new WebRequest(Method.POST, settings.apiUrl + "/api/delete");
        request.parameters.put("apikey", settings.apiKey);
        request.parameters.put("competitionid", Competition.id + "");

        String resultJson = request.load();
        Response response = gson.fromJson(resultJson, Response.class);

        if (response.status != Response.Status.Success) {
            throw new RemoteApiException(response.message);
        }
    }
}
