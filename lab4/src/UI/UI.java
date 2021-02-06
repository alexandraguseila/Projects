package UI;

import Domain.Address;
import Domain.Apartament;
import Domain.House;
import Service.ServiceApartament;
import Service.ServiceHouse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static UI.Menu.menu;

public class UI {
    static ServiceApartament aparts;
    static ServiceHouse houses;

    public UI(ServiceApartament a, ServiceHouse h){
        aparts = a;
        houses = h;
    }


    public static int readInt(String sir) {
        try { System.out.print(sir);
            Scanner s=new Scanner(System.in);
            int i=s.nextInt();
            return i;
        }
        catch(Exception e) {
            System.out.println("Ai gresit, mai incearca!!");
            return readInt(sir);
        }
    }

    public void addApartament() throws IOException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Introduceti datele apartamentului");
        System.out.println("id, strada, nr bloc, nr apartament, loc., jud/sector, tara, nr camere, etaj,\n"
        + "disponibilitate(true/false), disponibilitatea pe luni(true/false)");
        String linie = scn.nextLine();
        String[] parts = linie.split(",");

        int id = Integer.parseInt(parts[0]);
        String street = parts[1];
        String no = parts[2];
        String noAp = parts[3];
        String city = parts[4];
        String district = parts[5];
        String country = parts[6];
        int nrCamere = Integer.parseInt(parts[7]);
        int etaj = Integer.parseInt(parts[8]);

        boolean disponibil;
        if(parts[9].equals("false"))
            disponibil = false;
        else
            disponibil = true;
        boolean[] months = new boolean[13];
        for(int i=1;i<=12;i++)
            if(parts[9 + i].equals("false"))
                months[i] = false;
            else
                months[i] = true;

