package com.sacak.FutbolAPI;

import com.google.gson.*;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.ArrayList;
import java.util.List;

public class FutbolAPIRunner {
    public static void main(String[] args) {
        List<Result> results = displayResults(0);
        results.forEach(System.out::println);

    }

    private static List<Result> displayResults(int leagueKeyNumber){
        List<League> leagueArrayList = getLeagueKeys();
        Gson gson = new Gson();
        ArrayList<Result> resultArrayList = new ArrayList<>();
        HttpResponse<String> response
                = Unirest.get("https://api.collectapi.com/football/results?data.league="+leagueArrayList.get(leagueKeyNumber).getKey())
                .header("content-type", "application/json")
                .header("authorization", "apikey 2oAuR5srZrMvNcHphHSXJW:110IrHcWJTNQxp93WAdxCQ")
                .asString();
        JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("result");
        for(int i =0;i<jsonArray.size();i++){
            JsonObject resultJsonObject = jsonArray.get(i).getAsJsonObject();
            Result result = gson.fromJson(resultJsonObject, Result.class);
            resultArrayList.add(result);
        }
        return resultArrayList;
    }

    private static List<League> getLeagueKeys() {
        Gson gson = new Gson();
        ArrayList<League> leagueArrayList = new ArrayList<>();
        HttpResponse<String> response = Unirest.get("https://api.collectapi.com/football/leaguesList")
                .header("content-type", "application/json")
                .header("authorization", "apikey 2oAuR5srZrMvNcHphHSXJW:110IrHcWJTNQxp93WAdxCQ")
                .asString();

        JsonElement jsonElement = JsonParser.parseString(response.getBody());
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("result");
        for(int i =0;i<jsonArray.size();i++){
            JsonObject leaugeJsonObject = jsonArray.get(i).getAsJsonObject();
            League league = gson.fromJson(leaugeJsonObject, League.class);
            leagueArrayList.add(league);
        }
//        leagueArrayList.stream()
//                .limit(8)
//                .forEach(l-> System.out.printf("%d.%-25s = %s\n",leagueArrayList.indexOf(l),l.getLeague(),l.getKey()));
        return leagueArrayList;
    }
}
