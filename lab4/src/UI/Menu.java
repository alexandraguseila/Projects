package UI;

public class Menu {
    public static int menu() {
        System.out.println();
        System.out.println("1.Apartaments");
        System.out.println("2.Houses");
        System.out.println("3.Show");
        System.out.println("4.Show rent by mounth");
        System.out.println("5.Show rent all year");
        System.out.println("6.Show available");
        System.out.println("7.Show available short method");
        System.out.println("0.END PROGRAM");
        return UI.readInt("Your option:");
    }

    public static int menuCRUD() {
        System.out.println();
        System.out.println("1.ADD");
        System.out.println("2.UPDATE");
        System.out.println("3.DELETE");
        System.out.println("4.SHOW");
        System.out.println("0.Back");
        return UI.readInt("Your option:");
    }
}