        Apartament ap = new Apartament(id, street, no, noAp, city, district, country, nrCamere, etaj, disponibil, months);
        aparts.addApartament(ap);
    }

    public void addHouse() throws IOException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Introduceti datele casei");
        System.out.println("id, strada, nr, loc., jud/sector, tara, disponibilitate(true/false), disponibilitatea pe luni(true/false)");
        String linie = scn.nextLine();
        String[] parts = linie.split(",");

        int id = Integer.parseInt(parts[0]);
        String street = parts[1];
        String no = parts[2];
        String city = parts[3];
        String district = parts[4];
        String country = parts[5];

        boolean disponibil;
        if(parts[6].equals("false"))
            disponibil = false;
        else
            disponibil = true;
        boolean[] months = new boolean[13];
        for(int i=1;i<=12;i++)
            if(parts[6 + i].equals("false"))
                months[i] = false;
            else
                months[i] = true;


        House h = new House(id,disponibil,months,street,no,city,district,country);
        houses.addHouse(h);
    }

    public void deleteApartament() throws IOException {
        System.out.println("Introduceti id-ul apartamentului de sters: ");
        Scanner scn = new Scanner(System.in);
        int id = scn.nextInt();
        aparts.deleteApartament(id);

    }

    public void deleteHouse() throws IOException {
        System.out.println("Introduceti id-ul casei de sters: ");
        Scanner scn = new Scanner(System.in);
        int id = scn.nextInt();
        houses.deleteHouse(id);
    }

    public void updateApartament() throws IOException {
        Scanner scn = new Scanner(System.in);
        //System.out.println("Dati id-ul apartamentului pe care il actualizati: ");
        //int id = scn.nextInt();
        System.out.println("Introduceti id-ul apartamentului de actualizat si noile date:");
        System.out.println("id, strada, nr bloc, nr apartament, loc., jud/sector, tara, nr camere, etaj,\n"
                + "disponibilitate(true/false), disponibilitatea pe luni(true/false)");
        String linie = scn.nextLine();
        String[] parts = linie.split(",");

        int id2 = Integer.parseInt(parts[0]);
        String street = parts[1];
        String no = parts[2];
        String noAp = parts[3];
        String city = parts[4];
        String district = parts[5];
        String country = parts[6];
        int nrCamere = Integer.parseInt(parts[7]);
        int etaj = Integer.parseInt(parts[8]);

        boolean disponibil;
        if(parts[9].equals("false"))
            disponibil = false;
        else
            disponibil = true;
        boolean[] months = new boolean[13];
        for(int i=1;i<=12;i++)
            if(parts[9 + i].equals("false"))
                months[i] = false;
            else
                months[i] = true;

        Apartament ap = new Apartament(id2, street, no, noAp, city, district, country, nrCamere, etaj, disponibil, months);
        aparts.updateApartament(id2, ap);
    }

    public void updateHouse() throws IOException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Dati id-ul casei pe care o actualizati si noile date: ");
        System.out.println("id, strada, nr, loc., jud/sector, tara, disponibilitate(true/false), disponibilitatea pe luni(true/false)");
        String linie = scn.nextLine();
        String[] parts = linie.split(",");

        int id2 = Integer.parseInt(parts[0]);
        String street = parts[1];
        String no = parts[2];
        String city = parts[3];
        String district = parts[4];
        String country = parts[5];

        boolean disponibil;
        if(parts[6].equals("false"))
            disponibil = false;
        else
            disponibil = true;
        boolean[] months = new boolean[13];
        for(int i=1;i<=12;i++)
            if(parts[6 + i].equals("false"))
                months[i] = false;
            else
                months[i] = true;


        House h = new House(id2,disponibil,months,street,no,city,district,country);
        houses.updateHouse(id2, h);
    }
    //------------------------------------------------------------------------------------------------------


    public static void linii(String character, int nr)
    {
        for(int i=0;i<nr;i++)
            System.out.print(character);
    }

    public static void capTabelAll(){
        linii("=",99);
        System.out.println("\n|Id|Tip       |Adresa                                                             |Disponibilitate|\n");
        linii("=",99);
    }

    public static void capTabelAllIchiriate(){
        linii("=",93);
        System.out.println("\n|Id|Tip       |Adresa                                                             |Inchiriat|\n");
        linii("=",93);
    }

    public static void capTabelApartamente(){
        System.out.println("\n              |Strada          |Bloc|Ap  |Judet    |Localitate|Tara   |Camere|Etaj|               |\n");
        linii("=",99);
    }

    public static void capTabelApartamenteIchiriate(){
        System.out.println("\n              |Strada          |Bloc|Ap  |Judet    |Localitate|Tara   |Camere|Etaj|         |\n");
        linii("=",93);
    }

    public static void capTabelCase(){
        linii("=",87);
        System.out.println("\n|Id|Tip       |Adresa                                                 |Disponibilitate|\n");
        linii("=",87);
        System.out.println("\n              |Strada          |Bloc|Ap  |Judet    |Localitate|Tara   |Disponibilitate|\n");
        linii("=",87);
    }

    public static void capTabelCaseInchiriate(){
        linii("=",81);
        System.out.println("\n|Id|Tip       |Adresa                                                 |Inchiriat|\n");
        linii("=",81);
        System.out.println("\n              |Strada          |Bloc|Ap  |Judet    |Localitate|Tara   |         |\n");
        linii("=",81);
    }

    public static void tableAll(){
        System.out.println('\n');
        capTabelAllIchiriate();
        capTabelApartamenteIchiriate();
        ArrayList<Apartament> ap = new ArrayList<>(aparts.showApartaments());
        ArrayList<House> hs = new ArrayList<>(houses.showHouses());

        System.out.println();
        for (Apartament apartament : ap)
        {
            Address add = apartament.getAdresa();
            System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-9s|\n",
                    apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                    add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                    apartament.isBorrowed());
        }
        for(House h: hs )
        {
            Address add = h.getAdresa();
            System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-9s|\n",
                    h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                    add.getDistrict(), add.getCity(), add.getCountry(),"","",h.isBorrowed());
        }
        linii("=",93);
        System.out.println('\n');

    }

    public static void showApartaments(){
        System.out.println('\n');
        capTabelAllIchiriate();
        capTabelApartamenteIchiriate();
        ArrayList<Apartament> ap = new ArrayList<>(aparts.showApartaments());

        System.out.println();
        for (Apartament apartament : ap)
        {
            Address add = apartament.getAdresa();
            System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-9s|\n",
                    apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                    add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                    apartament.isBorrowed());
        }
        linii("=",93);
        System.out.println('\n');

    }

    public static void showHouses(){
        System.out.println('\n');
        capTabelCaseInchiriate();
        ArrayList<House> hs = new ArrayList<>(houses.showHouses());
        System.out.println();
        for(House h: hs )
        {
            Address add = h.getAdresa();
            System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%-9s|\n",
                    h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                    add.getDistrict(), add.getCity(), add.getCountry(),h.isBorrowed());
        }
        linii("=",81);
        System.out.println('\n');
    }

    public static void filterByMounth() {
            String mounth;
            int m ;
            ArrayList<Apartament> ap = new ArrayList<>(aparts.showApartaments());
            ArrayList<House> h = new ArrayList<>(houses.showHouses());
            System.out.print("dati luna dupa care filtrati imobilele:");
            Scanner s=new Scanner(System.in);
            mounth=s.nextLine();
            switch(mounth)
            {
                case "ianuarie": m = 1;break;
                case "februarie": m = 2;break;
                case "martie": m = 3;break;
                case "aprilie": m = 4;break;
                case "mai": m = 5;break;
                case "iunie": m = 6;break;
                case "iulie": m = 7;break;
                case "august": m = 8;break;
                case "septembrie": m = 9;break;
                case "octombrie": m = 10;break;
                case "noiembrie": m = 11;break;
                case "decembrie": m = 12;break;
                default:
                    throw new IllegalStateException("Dati o luna existenta, nu  " + mounth);
            }
            System.out.println("\nimobilele inchiriate in luna: "+mounth);
            linii("=",65);
            System.out.println("\n|Id|Tip       |Strada          |Bloc|Ap  |Localitate|Camere|Etaj|\n");
            linii("=",65);
            System.out.println();

            for(int i=0;i<ap.size(); i++) {
                boolean disp = ap.get(i).getMonths()[m];
                if(disp)
                    System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-10s|%6s|%4s|\n",
                            ap.get(i).getId(), ap.get(i).getTip(),ap.get(i).getAdresa().getStreet(),ap.get(i).getAdresa().getNumber(),ap.get(i).getAdresa().getApNumber(),
                            ap.get(i).getAdresa().getCity(), ap.get(i).getNrCamere(), ap.get(i).getEtaj());
            }
            for(int i=0;i<h.size(); i++) {
                boolean disp = h.get(i).getMonths()[m];
                if(disp)
                    System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-10s|%6s|%4s|\n",
                            h.get(i).getId(), h.get(i).getTip(),h.get(i).getAdresa().getStreet(),h.get(i).getAdresa().getNumber(),null,
                            h.get(i).getAdresa().getCity(), null, null);
            }
            linii("=",65);
    }



    public static void rentPerYear() {
        ArrayList<Apartament> ap = new ArrayList<>(aparts.showApartaments());
        ArrayList<House> hs = new ArrayList<>(houses.showHouses());

        System.out.print("Imobile inchiriate tot anul:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        boolean rent = true;
        boolean rent2 = true;
        for (Apartament apartament : ap)
        {
            for(int i=1;i<=12;i++)
                if(!apartament.getMonths()[i])
                {
                    rent = false;
                    break;}
            if(rent)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                    apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                    add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                    !apartament.isBorrowed());
            }
        }
        for(House h: hs )
        {
            for(int i=1;i<=12;i++)
                if(!h.getMonths()[i])
                {
                    rent2 = false;
                    break;}
            if(rent2)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                    h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                    add.getDistrict(), add.getCity(), add.getCountry(),"","",!h.isBorrowed());
            }
        }
        if(rent && rent2)
            System.out.println("Nu exista imobile inchiriate intregul an!");
        linii("=",99);
        System.out.println('\n');
    }


    public static void availableShort() {
        ArrayList<Apartament> ap = new ArrayList<>(aparts.showApartaments());
        ArrayList<House> hs = new ArrayList<>(houses.showHouses());

        System.out.print("Imobile disponibile:\n");
        linii("=",149);
        System.out.println("\n|Id|Tip       |Adresa                                                             |disponibilitate                                                  |\n");
        linii("=",149);
        System.out.println("\n              |Strada          |Bloc|Ap  |Judet    |Localitate|Tara   |Camere|Etaj|                                                                 |\n");
        linii("=",149);
        System.out.println();

        for(Apartament apartament:ap)
        {
            int []availableMonths = new int[13];
            int noAvailable = 0;
            for(int i=1;i<=12;i++)
                if(!apartament.getMonths()[i])
                {
                    noAvailable++;
                    availableMonths[noAvailable] = i;
                }
            if(noAvailable!=0)
            {String[] months = new String[noAvailable-1];
            for(int i=1;i<+noAvailable;i++)
                switch (availableMonths[i]) {
                    case 1 -> months[i-1] = "ian";
                    case 2 -> months[i-1] = "feb";
                    case 3 -> months[i-1] = "mar";
                    case 4 -> months[i-1] = "apr";
                    case 5 -> months[i-1] = "mai";
                    case 6 -> months[i-1] = "iun";
                    case 7 -> months[i-1] = "iul";
                    case 8 -> months[i-1] = "aug";
                    case 9 -> months[i-1] = "sep";
                    case 10 -> months[i-1] = "oct";
                    case 11 -> months[i-1] = "noi";
                    case 12 -> months[i-1] = "dec";
                }
            Address add = apartament.getAdresa();
            System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-65s|\n",
                    apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                    add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                    Arrays.toString(months));}
        }

        for(House house:hs)
        {
            int []availableMonths = new int[13];
            int noAvailable = 0;
            for(int i=1;i<=12;i++)
                if(!house.getMonths()[i])
                {
                    noAvailable++;
                    availableMonths[noAvailable] = i;
                }
            if(noAvailable!=0)
            {String[] months = new String[noAvailable-1];
                for(int i=1;i<+noAvailable;i++)
                    switch (availableMonths[i]) {
                        case 1 -> months[i-1] = "ian";
                        case 2 -> months[i-1] = "feb";
                        case 3 -> months[i-1] = "mar";
                        case 4 -> months[i-1] = "apr";
                        case 5 -> months[i-1] = "mai";
                        case 6 -> months[i-1] = "iun";
                        case 7 -> months[i-1] = "iul";
                        case 8 -> months[i-1] = "aug";
                        case 9 -> months[i-1] = "sep";
                        case 10 -> months[i-1] = "oct";
                        case 11 -> months[i-1] = "noi";
                        case 12 -> months[i-1] = "dec";
                    }
            Address add = house.getAdresa();
            System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-65s|\n",
                    house.getId(), house.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                    add.getDistrict(), add.getCity(), add.getCountry(), null,null,
                    Arrays.toString(months));}
        }

        linii("=",149);
        System.out.println();

    }

    public static void available() {
        ArrayList<Apartament> ap = new ArrayList<>(aparts.showApartaments());
        ArrayList<House> hs = new ArrayList<>(houses.showHouses());

        System.out.print("Imobile disponibile tot anul:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        for (Apartament apartament : ap)
        {
            boolean disp = !(apartament.isBorrowed());
            if(disp)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                        !apartament.isBorrowed());
            }
        }
        for(House h: hs )
        {
            boolean disp = !(h.isBorrowed());
            if(disp)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(),"","",!h.isBorrowed());
            }
        }
        //if(!disp && !disp2)
        //    System.out.println("Nu exista imobile disponibile intregul an!");
        linii("=",99);
        System.out.println('\n');

        System.out.print("Imobile disponibile in luna ianuarie:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        for (Apartament apartament : ap)
        {
            boolean disp = !(apartament.getMonths()[1]);
            if(disp)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                        "true");
            }
        }
        for(House h: hs )
        {
            boolean disp = !(h.getMonths()[1]);
            if(disp)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(),"","","true");
            }
        }
        //if(!disp && !disp2)
        //    System.out.println("Nu exista imobile disponibile intregul an!");
        linii("=",99);
        System.out.println('\n');

        System.out.print("Imobile disponibile in luna februarie:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        for (Apartament apartament : ap)
        {
            boolean disp = !(apartament.getMonths()[2]);
            if(disp)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                        "true");
            }
        }
        for(House h: hs )
        {
            boolean disp = !(h.getMonths()[2]);
            if(disp)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(),"","","true");
            }
        }
        //if(!disp && !disp2)
        //    System.out.println("Nu exista imobile disponibile intregul an!");
        linii("=",99);
        System.out.println('\n');


        System.out.print("Imobile disponibile in luna martie:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        for (Apartament apartament : ap)
        {
            boolean disp = !(apartament.getMonths()[3]);
            if(disp)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                        "true");
            }
        }
        for(House h: hs )
        {
            boolean disp = !(h.getMonths()[3]);
            if(disp)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(),"","","true");
            }
        }
        //if(!disp && !disp2)
        //    System.out.println("Nu exista imobile disponibile intregul an!");
        linii("=",99);
        System.out.println('\n');

        System.out.print("Imobile disponibile in luna aprilie:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        for (Apartament apartament : ap)
        {
            boolean disp = !(apartament.getMonths()[4]);
            if(disp)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                        "true");
            }
        }
        for(House h: hs )
        {
            boolean disp = !(h.getMonths()[4]);
            if(disp)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(),"","","true");
            }
        }
        //if(!disp && !disp2)
        //    System.out.println("Nu exista imobile disponibile intregul an!");
        linii("=",99);
        System.out.println('\n');

        System.out.print("Imobile disponibile in luna mai:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        for (Apartament apartament : ap)
        {
            boolean disp = !(apartament.getMonths()[5]);
            if(disp)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                        "true");
            }
        }
        for(House h: hs )
        {
            boolean disp = !(h.getMonths()[5]);
            if(disp)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(),"","","true");
            }
        }
        //if(!disp && !disp2)
        //    System.out.println("Nu exista imobile disponibile intregul an!");
        linii("=",99);
        System.out.println('\n');

        System.out.print("Imobile disponibile in luna iunie:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        for (Apartament apartament : ap)
        {
            boolean disp = !(apartament.getMonths()[6]);
            if(disp)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                        "true");
            }
        }
        for(House h: hs )
        {
            boolean disp = !(h.getMonths()[6]);
            if(disp)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(),"","","true");
            }
        }
        //if(!disp && !disp2)
        //    System.out.println("Nu exista imobile disponibile intregul an!");
        linii("=",99);
        System.out.println('\n');

        System.out.print("Imobile disponibile in luna iulie:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        for (Apartament apartament : ap)
        {
            boolean disp = !(apartament.getMonths()[7]);
            if(disp)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                        "true");
            }
        }
        for(House h: hs )
        {
            boolean disp = !(h.getMonths()[7]);
            if(disp)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(),"","","true");
            }
        }
        //if(!disp && !disp2)
        //    System.out.println("Nu exista imobile disponibile intregul an!");
        linii("=",99);
        System.out.println('\n');

        System.out.print("Imobile disponibile in luna august:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        for (Apartament apartament : ap)
        {
            boolean disp = !(apartament.getMonths()[8]);
            if(disp)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                        "true");
            }
        }
        for(House h: hs )
        {
            boolean disp = !(h.getMonths()[8]);
            if(disp)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(),"","","true");
            }
        }
        //if(!disp && !disp2)
        //    System.out.println("Nu exista imobile disponibile intregul an!");
        linii("=",99);
        System.out.println('\n');

        System.out.print("Imobile disponibile in luna septembrie:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        for (Apartament apartament : ap)
        {
            boolean disp = !(apartament.getMonths()[9]);
            if(disp)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                        "true");
            }
        }
        for(House h: hs )
        {
            boolean disp = !(h.getMonths()[9]);
            if(disp)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(),"","","true");
            }
        }
        //if(!disp && !disp2)
        //    System.out.println("Nu exista imobile disponibile intregul an!");
        linii("=",99);
        System.out.println('\n');

        System.out.print("Imobile disponibile in luna octombrie:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        for (Apartament apartament : ap)
        {
            boolean disp = !(apartament.getMonths()[10]);
            if(disp)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                        "true");
            }
        }
        for(House h: hs )
        {
            boolean disp = !(h.getMonths()[10]);
            if(disp)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(),"","","true");
            }
        }
        //if(!disp && !disp2)
        //    System.out.println("Nu exista imobile disponibile intregul an!");
        linii("=",99);
        System.out.println('\n');

        System.out.print("Imobile disponibile in luna noiembrie:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        for (Apartament apartament : ap)
        {
            boolean disp = !(apartament.getMonths()[11]);
            if(disp)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                        "true");
            }
        }
        for(House h: hs )
        {
            boolean disp = !(h.getMonths()[11]);
            if(disp)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(),"","","true");
            }
        }
        //if(!disp && !disp2)
        //    System.out.println("Nu exista imobile disponibile intregul an!");
        linii("=",99);
        System.out.println('\n');

        System.out.print("Imobile disponibile in luna decembrie:\n");
        capTabelAll();
        capTabelApartamente();
        System.out.println();

        for (Apartament apartament : ap)
        {
            boolean disp = !(apartament.getMonths()[12]);
            if(disp)
            {
                Address add = apartament.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%4s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        apartament.getId(), apartament.getTip(), add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(), apartament.getNrCamere(), apartament.getEtaj(),
                        "true");
            }
        }
        for(House h: hs )
        {
            boolean disp = !(h.getMonths()[12]);
            if(disp)
            {
                Address add = h.getAdresa();
                System.out.format("|%2d|%-10s|%-16s|%4s|%2s|%-9s|%-10s|%-7s|%6s|%4s|%-15s|\n",
                        h.getId(),h.getTip(),add.getStreet(), add.getNumber(), add.getApNumber(),
                        add.getDistrict(), add.getCity(), add.getCountry(),"","","true");
            }
        }
        //if(!disp && !disp2)
        //    System.out.println("Nu exista imobile disponibile intregul an!");
        linii("=",99);
        System.out.println('\n');
    }
}

