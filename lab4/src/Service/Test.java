package Service;

import Domain.Apartament;
import Domain.House;
import Repository.RepoApartament;
import Repository.RepoHouse;

import java.util.ArrayList;

public class Test {
    public static void testService(){
        RepoApartament repoAp = new RepoApartament("D:\\Info\\MAP\\Laborator\\Firma de inchirieri\\src\\Files\\apartamente.txt");
        RepoHouse repoH = new RepoHouse("D:\\Info\\MAP\\Laborator\\Firma de inchirieri\\src\\Files\\case.txt");
        ServiceApartament ap = new ServiceApartament(repoAp);
        ServiceHouse h = new ServiceHouse(repoH);
        ArrayList<Apartament> aprts = ap.showApartaments();
        ArrayList<House> houses = h.showHouses();
        for (Apartament aprt : aprts) System.out.println(aprt.toString());
        //for(int i=0;i<h.size();i++)
        //    System.out.println(houses.get(i).toString());
    }
}
