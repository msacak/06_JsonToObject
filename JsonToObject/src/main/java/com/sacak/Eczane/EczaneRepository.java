package com.sacak.Eczane;

import com.google.gson.*;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EczaneRepository {

    public List<Eczane> getEczaneFromHTTP(String cityName, String districtName){
        List<Eczane> list = getEczaneFromHTTP(cityName);
        List<Eczane> list1 = null;
        if(list!=null){
            list1 = list.stream().filter(e -> e.getDist().equalsIgnoreCase(districtName)).toList();
            if(list1.isEmpty()){
                System.err.println("Girilen ilce ismi hatali ya da o bolgede hic bir eczane yok.");
                System.exit(0);
            }
        }

        return list1;
    }

    public List<Eczane> getEczaneFromHTTP(String cityName){
        List<Eczane> eczaneList = new ArrayList<>();
        Gson gson = new Gson();

        HttpResponse<String> response = Unirest.get("https://api.collectapi.com/health/dutyPharmacy?il="+cityName)
                .header("content-type", "application/json")
                .header("authorization", "apikey 2oAuR5srZrMvNcHphHSXJW:110IrHcWJTNQxp93WAdxCQ")
                .asString();
        int statusCode = response.getStatus();
        String responseBody = response.getBody(); //-> Json formatinda cikti verir.

        if(responseBody.contains("Wrong district")){
            System.out.println("Girilen sehir ismi hatali.");
            return null;
        }


//        System.out.println("HTTP status code = "+statusCode); //if its 200, it's working.

        if(statusCode==200){
            JsonElement jsonElement = JsonParser.parseString(responseBody);
            JsonObject jsonResponse = jsonElement.getAsJsonObject();
//            JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class); //üstteki 2 metod yerine alternatifi
            JsonArray jsonArray = jsonResponse.getAsJsonArray("result"); //-> ONEMLİ. jsonObject icinden hangi obje grubunu almak istiosan onun adini yazmalisin.
            for(int i =0;i<jsonArray.size();i++){
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                Eczane eczane = gson.fromJson(jsonObject, Eczane.class);
                eczaneList.add(eczane);
            }
        }
        else{
            System.out.println("404 - status code");
        }
        return eczaneList;
    }



    public List<Eczane> getEczaneFromFile(String filePath){ //JsonObjects from Json file.
        List<Eczane> eczaneList = new ArrayList<>();
        String jsonData = "";

        try(Scanner sc = new Scanner(new File(filePath))){
        jsonData = sc.nextLine();

        } catch (FileNotFoundException e) {
            System.out.println("404-FILE NOT FOUND!!!");
        }
        Gson gson = new Gson();
        JsonElement jsonElement = JsonParser.parseString(jsonData);
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonArray resultsJsonArray = asJsonObject.get("result").getAsJsonArray(); //????
        for(int i =0;i<resultsJsonArray.size();i++){
            JsonObject eczaneJsonObject = resultsJsonArray.get(i).getAsJsonObject();
            Eczane eczane = gson.fromJson(eczaneJsonObject, Eczane.class);
            eczaneList.add(eczane);
        }


        return eczaneList;
    }

    public void getDistrictList(String cityName){
        List<Eczane> eczaneFromHTTP = getEczaneFromHTTP(cityName);
        if(eczaneFromHTTP.isEmpty()){
            System.out.println("Girilen sehir ismi hatali.");
            return;
        }
        eczaneFromHTTP.stream().map(Eczane::getDist).distinct().forEach(System.out::println);
    }


}
