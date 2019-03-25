package com.lab_03;

public class Wycieczki {
    int index;
    String termin_rozpoczecia;
    String termin_zakonczenia;
    String opis;
    String kierunek;
    int miejsca;
    int wykup_miejsca;

    public Wycieczki(int index, String termin_rozpoczecia, String termin_zakonczenia, String opis, String kierunek, int miejsca, int wykup_miejsca) {
        this.index = index;
        this.termin_rozpoczecia = termin_rozpoczecia;
        this.termin_zakonczenia = termin_zakonczenia;
        this.opis = opis;
        this.kierunek = kierunek;
        this.miejsca = miejsca;
        this.wykup_miejsca = wykup_miejsca;
    }

    public void Print(){
        System.out.println("---------------------");
        System.out.println("Kierunek: "+ kierunek);
        System.out.println(opis);
        System.out.println("Termin Rozpoczecia: "+termin_rozpoczecia);
        System.out.println("Termin Zakonczenia: "+termin_zakonczenia);
        System.out.println("Wyjazd dla "+miejsca +" osob");
        System.out.println("---------------------");
        System.out.println();
    }
    public void Print1(){
        System.out.println("---------------------");
        System.out.println("Kierunek: "+ kierunek);
        System.out.println(opis);
        System.out.println("Termin Rozpoczecia: "+termin_rozpoczecia);
        System.out.println("Termin Zakonczenia: "+termin_zakonczenia);
        System.out.println("Wyjazd dla "+miejsca +" osob");
        System.out.println("Ilosc wykupionych miejsc: "+wykup_miejsca);
        System.out.println("---------------------");
        System.out.println();
    }

    public int getWykup_miejsca() {
        return wykup_miejsca;
    }
}
