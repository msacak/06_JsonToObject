package com.sacak.Eczane;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;


public class EczaneApp {
    public static void main(String[] args) {
        EczaneRepository repository = new EczaneRepository();
        List<Eczane> nobetciEczane = repository.getEczaneFromHTTP("ANKARA");
        List<Eczane> nobetciEczaneIlce = repository.getEczaneFromHTTP("ankara","KEÇİÖREN");

        nobetciEczaneIlce.forEach(System.out::println);

//        repository.getDistrictList("Ankara");









    }
}
