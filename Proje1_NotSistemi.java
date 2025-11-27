/**
 * Ad Soyad: Muhammed Said Bestil
 * Numara: 250542007
 * Proje: Proje 1 - Ogrenci Not Degerlendirme Sistemi
 * Tarih: 27.11.2025
 */

import java.util.Scanner;

public class Proje1_NotSistemi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- OGRENCI NOT SISTEMI ---");
        System.out.print("Vize Notu (0-100): ");
        double vize = scanner.nextDouble();

        System.out.print("Final Notu (0-100): ");
        double finalNot = scanner.nextDouble();

        System.out.print("Odev Notu (0-100): ");
        double odev = scanner.nextDouble();

        // Hesaplamalar
        double ortalama = calculateAverage(vize, finalNot, odev);
        boolean gectiMi = isPassingGrade(ortalama);
        String harfNotu = getLetterGrade(ortalama);
        boolean onurListesi = isHonorList(ortalama, vize, finalNot, odev);
        boolean butunleme = hasRetakeRight(ortalama);

        // Cikti Ekrani
        System.out.println("\n=== OGRENCI NOT RAPORU ===");
        System.out.printf("Vize Notu     : %.1f\n", vize);
        System.out.printf("Final Notu    : %.1f\n", finalNot);
        System.out.printf("Odev Notu     : %.1f\n", odev);
        System.out.println("--------------------------");
        System.out.printf("Ortalama      : %.1f\n", ortalama);
        System.out.println("Harf Notu     : " + harfNotu);
        System.out.println("Durum         : " + (gectiMi ? "GECTI" : "KALDI"));
        System.out.println("Onur Listesi  : " + (onurListesi ? "EVET" : "HAYIR"));
        System.out.println("Butunleme     : " + (butunleme ? "HAKKI VAR" : "YOK"));
        
        scanner.close();
    }

    // Ortalama Hesaplama: Vize %30 + Final %40 + Odev %30
    public static double calculateAverage(double vize, double finalNot, double odev) {
        return (vize * 0.30) + (finalNot * 0.40) + (odev * 0.30);
    }

    // Gecme Durumu: 50 ve uzeri GECTI
    public static boolean isPassingGrade(double ortalama) {
        return ortalama >= 50.0;
    }

    // Harf Notu Hesaplama
    public static String getLetterGrade(double ortalama) {
        if (ortalama >= 90) return "A";
        else if (ortalama >= 80) return "B";
        else if (ortalama >= 70) return "C";
        else if (ortalama >= 60) return "D";
        else return "F";
    }

    // Onur Listesi: Ortalama > 85 VE hicbir not < 70 olmamali
    public static boolean isHonorList(double ortalama, double vize, double finalNot, double odev) {
        return (ortalama > 85) && (vize >= 70 && finalNot >= 70 && odev >= 70);
    }

    // Butunleme Hakki: 40 <= ortalama < 50
    public static boolean hasRetakeRight(double ortalama) {
        return (ortalama >= 40.0) && (ortalama < 50.0);
    }
}
