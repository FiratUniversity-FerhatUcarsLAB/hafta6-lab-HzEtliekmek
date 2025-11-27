/**
 * Ad Soyad: Muhammed Said Bestil
 * Numara: 250542007
 * Proje: Proje 2 - Sinema Bileti Fiyatlandirma
 * Tarih: 27.11.2025
 */

import java.util.Scanner;

public class Proje2_SinemaBileti {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- SINEMA BILETI SISTEMI ---");
        
        System.out.print("Gun (1-Pzt, ..., 7-Paz): ");
        int gun = scanner.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = scanner.nextInt();

        System.out.print("Yasiniz: ");
        int yas = scanner.nextInt();

        System.out.print("Meslek (1: Ogrenci, 2: Ogretmen, 3: Diger): ");
        int meslek = scanner.nextInt();

        System.out.print("Film Turu (1: 2D, 2: 3D, 3: IMAX, 4: 4DX): ");
        int filmTuru = scanner.nextInt();

        // Hesaplamalar
        double temelFiyat = calculateBasePrice(gun, saat);
        double indirimMiktari = calculateDiscount(yas, meslek, gun, temelFiyat);
        double ekstraUcret = getFormatExtra(filmTuru);
        double toplamFiyat = calculateFinalPrice(temelFiyat, indirimMiktari, ekstraUcret);

        // Bilet Bilgisi
        generateTicketInfo(temelFiyat, indirimMiktari, ekstraUcret, toplamFiyat);
        
        scanner.close();
    }

    // Hafta sonu mu? (Cmt=6, Paz=7)
    public static boolean isWeekend(int gun) {
        return (gun == 6 || gun == 7);
    }

    // Matine mi? (12:00 oncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // Temel Fiyat Hesaplama
    public static double calculateBasePrice(int gun, int saat) {
        boolean haftaSonu = isWeekend(gun);
        boolean matine = isMatinee(saat);

        if (haftaSonu) {
            return matine ? 55.0 : 85.0; // Hafta sonu Matine / Normal
        } else {
            return matine ? 45.0 : 65.0; // Hafta ici Matine / Normal
        }
    }

    // Indirim Hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun, double fiyat) {
        double indirimOrani = 0.0;

        // Yas indirimleri (Oncelikli kontrol edilebilir veya kümülatif degilse en yuksegi alinir)
        // Burada senaryo geregi en uygun indirimi uyguluyoruz.
        
        if (yas > 65) {
            indirimOrani = 0.30; // %30 Yasli indirimi
        } else if (yas < 12) {
            indirimOrani = 0.25; // %25 Cocuk indirimi
        } else {
            // Meslek indirimleri (Switch-case ile)
            switch (meslek) {
                case 1: // Ogrenci
                    // Pzt(1) - Per(4) arasi %20, Cuma(5) - Paz(7) arasi %15
                    if (gun >= 1 && gun <= 4) {
                        indirimOrani = 0.20;
                    } else {
                        indirimOrani = 0.15;
                    }
                    break;
                case 2: // Ogretmen
                    // Sadece Carsamba (3) gunu %35
                    if (gun == 3) {
                        indirimOrani = 0.35;
                    }
                    break;
                default:
                    indirimOrani = 0.0;
            }
        }
        return fiyat * indirimOrani;
    }

    // Format Ekstra Ucreti (Switch-case)
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0.0;  // 2D
            case 2: return 25.0; // 3D
            case 3: return 35.0; // IMAX
            case 4: return 50.0; // 4DX
            default: return 0.0;
        }
    }

    // Toplam Fiyat Hesaplama
    public static double calculateFinalPrice(double temel, double indirim, double ekstra) {
        return (temel - indirim) + ekstra;
    }

    // Bilet Bilgisi Yazdirma
    public static void generateTicketInfo(double temel, double indirim, double ekstra, double toplam) {
        System.out.println("\n=== BILET DETAYI ===");
        System.out.printf("Temel Fiyat    : %.2f TL\n", temel);
        System.out.printf("Indirim        : -%.2f TL\n", indirim);
        System.out.printf("Format Ekstra  : +%.2f TL\n", ekstra);
        System.out.println("--------------------");
        System.out.printf("TOPLAM TUTAR   : %.2f TL\n", toplam);
    }
}
