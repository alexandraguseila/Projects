//package GUI;
//
//import Service.ServiceApartament;
//import Service.ServiceHouse;
//
//import javax.swing.*;
//import javax.swing.plaf.ColorUIResource;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class GUI2
//{
//    private ServiceApartament serviceApartament;
//    private ServiceHouse serviceHouse;
//
//    private JFrame frame;
//    //creating the menu bar
//    private JMenuBar menuBar;
//    private JMenu file;
//    private JMenu help;
//    private JMenuItem open;
//    private JMenuItem save;
//    private JLabel helpLabel;
//
//    //creating main options: apartments and houses
//    //private JMenuBar mainMenu;
//    private JMenu apartamente;
//    private JMenu casee;
//    private JMenuItem addApartment;
//    private JMenuItem updateApartment;
//    private JMenuItem deleteApartment;
//    private JMenuItem showApartments;
//    private JMenuItem addHouse;
//    private JMenuItem updateHouse;
//    private JMenuItem deleteHouse;
//    private JMenuItem showHouses;
//
//    public GUI2(ServiceApartament sAp, ServiceHouse sHouse)
//    {
//        this.serviceApartament = sAp;
//        this.serviceHouse = sHouse;
//
//        //creating the frame
//        frame = new JFrame("Firma de inchirieri");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1000,600);               // set size of frame
//        frame.setLocationRelativeTo(null);                 // set location center Desktop
//
//        //creating the menuBar
//        menuBar = new JMenuBar();
//        file = new JMenu("File");
//        help = new JMenu("Help");
//        open = new JMenuItem("Open");
//        save = new JMenuItem("Save as");
//        helpLabel = new JLabel("Pentru orice informatii sunati la 0722128221");
//
//        //Creating the login panel at center and adding components
//
//        //Creating apartament and houses menu
//        //mainMenu = new JMenuBar();
//        apartamente = new JMenu("Apartamente");
//        casee = new JMenu("Case");
//        addApartment = new JMenuItem("Adauga un apartament");
//        updateApartment = new JMenuItem("Modifica un apartament");
//        deleteApartment = new JMenuItem("Sterge un apartament");
//        showApartments = new JMenuItem("Vezi apartamentele");
//        addHouse = new JMenuItem("Adauga o casa");
//        updateHouse = new JMenuItem("Modifica o casa");
//        deleteHouse = new JMenuItem("Sterge o casa");
//        showHouses = new JMenuItem("Vezi casele");
//        //mainMenu.add(apartamente);
//        //mainMenu.add(casee);
//        apartamente.add(addApartment);
//        apartamente.add(updateApartment);
//        apartamente.add(deleteApartment);
//        apartamente.add(showApartments);
//        casee.add(addHouse);
//        casee.add(updateHouse);
//        casee.add(deleteHouse);
//        casee.add(showHouses);
//        apartamente.setBounds(50,110,200,50);
//        casee.setBounds(50,110,200,50);
//        addApartment.setBounds(50,110,200,50);
//        updateApartment.setBounds(50,170,200,50);
//        deleteApartment.setBounds(50,230,200,50);
//        showApartments.setBounds(50,290,200,50);
//        addHouse.setBounds(50,110,200,50);
//        updateHouse.setBounds(50,170,200,50);
//        deleteHouse.setBounds(50,230,200,50);
//        showHouses.setBounds(50,290,200,50);
//
//
//        //frame.getContentPane().add(BorderLayout.CENTER,mainMenu);
//        frame.add(apartamente);
//        frame.add(casee);
//
//        frame.setVisible(true);
//
//    }
//
//    public void frameMenuBar()
//    {
//        menuBar.add(file);
//        menuBar.add(help);
//        file.add(open);
//        file.add(save);
//        help.add(helpLabel);
//        //Adding components to frame
//        frame.getContentPane().add(BorderLayout.NORTH,menuBar);
//        frame.setVisible(true);
//    }
//
//    public void frameLogin(){
//
//        frame.getContentPane().removeAll();
//        frame.repaint();
//
//        errorUsername.setBounds(270,150,150,30);
//        errorUsername.setForeground(ColorUIResource.RED);
//        errorUsername.setVisible(false);
//
//        errorPassword.setBounds(270,210,150,30);
//        errorPassword.setForeground(ColorUIResource.RED);
//        errorPassword.setVisible(false);
//
//        username.setBounds(50,150,200,40);
//        username.setBackground(Color.CYAN);
//        username.setForeground(ColorUIResource.BLACK);
//
//        password.setBounds(50,210,200,40);
//        password.setBackground(Color.CYAN);
//        password.setForeground(ColorUIResource.BLACK);
//
//        checkLogin.setBounds(50,290,80,30);
//        checkLogin.setBackground(Color.WHITE);
//        checkLogin.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == checkLogin) {
//                    frameLibrary();
//                }
////                    else
////                    {
////                        if(!username.getText().equals("Paul Hoban"))
////                            errorUsername.setVisible(true);
////                        if(username.getText().equals("Paul Hoban"))
////                            errorUsername.setVisible(false);
////                        if(!password.getText().equals("1234"))
////                            errorPassword.setVisible(true);
////                        if(password.getText().equals("1234"))
////                            errorPassword.setVisible(false);
////                    }
////                }
//            }
//        });
//
//        frame.add(username);
//        frame.add(password);
//        frame.add(checkLogin);
//        frame.add(errorUsername);
//        frame.add(errorPassword);
//
//        frame.setLayout(null);
//        frame.setVisible(true);
//
//    }
//}