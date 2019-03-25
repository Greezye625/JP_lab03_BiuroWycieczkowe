package com.lab_03;

class Klienci {
    int index;
    String imie;
    String nazwisko;
    int wykup_wyciecz;

    Klienci(int index, String imie, String nazwisko, int wykup_wyciecz) {
        this.index = index;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wykup_wyciecz = wykup_wyciecz;
    }

    int getWykup_wyciecz() {
        return wykup_wyciecz;
    }

    void Print(){
        System.out.println("---------------------");
        System.out.print(imie +" ");
        System.out.println(nazwisko);
        System.out.println("---------------------");
        System.out.println();
    }
}