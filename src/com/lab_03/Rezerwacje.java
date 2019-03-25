package com.lab_03;

public class Rezerwacje {
    Klienci os_rezerwujaca;
    Wycieczki wycieczka;
    int uczestnicy;
    String termin_platnosci;
    String status;

    Rezerwacje(Klienci os_rezerwujaca, Wycieczki wycieczka, int uczestnicy, String termin_platnosci, String status){
        this.os_rezerwujaca = os_rezerwujaca;
        this.wycieczka = wycieczka;
        this.uczestnicy = uczestnicy;
        this.termin_platnosci = termin_platnosci;
        this.status = status;
    }

    public void Przedawnienie(String data){
        String[] dataparts = data.split("/");
        int datanum = Integer.parseInt(dataparts[2]+dataparts[1]+dataparts[0]);

        String[] terminparts = termin_platnosci.split("/");
        int terminnum = Integer.parseInt(terminparts[2]+terminparts[1]+terminparts[0]);

        if((datanum > terminnum) && (status.equals("Zalozono"))) status = "Przedawnione";
    }

    public void Print(){
        System.out.println();
        System.out.println("---------------------");
        System.out.println("Wycieczka:");
        wycieczka.Print();
        System.out.println("Osoba rezerwujaca:");
        os_rezerwujaca.Print();
        System.out.println("Termin Platnosci: " + termin_platnosci);
        System.out.println("Status: "+ status);
        System.out.println("---------------------");
        System.out.println();
    }
}
