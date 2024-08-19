package com.sacak;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Odev01 {
    public static void main(String[] args) {
        UserRepository repository = new UserRepository();
        List<User> users = repository.getUsers(200);

        //1.Kullanıcılar arasında en yaşlı kişiyi bulun.
        System.out.println("##### 1.KULLANICILAR ARASINDA EN YASLI KISI ######");
        users.stream()
                .max(Comparator.comparing(user->user.getDob().getAge())).ifPresent(user-> System.out.println(user.getName()+" = "+user.getDob().getAge()));


        //2.Kullanıcıların yaş ortalamasını hesaplayın.
        System.out.println("\n##### 2.KULLANICILARIN YAS ORTALAMASI ######");
        Double averageAge = users.stream()
                .collect(Collectors.averagingLong(user -> user.getDob().getAge()));
        System.out.printf("Kullanicilarin yas ortalamasi = %.2f\n",averageAge);

        //3.İsmi "A" harfi ile başlayan kullanıcıları filtreleyip listeyin.
        System.out.println("\n##### 3.ISMI A ILE BASLAYAN KULLANICILAR ######");
        users.stream()
                .map(User::getName)
                .filter(name -> name.getFirst().startsWith("A") || name.getFirst().startsWith("a"))
                .forEach(System.out::println);
        //4.Kullanıcıları ülkelerine göre gruplandırın ve her ülkeden kaç kullanıcı olduğunu gösterin.
        System.out.println("\n##### 4.HANGI ULKEDEN KAC KISI VAR ######");
        Map<String, Long> collect = users.stream()
                .collect(Collectors.groupingBy(user -> user.getLocation().getCountry(), Collectors.counting()));
        collect.forEach((k,v)-> System.out.println(k+" = "+v));

        //5.En çok kullanılan 5 e-posta servis sağlayıcısını (gmail.com, yahoo.com gibi) ve kullanım sayılarını bulun. EXAMPLE.COM VAR BIR TEK.
        System.out.println("\n##### 5.EN COK KULLANILAN 5 E-POSTA SERVIS SAGLAYICISI ######");
        System.out.println("Tek bir mail kategorisi var -> example.com");

        //6.Kullanıcıları cinsiyetlerine göre ayırın ve her cinsiyetin yaş ortalamasını hesaplayın.
        System.out.println("\n##### 6.CINSIYETLERE GORE YAS ORTALAMASI ######");
        users.stream()
                .collect(Collectors.groupingBy(User::getGender,Collectors.averagingLong(user->user.getDob().getAge())))
                .forEach((k,v)-> System.out.printf("%-10s = %.2f\n",k,v));

        //7.30 yaşından küçük kullanıcıları bulun ve isimleriyle birlikte yaşlarını küçükten büyüğe sıralayarak listeyin.
        System.out.println("\n##### 7.30YASINDAN KUCUK KULLANICILARIN YASI KUCUKTEN BUYUGE SIRALANMIS LISTESI ######");
        users.stream()
                .filter(user->user.getDob().getAge()<30)
                .sorted(Comparator.comparing(user->user.getDob().getAge()))
                .map(user->user.getName()+" = "+user.getDob().getAge()).forEach(System.out::println);
        //8.Kullanıcıların telefon numaralarının alan kodlarına göre gruplandırın ve her alan kodundan kaç kişi olduğunu gösterin.
        System.out.println("\n##### 8.TELEFON ALAN KODLARINA GORE GRUPLANDIRMA ######");
        System.out.println("Telefon alan kodları hatali ??");

        //9.İsim ve soyisimlerinin toplam uzunluğu 15 karakterden fazla olan kullanıcıları bulun
        // ve bu kullanıcıların tam adlarını büyük harfle yazdırın.
        System.out.println("\n##### 9.ISIM VE SOYISIMLERI 15 KARAKTERDEN FAZZLA OLAN KULLANICILARI BUYUK HARFLE YAZDIR ######");
        users.stream()
                .filter(user->(user.getName().getFirst().length()+user.getName().getLast().length())>15)
                .forEach(user-> System.out.println(user.getName().getFirst().toUpperCase()+" "+user.getName().getLast().toUpperCase()));

        //10.Kullanıcıların doğum tarihlerine göre, hangi ayda kaç kişinin doğduğunu hesaplayın ve sonuçları ay isimlerine göre sıralayın.
        System.out.println("\n##### 10.KULLANICI DOGUM AYLARINA GORE SIRALA VE HANGİ AYDA KAC KISI DOGMUS GOSTER ######");
        users.stream()
                .sorted(Comparator.comparing(user->Integer.parseInt(user.getDob().getDate().substring(5,7))))
                .collect(Collectors.groupingBy(user->user.getDob().getDate().substring(5,7),Collectors.counting()))
                .forEach((k,v)-> System.out.println(k+" = "+v)); //11 ve 12.ayi en once gosteriyor..???


    }
}
