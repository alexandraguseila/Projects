package UI;

import Repository.RepoApartament;
import Repository.RepoHouse;
import Service.ServiceApartament;
import Service.ServiceHouse;

import java.io.IOException;

public class Test {
    public static void testUI() throws IOException {
        RepoApartament repoAp = new RepoApartament("D:\\Info\\MAP\\Laborator\\lab4\\src\\Files\\apartamente.txt");
        RepoHouse repoH = new RepoHouse("D:\\Info\\MAP\\Laborator\\lab4\\src\\Files\\case.txt");
        ServiceApartament serviceAp = new ServiceApartament(repoAp);
        ServiceHouse serviceH = new ServiceHouse(repoH);
        UI ui = new UI(serviceAp, serviceH);
        //ui.addApartament();-merge
        //ui.deleteApartament();-merge
        //ui.addHouse();-merge
        //ui.deleteHouse();
        //ui.updateApartament();-merge
       //ui.updateHouse();-merge
    }
}
