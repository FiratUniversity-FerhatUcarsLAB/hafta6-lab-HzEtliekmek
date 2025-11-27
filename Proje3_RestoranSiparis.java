/**
 * Ad Soyad: Muhammed Said Bestil
 * Numara: 250542007
 * Proje: Proje 3 - Akilli Restoran Siparis Sistemi
 * Tarih: 27.11.2025
 */

import java.util.Scanner;

public class Proje3_RestoranSiparis {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- RESTORAN SIPARIS SISTEMI ---");
        
        // Girdiler
        System.out.println("1:Tavuk(85), 2:Adana(120), 3:Levrek(110), 4:Manti(65)");
        System.out.print("Ana Yemek Secimi (1-4, Yoksa 0): ");
        int anaYemekSecim = scanner.nextInt();

        System.out.println("1:Corba(25), 2:Humus(45), 3:Borek(55)");
        System.out.print("Baslangic Secimi (1-3, Yoksa 0): ");
        int baslangicSecim = scanner.nextInt();

        System.out.println("1:Kola(15), 2:Ayran(12), 3:Meyve Suyu(35), 4:Limonata(25)");
        System.out.print("Icecek Secimi (1-4, Yoksa 0): ");
        int icecekSecim = scanner.nextInt();

        System.out.println("1:Kunefe(65), 2:Baklava(55), 3:Sutlac(35)");
        System.out.print("Tatli Secimi (1-3, Yoksa 0): ");
        int tatliSecim = scanner.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = scanner.nextInt();

        System.out.print("Ogrenci misiniz? (E/H): ");
        char ogrenciDurumu = scanner.next().toUpperCase().charAt(0);
        boolean isStudent = (ogrenciDurumu == 'E');

        System.out.print("Hangi Gun? (1-7): ");
        int gun = scanner.nextInt();

        // Fiyatlari Getir
        double anaYemekFiyat = getMainDishPrice(anaYemekSecim);
        double baslangicFiyat = getAppetizerPrice(baslangicSecim);
        double icecekFiyat = getDrinkPrice(icecekSecim);
        double tatliFiyat = getDessertPrice(tatliSecim);

        // Ara Toplam
        double araToplam = anaYemekFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;

        // Durum Kontrolleri
        boolean comboVar = isComboOrder(anaYemekSecim > 0, icecekSecim > 0, tatliSecim > 0);
        boolean happyHour = isHappyHour(saat);

        // Indirim Hesaplama (Dokumandaki ornek sirasina gore)
        // Not: Dokuman orneginde indirimler ara toplamdan sirayla dusuluyor.
        
        double toplamIndirim = 0;

        // 1. Combo Indirimi (%15 - Ana yemek, icecek ve tatli varsa)
        if (comboVar) {
            toplamIndirim += araToplam * 0.15;
        }

        // 2. Happy Hour (%20 - Sadece iceceklerde)
        if (happyHour && icecekSecim > 0) {
            toplamIndirim += icecekFiyat * 0.20;
        }

        // 3. 200 TL Uzeri (%10 - Ara toplam uzerinden mi yoksa kalan uzerinden mi? 
        // Dokuman orneginde belirtilmemis ama genelde sepet indirimidir. 
        // Biz dokumandaki ogrenci indirim mantigina benzer sekilde ekleyelim.)
        if (araToplam > 200) {
            toplamIndirim += araToplam * 0.10;
        }

        // Kalan Tutar Uzerinden Ogrenci Indirimi
        double kalanTutar = araToplam - toplamIndirim;
        double ogrenciIndirimi = 0;
        
        // Ogrenci indirimi: Hafta ici (1-5) ekstra %10
        if (isStudent && gun <= 5) {
            ogrenciIndirimi = kalanTutar * 0.10;
        }
        
        double finalTutar = kalanTutar - ogrenciIndirimi;
        double bahsis = calculateServiceTip(finalTutar);

        // Cikti
        System.out.println("\n=== HESAP FISI ===");
        System.out.printf("Ara Toplam      : %.2f TL\n", araToplam);
        System.out.printf("Toplam Indirim  : -%.2f TL\n", (toplamIndirim + ogrenciIndirimi));
        System.out.println("-------------------------");
        System.out.printf("ODENECEK TUTAR  : %.2f TL\n", finalTutar);
        System.out.printf("Onerilen Bahsis : %.2f TL (%%10)\n", bahsis);
        
        if(comboVar) System.out.println("* Combo Menu Indirimi Uygulandi!");
        if(happyHour) System.out.println("* Happy Hour Indirimi Uygulandi!");
    }

    // 1. Ana Yemek Fiyatlari
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85.0;  // Tavuk
            case 2: return 120.0; // Adana
            case 3: return 110.0; // Levrek
            case 4: return 65.0;  // Manti
            default: return 0.0;
        }
    }

    // 2. Baslangic Fiyatlari
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25.0; // Corba
            case 2: return 45.0; // Humus
            case 3: return 55.0; // Borek
            default: return 0.0;
        }
    }

    // 3. Icecek Fiyatlari
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15.0; // Kola
            case 2: return 12.0; // Ayran
            case 3: return 35.0; // Meyve Suyu
            case 4: return 25.0; // Limonata
            default: return 0.0;
        }
    }

    // 4. Tatli Fiyatlari
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65.0; // Kunefe
            case 2: return 55.0; // Baklava
            case 3: return 35.0; // Sutlac
            default: return 0.0;
        }
    }

    // 5. Combo Kontrolu
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    // 6. Happy Hour Kontrolu (14:00 - 17:00 arasi)
    public static boolean isHappyHour(int saat) {
        return (saat >= 14 && saat < 17);
    }

    // 8. Bahsis Onerisi
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }
}
