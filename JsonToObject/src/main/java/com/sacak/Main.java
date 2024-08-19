package com.sacak;

/**
 * JSON : JavaScript Object Notation
 * Json: Hangi dili kullandiginiz farketmeksizin, veri tasimak icin kullanilan ortak bir yapidir.
 * xml'e gore cok daha az boyut kapladigi icin tercih edilmektedir.
 *[ ->Array
 * { -> Object
 *     "ad":"Baris", -> ad = key, Baris = value
 *     "yas":30,
 *     "araba":null
 * } bir json 1
 * {
 *     "ad":"Serkan"
 *     "yas":30
 * }json obje2
 * ] - > Json Array
 * [] : Json Arrayi : Birden fazla obje bulunddurabilir.
 * Eskiden json yerine xml kullaniliyordu, json cok daha pratik ve hizli.
 *
 * API : Application Programming Interface
 */

/* Json:
{
    "employees": [
    {"ad":"Selim","soyad":"Tas","maas":50000},
    {"ad":"Ayse","soyad":"Tas","maas":80000},
    {"ad":"Kenan","soyad":"Tas","maas":70000}
    ]
}
yukaraki json formatini xml ile yazmak istersek:
<employees>
    <employee>
        <ad> Selim </> <soyad> Tas </soyad> <maas> 50000 </maas>
    </employee>
    <employee>
        <ad> Ayse </> <soyad> Tas </soyad> <maas> 80000 </maas>
    </employee>
    <employee>
        <ad> Kenan </> <soyad> Tas </soyad> <maas> 70000 </maas>
    </employee>
</employeees>

{
    "employees":["Selim","Ayse","Kenan"], -> json objectte string dizisi tuttuk.
    "butce":5000000,
    "vergiBorcu":false,
    "sirket":null
}


 */

public class Main {
    public static void main(String[] args) {
        UserRepository repository = new UserRepository();
        repository.getUsers(200).stream().filter(user->user.getLocation().getCountry().equalsIgnoreCase("Turkey")).forEach(user->{
            System.out.println("usersArray -> "+user);
        });
    }
    //Odev01 : 10farkli stream sorusu yaz ve coz.
    //Odev02 : collectapi.com -> eczane api kullan.........
}
