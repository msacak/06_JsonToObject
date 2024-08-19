package com.sacak;


import com.google.gson.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class UserRepository {

    public List<User> getUsers(int count){
        List<User> users = new ArrayList<>();
        Scanner sc;
        String jsonData ="";
        try {
            sc=new Scanner(new URL("https://randomuser.me/api/?results="+count).openStream());

            jsonData = sc.nextLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonElement jsonElement = JsonParser.parseString(jsonData);
//        System.out.println("jsonElement = "+jsonElement); //json turunde yazdirir, jesonElement ozel metodlarına erisim saglarsin.
//        System.out.println("------------");

        //jsonElement icinde { ile basladigi icin bir jsonObject oldugunu biliyoruz. Bu yuzden icindeki jsonObjecti almak icin
        //getAsJsonObject metodunu kullaniyoruz.
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        //jsonObject icinde bizi ilgilendiren user datalari result kisminda oldugu icin results yaziyoruz. ardindan results kisminda
        // [ ile basladigi icin jsonArray oldugunu biliyoruz. bu yuzden jsonArrayi almak icin getAsJsonArray() metodunu kullaniyoruz.
        JsonArray resultsJsonArray = asJsonObject.get("results").getAsJsonArray();
//        System.out.println("resultsJsonArray = "+(resultsJsonArray));
//        System.out.println("------------------");
        for(int i =0;i<resultsJsonArray.size();i++){
            JsonObject userJsonObject = resultsJsonArray.get(i).getAsJsonObject(); //sadece results kismi olan objecte ulastik.
//            System.out.println("userJsonObject = "+userJsonObject);
            User user = gson.fromJson(userJsonObject, User.class);//parametrede Class<T> classofT seklinde bir yapi gorurseniz vereceginiz sinifin sonuna .class ekleyin.
//            System.out.println("user = "+user);
            users.add(user);
        }

        return users;
    }

}
