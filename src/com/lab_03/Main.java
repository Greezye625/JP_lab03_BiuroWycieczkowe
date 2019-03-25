package com.lab_03;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {

    static Scanner reader = new Scanner(System.in);
    static Scanner reader1 = new Scanner(System.in);

    public static void main(String[] args) {


        String data = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        List <Wycieczki> ListaWycieczek = new ArrayList<>();
        List <Klienci> ListaKlientow = new ArrayList<>();
        List <Rezerwacje> ListaRezerwacji = new ArrayList<>();


        //zczytanie danych wycieczek z pliku i umieszczenie ich w liscie

        List DaneWycieczek = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Wycieczki.txt"));

            String line = br.readLine();

            while (line != null) {
                DaneWycieczek.add(line);
                line = br.readLine();
            }

            br.close();
        } catch (Exception ex) {
            System.out.println("nie udalo sie odczytac pliku");
        }

        int indw = 0;
        for (int i = 0; i<DaneWycieczek.size(); i+=7){
            int index = Integer.parseInt((String) DaneWycieczek.get(i));
            String miejsce = (String) DaneWycieczek.get(i+1);
            String opis = (String) DaneWycieczek.get(i+2);
            String ter1 = (String) DaneWycieczek.get(i+3);
            String ter2 = (String) DaneWycieczek.get(i+4);
            int ilmiejsc = Integer.parseInt((String) DaneWycieczek.get(i+5));
            int wykupmiejsc = Integer.parseInt((String) DaneWycieczek.get(i+6));
            Wycieczki w = new Wycieczki(index, ter1, ter2, opis, miejsce,ilmiejsc,wykupmiejsc);
            ListaWycieczek.add(w);
            indw = index+1;
        }


        //zczytanie danych klientow i umiesczenie ich w liscie


        List DaneKlientow = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader("klienci.txt"));

            String line = br.readLine();

            while (line != null) {
                DaneKlientow.add(line);
                line = br.readLine();
            }

            br.close();
        } catch (Exception ex) {
            System.out.println("nie udalo sie odczytac pliku");
        }


        int indk = 0;
        for(int i = 0; i<DaneKlientow.size();i+=3){
            int index = Integer.parseInt((String) DaneKlientow.get(i));
            String imie = (String) DaneKlientow.get(i+1);
            String nazwisko = (String) DaneKlientow.get(i+2);
            Klienci k = new Klienci(index,imie,nazwisko,0);
            ListaKlientow.add(k);
            indk = index+1;
        }

        List DaneRezerwacji = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Rezerwacje.txt"));

            String line = br.readLine();

            while (line != null) {
                DaneRezerwacji.add(line);
                line = br.readLine();
            }

            br.close();
        } catch (Exception ex) {
            System.out.println("nie udalo sie odczytac pliku");
        }

        for(int i=0; i<DaneRezerwacji.size(); i+=5){
            int klient = Integer.parseInt((String) DaneRezerwacji.get(i));
            int wycieczka = Integer.parseInt((String) DaneRezerwacji.get(i+1));
            int uczestnicy = Integer.parseInt((String) DaneRezerwacji.get(i+2));
            String termin = (String) DaneRezerwacji.get(i+3);
            String status = (String) DaneRezerwacji.get(i+4);

            int klientnum=0;
            for(int j=0; j<ListaKlientow.size(); j++){
                if(ListaKlientow.get(j).index == klient){
                    klientnum = j;
                }
            }

            int wycieczkanum=0;
            for(int k=0; k<ListaWycieczek.size(); k++){
                if(ListaWycieczek.get(k).index == wycieczka){
                    wycieczkanum = k;
                }
            }

            Rezerwacje rez = new Rezerwacje(ListaKlientow.get(klientnum),ListaWycieczek.get(wycieczkanum),uczestnicy,termin,status);

            ListaRezerwacji.add(rez);
        }


        for(int i=0; i<ListaRezerwacji.size(); i++){
            ListaRezerwacji.get(i).Przedawnienie(data);
            for(int j=0; j<ListaWycieczek.size(); j++){
                if((ListaWycieczek.get(j).index == ListaRezerwacji.get(i).wycieczka.index) && ListaRezerwacji.get(i).status.equalsIgnoreCase("Przedawnione")){
                    ListaWycieczek.get(j).wykup_miejsca -= ListaRezerwacji.get(i).uczestnicy;
                }
            }
        }



        byte op;

        do {


            //MENU
            System.out.println("Witamy w Biurze Wycieczkowym");
            System.out.println("1 - Zarzadzanie Ofertami");
            System.out.println("2 - Zarządzanie Klientami");
            System.out.println("3 - Szukaj Ofert");
            System.out.println("4 - Zarządzanie Rezerwacjami");
            System.out.println("5 - Zestawienia");
            System.out.println("6 - Symulacja Przedawnienia");
            System.out.println("0 - Wyjscie");
            op = reader.nextByte();

            switch (op) {
                case 1: {
                    byte op1;

                    System.out.println("1 - Dodaj Oferte");
                    System.out.println("2 - Modyfikuj Oferte");
                    System.out.println("3 - Usun Oferte");
                    System.out.println("0 - Powrot");
                    op1 = reader.nextByte();

                    switch (op1) {
                        case 1: {
                            System.out.println("Wpisz dane Wycieczki");
                            System.out.print("Miejsce: ");
                            String miejsce = reader.next();
                            System.out.print("Opis Wycieczki: ");
                            String opis = reader1.nextLine();
                            System.out.print("Termin Rozpoczecia(format wpisywania DD/MM/YYYY): ");
                            String ter1 = reader.next();
                            System.out.print("Termin Zakonczenia(format wpisywania DD/MM/YYYY): ");
                            String ter2 = reader.next();
                            System.out.print("Ilosc miejsc ");
                            int miejsca = reader.nextInt();
                            Wycieczki w = new Wycieczki(indw,ter1,ter2,opis,miejsce,miejsca,0);
                            ListaWycieczek.add(w);
                            indw++;
                            break;
                        }
                        case 2: {
                            System.out.println("Dostepne sa oferty: ");
                            for(int i =0; i<ListaWycieczek.size();i++){
                                System.out.println("Oferta nr: "+ (i+1));
                                ListaWycieczek.get(i).Print();
                            }

                            System.out.println("Wpisz nr oferty ktora chcesz modyfikowac");

                            byte numer = reader.nextByte();

                            ListaWycieczek.get(numer-1).Print();

                            System.out.println("Wpisz nowe dane:");

                            System.out.print("Miejsce: ");
                            String miejsce = reader.next();
                            System.out.print("Opis Wycieczki: ");
                            String opis = reader1.nextLine();
                            System.out.print("Termin Rozpoczecia(format wpisywania DD/MM/YYYY): ");
                            String ter1 = reader.next();
                            System.out.print("Termin Zakonczenia(format wpisywania DD/MM/YYYY): ");
                            String ter2 = reader.next();
                            System.out.print("Ilosc miejsc ");
                            int miejsca = reader.nextInt();

                            ListaWycieczek.get(numer-1).kierunek = miejsce;
                            ListaWycieczek.get(numer-1).opis = opis;
                            ListaWycieczek.get(numer-1).termin_rozpoczecia = ter1;
                            ListaWycieczek.get(numer-1).termin_zakonczenia = ter2;
                            ListaWycieczek.get(numer-1).miejsca = miejsca;
                            break;
                        }
                        case 3: {
                            System.out.println("Dostepne sa oferty: ");
                            for(int i =0; i<ListaWycieczek.size();i++){
                                System.out.println("Oferta nr: "+ (i+1));
                                ListaWycieczek.get(i).Print();
                            }
                            System.out.println("Wpisz nr oferty ktora chcesz usunac");
                            byte numer = reader.nextByte();
                            ListaWycieczek.remove(numer-1);
                            break;
                        }
                        default:
                            break;
                    }
                    break;
                }
                case 2: {
                    byte op1;

                    System.out.println("1 - Dodaj Klienta");
                    System.out.println("2 - Usun Klienta");
                    System.out.println("0 - Powrot");
                    op1 = reader.nextByte();

                    switch (op1) {
                        case 1: {
                            System.out.println("Wpisz dane Klienta");
                            System.out.print("Imie: ");
                            String imie = reader.next();
                            System.out.print("Nazwisko: ");
                            String nazwisko = reader.next();
                            Klienci k = new Klienci(indk,imie,nazwisko,0);
                            ListaKlientow.add(k);
                            indk++;
                            break;
                        }
                        case 2:{
                            System.out.println("Dane klientow");
                            System.out.println();
                            for(int i=0; i<ListaKlientow.size();i++){
                                System.out.println("Klient nr: "+ (i+1));
                                ListaKlientow.get(i).Print();
                            }

                            System.out.print("Wpisz nr klienta ktorego chcesz usunac: ");
                            int numer = reader.nextInt();
                            ListaKlientow.remove(numer-1);
                            break;
                        }
                        default: break;
                    }
                }
                case 3: {
                    System.out.println("1 - szukaj wg kierunku");
                    System.out.println("2 - szukaj wg ilosci miejsc");
                    System.out.println("3 - szukaj wg terminu");
                    System.out.println("0 - powrot");
                    byte op1 = reader.nextByte();
                    switch(op1){
                        case 1:{
                            System.out.print("Wpisz gdzie chcesz jechac: ");
                            String kier = reader.next();
                            System.out.println("Pasujace oferty: ");
                            System.out.println();
                            for(int i = 0; i<ListaWycieczek.size(); i++){
                                if(ListaWycieczek.get(i).kierunek.equalsIgnoreCase(kier)){
                                    System.out.println("Oferta nr: "+ (i+1));
                                    ListaWycieczek.get(i).Print();
                                }
                            }
                            break;
                        }
                        case 2:{
                            System.out.print("Wpisz liczbe osob: ");
                            int osob = reader.nextInt();
                            System.out.println("Pasujace oferty: ");
                            System.out.println();
                            for(int i = 0; i<ListaWycieczek.size(); i++){
                                if(ListaWycieczek.get(i).miejsca >= osob){
                                    System.out.println("Oferta nr: "+ (i+1));
                                    ListaWycieczek.get(i).Print();
                                }
                            }
                            break;
                        }
                        case 3:{
                            System.out.println("Wpisz daty a, my znajdziemy dla ciebie wycieczkę, ktora rozpoczyna sie w tym terminie");
                            System.out.print("(Format DD/MM/YYYY)Od: ");
                            String data1 = reader.next();
                            System.out.print("(Format DD/MM/YYYY)Do: ");
                            String data2 = reader.next();

                            String[] data1parts = data1.split("/");
                            int data_od = Integer.parseInt(data1parts[2]+data1parts[1]+data1parts[0]);

                            String[] data2parts = data2.split("/");
                            int data_do = Integer.parseInt(data2parts[2]+data2parts[1]+data2parts[0]);

                            System.out.println();
                            System.out.println("Pasujace Wycieczki: ");
                            System.out.println();
                            for(int i = 0; i<ListaWycieczek.size(); i++){
                                String[] datawycparts = ListaWycieczek.get(i).termin_rozpoczecia.split("/");
                                int datawyc = Integer.parseInt(datawycparts[2]+datawycparts[1]+datawycparts[0]);

                                if(data_do >= datawyc && datawyc >= data_od){
                                    ListaWycieczek.get(i).Print();
                                }
                            }


                            break;
                        }
                        default:break;
                    }
                    break;
                }
                case 4: {

                    byte op1;
                    System.out.println("1 - Rezerwuj");
                    System.out.println("2 - Oplac");
                    System.out.println("3 - Anuluj");
                    op1 = reader.nextByte();
                    switch(op1){
                        case 1:{
                            System.out.print("Imie osoby rezerwujacej: ");
                            String imie = reader.next();
                            System.out.print("Nazwisko osoby rezerwujacej: ");
                            String nazwisko = reader.next();



                            int iter=0;
                            for(int i = 0; i<ListaKlientow.size(); i++){
                                if((ListaKlientow.get(i).imie.equalsIgnoreCase(imie))&& (ListaKlientow.get(i).nazwisko.equalsIgnoreCase(nazwisko))){
                                    iter = i;
                                }
                            }

                            Klienci os_rez = ListaKlientow.get(iter);

                            System.out.println("Dostepne wycieczki: ");
                            System.out.println();

                            for(int i = 0; i<ListaWycieczek.size(); i++){
                                System.out.println("Oferta nr: "+(i+1));
                                ListaWycieczek.get(i).Print();
                            }

                            System.out.print("Ktora wycieczka cie interesuje?(nr): ");
                            int numer = reader.nextInt();

                            System.out.print("Ile osob bierze udzial w wycieczce?: ");
                            int iloscuczest = reader.nextInt();

                            System.out.print("Wpisz ustalony termin platnosci(DD/MM/YYYY): ");
                            String termin = reader.next();

                            Rezerwacje rez = new Rezerwacje(os_rez, ListaWycieczek.get(numer-1), iloscuczest, termin, "Zalozono");

                            ListaRezerwacji.add(rez);

                            ListaWycieczek.get(numer-1).wykup_miejsca += iloscuczest;

                            break;
                        }
                        case 2:{
                            System.out.println("Aktualne Rezerwacje");
                            for(int i=0;i<ListaRezerwacji.size();i++){
                                if(ListaRezerwacji.get(i).status.equals("Zalozono")) {
                                    System.out.println();
                                    System.out.println("Rezerwacja nr: " + (i + 1));
                                    ListaRezerwacji.get(i).Print();
                                }
                            }

                            System.out.print("Ktora rezerwacje chcesz oplacic?(nr): ");
                            int nr = reader.nextInt();

                            ListaRezerwacji.get(nr-1).status = "Oplacono";
                            System.out.println("Oplacono Rezerwacje");

                            break;
                        }
                        case 3:{

                            System.out.println("Aktualne Rezerwacje");
                            for(int i=0;i<ListaRezerwacji.size();i++){
                                if(ListaRezerwacji.get(i).status.equals("Zalozono") || ListaRezerwacji.get(i).status.equals("Oplacono")) {
                                    System.out.println();
                                    System.out.println("Rezerwacja nr: " + (i + 1));
                                    ListaRezerwacji.get(i).Print();
                                }
                            }

                            System.out.print("Ktora rezerwacje chcesz anulowac?(nr): ");
                            int nr = reader.nextInt();

                            ListaRezerwacji.get(nr-1).status = "Anulowano";

                            for(int i=0; i<ListaWycieczek.size(); i++){
                                if(ListaWycieczek.get(i).index == ListaRezerwacji.get(nr-1).wycieczka.index){
                                    ListaWycieczek.get(i).wykup_miejsca -= ListaRezerwacji.get(nr-1).uczestnicy;
                                }
                            }
                            System.out.println("Anulowano Rezerwacje");
                            break;
                        }
                        default: break;
                    }
                    break;
                }
                case 5:{


                    System.out.println("1 - Zestawienie wycieczek wg. ilosci wykupionych miejsc");
                    System.out.println("2 - Zestawienie aktywnosci klientow w okreslonym okresie");
                    System.out.println("0 - Powrot");
                    byte op1 = reader.nextByte();
                    switch (op1){
                        case 1:{
                            List<Wycieczki> Kopia = new ArrayList<>();
                            for(int i = 0; i<ListaWycieczek.size(); i++){
                                Kopia.add(ListaWycieczek.get(i));
                            }
                            Kopia.sort(Comparator.comparing(Wycieczki::getWykup_miejsca).reversed());

                            for (int i=0; i<Kopia.size(); i++){
                                System.out.println();
                                System.out.println("Wycieczka nr: "+ (i+1));
                                Kopia.get(i).Print1();
                            }
                            break;
                        }
                        case 2:{

                            List <Rezerwacje> Aktywnosc = new ArrayList<>();

                            System.out.println("Wyswietlanie aktywnosci klientow w okresie");
                            System.out.print("Od(DD/MM/YYYY): ");
                            String dataod = reader.next();
                            System.out.print("Do(DD/MM/YYYY): ");
                            String datado = reader.next();

                            String[] dataodparts = dataod.split("/");
                            int dataodnum = Integer.parseInt(dataodparts[2]+dataodparts[1]+dataodparts[0]);


                            String[] datadoparts = datado.split("/");
                            int datadonum = Integer.parseInt(datadoparts[2]+datadoparts[1]+datadoparts[0]);

                            for(int i=0; i<ListaRezerwacji.size(); i++){
                                String[] datazest = ListaRezerwacji.get(i).wycieczka.termin_rozpoczecia.split("/");
                                int datazestnum = Integer.parseInt(datazest[2]+datazest[1]+datazest[0]);
                                if(datadonum >= datazestnum && datazestnum >= dataodnum){
                                    Aktywnosc.add(ListaRezerwacji.get(i));
                                }
                            }

                            for(int i=0;i<Aktywnosc.size(); i++){

                                for( int j=0; j<ListaKlientow.size(); j++){
                                    if(Aktywnosc.get(i).os_rezerwujaca.index == ListaKlientow.get(j).index){
                                        ListaKlientow.get(j).wykup_wyciecz++;
                                    }
                                }
                            }


                            List<Klienci> Kopia = new ArrayList<>();
                            for(int i=0; i<ListaKlientow.size(); i++){
                                Kopia.add(ListaKlientow.get(i));
                            }
                            Kopia.sort(Comparator.comparing(Klienci::getWykup_wyciecz).reversed());

                            System.out.println("Najaktywniejsi klienci w zadanym okresie: ");
                            for (int i=0; i<Kopia.size(); i++){
                                System.out.println();
                                System.out.println("Klient nr: "+(i+1));
                                Kopia.get(i).Print();
                            }
                            break;
                        }
                    }

                    break;
                }
                case 6:{
                    System.out.print("Wpisz date do symulacji(DD/MM/YYYY): ");
                    String datasym = reader.next();
                    List<Rezerwacje> Kopia = new ArrayList<>();
                    for(int i=0; i<ListaRezerwacji.size();i++){
                        Kopia.add(ListaRezerwacji.get(i));
                    }

                    for(int i=0; i<Kopia.size(); i++){
                        Kopia.get(i).Przedawnienie(datasym);
                    }
                    System.out.println("Przedawnione rezerwacje: ");

                    for(int i=0; i<Kopia.size(); i++){
                        if (Kopia.get(i).status.equals("Przedawnione")){
                            System.out.println();
                            System.out.println("Rezerwacja nr: "+ (i+1));
                            Kopia.get(i).Print();
                        }
                    }

                    break;
                }
                default:
                    break;
            }
        } while (op != 0);
        try {
            FileWriter fo = new FileWriter("Wycieczki.txt");

            for (int i=0; i<ListaWycieczek.size();i++){
                fo.write(String.valueOf(ListaWycieczek.get(i).index));
                fo.write(System.lineSeparator());
                fo.write(ListaWycieczek.get(i).kierunek);
                fo.write(System.lineSeparator());
                fo.write(ListaWycieczek.get(i).opis);
                fo.write(System.lineSeparator());
                fo.write(ListaWycieczek.get(i).termin_rozpoczecia);
                fo.write(System.lineSeparator());
                fo.write(ListaWycieczek.get(i).termin_zakonczenia);
                fo.write(System.lineSeparator());
                fo.write(String.valueOf(ListaWycieczek.get(i).miejsca));
                fo.write(System.lineSeparator());
                fo.write(String.valueOf(ListaWycieczek.get(i).wykup_miejsca));
                fo.write(System.lineSeparator());
            }
            fo.close();
        } catch (Exception ex) {
            System.out.println("nie udalo sie otworzyc pliku");
        }

        try {
            FileWriter fo = new FileWriter("klienci.txt");

            for (int i=0; i<ListaKlientow.size();i++){
                fo.write(String.valueOf(ListaKlientow.get(i).index));
                fo.write(System.lineSeparator());
                fo.write(ListaKlientow.get(i).imie);
                fo.write(System.lineSeparator());
                fo.write(ListaKlientow.get(i).nazwisko);
                fo.write(System.lineSeparator());
            }
            fo.close();
        }
        catch (Exception ex) {
            System.out.println("nie udalo sie otworzyc pliku");
        }

        try{
            FileWriter fo = new FileWriter("Rezerwacje.txt");
            for( int i=0; i<ListaRezerwacji.size(); i++){
                fo.write(String.valueOf(ListaRezerwacji.get(i).os_rezerwujaca.index));
                fo.write(System.lineSeparator());
                fo.write(String.valueOf(ListaRezerwacji.get(i).wycieczka.index));
                fo.write(System.lineSeparator());
                fo.write(String.valueOf(ListaRezerwacji.get(i).uczestnicy));
                fo.write(System.lineSeparator());
                fo.write(ListaRezerwacji.get(i).termin_platnosci);
                fo.write(System.lineSeparator());
                fo.write(ListaRezerwacji.get(i).status);
                fo.write(System.lineSeparator());
            }
            fo.close();
        }
        catch (Exception ex) {
            System.out.println("nie udalo sie otworzyc pliku");
        }
    }
}