package Application;
import Domain.Apartament;
import Domain.House;

import GUI.GUI;
//import GUI.GUI2;

import Repository.RepoApartament;
import Repository.RepoHouse;
import Service.ServiceApartament;
import Service.ServiceHouse;
import Service.Test;
import UI.*;

import java.io.IOException;

import static UI.Menu.menu;
import static UI.Menu.menuCRUD;


public class Main {
    public static void main(String []args) throws IOException {
        runApp();

    }

    public static void runApp() throws IOException {
        RepoApartament repoAp = new RepoApartament("D:\\Info\\MAP\\Laborator\\lab4\\src\\Files\\apartamente.txt");
        RepoHouse repoH = new RepoHouse("D:\\Info\\MAP\\Laborator\\lab4\\src\\Files\\case.txt");
        ServiceApartament servAp = new ServiceApartament(repoAp);
        ServiceHouse servH = new ServiceHouse(repoH);

        GUI GUI=new GUI(servAp, servH);
        GUI.runApp();

        //GUI2 gui = new GUI2(servAp,servH);


        UI ui = new UI(servAp,servH);
        int opt=menu();
        while(opt!=0) {
            switch(opt) {
                case 1: {
                    int opt2 = menuCRUD();
                    while(opt2!=0)
                    {
                        switch(opt2)
                        {
                            case 1:ui.addApartament();break;
                            case 2:ui.updateApartament();break;
                            case 3:ui.deleteApartament();break;
                            case 4:ui.showApartaments();break;
                            default:System.out.println("Wrong option :( Try again!");
                        }
                        opt2 = menuCRUD();
                    }
                }
                break;
                case 2: {
                    int opt2 = menuCRUD();
                    while(opt2!=0)
                    {
                        switch(opt2)
                        {
                            case 1:ui.addHouse();break;
                            case 2:ui.updateHouse();break;
                            case 3:ui.deleteHouse();break;
                            case 4:ui.showHouses();break;
                            default:System.out.println("Wrong option :( Try again!");
                        }
                        opt2 = menuCRUD();
                    }
                }
                break;
                case 3: UI.tableAll();
                    break;

                case 4: UI.filterByMounth();
                    break;
                case 5:UI.rentPerYear();
                    break;
                case 6:UI.available();
                    break;
                case 7:UI.availableShort();
                    break;

                default:System.out.println("Wrong option :( Try again!");
            }
            opt=menu();
        }
        System.out.println(" Program terminat :) ");
    }


}
