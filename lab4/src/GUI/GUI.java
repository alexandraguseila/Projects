package GUI;
import Domain.Apartament;
import Domain.House;
import Service.ServiceApartament;
import Service.ServiceHouse;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame{

    private final JFrame frameApp;

    //creating the menu bar
    private JMenuBar menuBar;
    private JMenu files;
    private JMenu help;
    private JMenuItem open;
    private JMenuItem save;
    private JLabel helpLabel;

    //creating login frame
    private final JTextField username=new JTextField("USERNAME: ");
    private final JTextField password=new JTextField("PASSWORD: ");
    private JButton checkLogin=new JButton("LOGIN");
    private final JLabel errorUsername = new JLabel("Invalid username");
    private final JLabel errorPassword = new JLabel("Invalid password");

    //creating main menu
    private final JButton apartamente =new JButton("APARTAMENTE");
    private final JButton casa = new JButton("CASE");
    private final JButton add=new JButton("ADD");
    private final JButton modify=new JButton("MODIFY");
    private final JButton delete=new JButton("DELETE");
    private final JButton show=new JButton("SHOW ALL");

    //back button
    private JButton back=new JButton("BACK");
    private final String file = "D:\\Info\\MAP\\Laborator\\lab 4-5 paul\\Library\\src\\back-button-icons-download-free-vector-icons-noun-project-back-button-png-200_200.png";
    private JButton undo=new JButton();

    private final String fileTextureFrameApp = "D:\\Info\\MAP\\Laborator\\lab4\\casa5.jpg";

    //atribute private de tip service
    private final ServiceApartament serviceApartament;
    private final ServiceHouse serviceHouse;



    public GUI(ServiceApartament serviceApartament, ServiceHouse serviceHouse) throws IOException {

        this.serviceApartament = serviceApartament;
        this.serviceHouse = serviceHouse;

        frameApp=new JFrame("Firma de inchirieri");                  // create frame of app
        frameApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set image textured to frame of app
        frameApp.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(fileTextureFrameApp)))));
        frameApp.pack();

        //creating the menuBar
        menuBar = new JMenuBar();
        files = new JMenu("File");
        help = new JMenu("Help");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save as");
        helpLabel = new JLabel("Pentru orice informatii sunati la 0722128221");
        menuBar.add(files);
        menuBar.add(help);
        files.add(open);
        files.add(save);
        help.add(helpLabel);
        //Adding components to frame
        frameApp.getContentPane().add(BorderLayout.NORTH,menuBar);
        frameApp.setVisible(true);
        frameApp.setJMenuBar(menuBar);

        frameApp.setSize(1000,600);               // set size of frame
        frameApp.setLocationRelativeTo(null);                 // set location center Desktop

        frameApp.setLayout(null);
        frameApp.setVisible(true);
    }

    public void frameLogin(){

        frameApp.getContentPane().removeAll();
        frameApp.repaint();

        errorUsername.setBounds(270,150,150,30);
        errorUsername.setForeground(ColorUIResource.RED);
        errorUsername.setVisible(false);

        errorPassword.setBounds(270,210,150,30);
        errorPassword.setForeground(ColorUIResource.RED);
        errorPassword.setVisible(false);

        username.setBounds(50,150,200,40);
        username.setBackground(Color.WHITE);
        username.setForeground(ColorUIResource.BLACK);
        username.setVisible(true);

        password.setBounds(50,210,200,40);
        password.setBackground(Color.WHITE);
        password.setForeground(ColorUIResource.BLACK);
        password.setVisible(true);

        checkLogin.setBounds(50,290,80,30);
        checkLogin.setBackground(Color.WHITE);
        checkLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == checkLogin) {
                    frameLibrary();
                }
//                    else
//                    {
//                        if(!username.getText().equals("Paul Hoban"))
//                            errorUsername.setVisible(true);
//                        if(username.getText().equals("Paul Hoban"))
//                            errorUsername.setVisible(false);
//                        if(!password.getText().equals("1234"))
//                            errorPassword.setVisible(true);
//                        if(password.getText().equals("1234"))
//                            errorPassword.setVisible(false);
//                    }
//                }
            }
        });

        frameApp.add(username);
        frameApp.add(password);
        frameApp.add(checkLogin);
        frameApp.add(errorUsername);
        frameApp.add(errorPassword);

        frameApp.setLayout(null);
        frameApp.setVisible(true);

    }

    public void frameLibrary(){

        frameApp.getContentPane().removeAll();
        frameApp.repaint();

        apartamente.setBounds(50,110,200,50);
        casa.setBounds(50,170,200,50);

        apartamente.setBackground(Color.WHITE);
        casa.setBackground(Color.WHITE);

        apartamente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == apartamente)
                    frameApartaments();
            }
        });
        casa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == casa)
                    frameCase();
            }
        });

        ImageIcon image = new ImageIcon(file);
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        back = new JButton( newIcon);
        back.setBounds(920,480,50,50);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusable(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    frameLogin();
                }
            }
        });


        frameApp.add(apartamente);
        frameApp.add(casa);
        frameApp.add(back);

        frameApp.setSize(1000,600);
        frameApp.setLayout(null);
        frameApp.setVisible(true);
    }

    public void frameApartaments() {
        frameApp.getContentPane().removeAll();
        frameApp.repaint();

        add.setBounds(50,110,200,50);
        modify.setBounds(50,170,200,50);
        delete.setBounds(50,230,200,50);
        show.setBounds(50,290,200,50);

        add.setBackground(Color.WHITE);
        modify.setBackground(Color.WHITE);
        delete.setBackground(Color.WHITE);
        show.setBackground(Color.WHITE);

        add.setBorderPainted(false);
        modify.setBorderPainted(false);
        delete.setBorderPainted(false);
        show.setBorderPainted(false);

        add.setFocusable(false);
        modify.setFocusable(false);
        delete.setFocusable(false);
        show.setFocusable(false);

        ImageIcon image = new ImageIcon(file);
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        back = new JButton( newIcon);
        back.setBounds(920,480,50,50);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusable(false);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == add){
                    frameAddApartaments();
                }
            }
        });
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == modify){
                    frameModifyApartaments();
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == delete){
                    frameDeleteApartaments();
                }
            }
        });
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == show){
                    frameShowApartaments();
                }
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    frameLibrary();
                }
            }
        });

        frameApp.add(add);
        frameApp.add(modify);
        frameApp.add(delete);
        frameApp.add(show);
        frameApp.add(back);

        frameApp.setLayout(null);
        frameApp.setVisible(true);
    }

    public void frameAddApartaments() {

        frameApp.getContentPane().removeAll();
        frameApp.repaint();

        JLabel addApartament = new JLabel("ADAUGA APARTAMENT");
        addApartament.setFont(new Font("HelveticaNeue-CondensedBlack", Font.BOLD, 30));
        addApartament.setBounds(50,10,350,40);

        JLabel id = new JLabel("Id");
        id.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        id.setBounds(50,60,100,40);

        JLabel street = new JLabel("Strada");
        street.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        street.setBounds(50,110,100,40);

        JLabel no = new JLabel("Nr Bloc");
        no.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        no.setBounds(50,160,100,40);

        JLabel noAp = new JLabel("Nr Ap");
        noAp.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        noAp.setBounds(50,210,100,40);

        JLabel localitate = new JLabel("Localitate");
        localitate.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        localitate.setBounds(50,260,100,40);

        JLabel judet = new JLabel("Judet");
        judet.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        judet.setBounds(50,310,100,40);

        JLabel tara = new JLabel("Tara");
        tara.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        tara.setBounds(50,360,100,40);

        JLabel nrCamere = new JLabel("Nr camere");
        nrCamere.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        nrCamere.setBounds(50,410,100,40);

        JLabel etaj = new JLabel("Etaj");
        etaj.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        etaj.setBounds(50,460,100,40);

        JTextField idTextField=new JTextField();
        idTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        idTextField.setBounds(150,60,60,40);

        JTextField streetTextField=new JTextField();
        streetTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        streetTextField.setBounds(150,110,270,40);

        JTextField noTextField1=new JTextField();
        noTextField1.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        noTextField1.setBounds(150,160,60,40);

        JTextField noApTextField2=new JTextField();
        noApTextField2.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        noApTextField2.setBounds(150,210,60,40);

        JTextField localitateTextField3=new JTextField();
        localitateTextField3.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        localitateTextField3.setBounds(150,260,270,40);

        JTextField judetTextField=new JTextField();
        judetTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        judetTextField.setBounds(150,310,270,40);

        JTextField taraTextField=new JTextField();
        taraTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        taraTextField.setBounds(150,360,270,40);

        JTextField nrCamereTextField=new JTextField();
        nrCamereTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        nrCamereTextField.setBounds(150,410,60,40);

        JTextField etajTextField=new JTextField();
        etajTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        etajTextField.setBounds(150,460,60,40);

        ImageIcon image = new ImageIcon(file);
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        back = new JButton( newIcon);
        back.setBounds(920,480,50,50);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusable(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    frameApartaments();
                }
            }
        });

        frameApp.add(addApartament);
        frameApp.add(id);
        frameApp.add(street);
        frameApp.add(no);
        frameApp.add(noAp);
        frameApp.add(localitate);
        frameApp.add(judet);
        frameApp.add(tara);
        frameApp.add(nrCamere);
        frameApp.add(etaj);

        frameApp.add(idTextField);
        frameApp.add(streetTextField);
        frameApp.add(noTextField1);
        frameApp.add(noApTextField2);
        frameApp.add(localitateTextField3);
        frameApp.add(judetTextField);
        frameApp.add(taraTextField);
        frameApp.add(nrCamereTextField);
        frameApp.add(etajTextField);

        frameApp.add(back);

        frameApp.setLayout(null);
        frameApp.setVisible(true);
    }
    public void frameModifyApartaments() {

        frameApp.getContentPane().removeAll();
        frameApp.repaint();

        JLabel addApartament = new JLabel("MODIFICA APARTAMENT");
        addApartament.setFont(new Font("HelveticaNeue-CondensedBlack", Font.BOLD, 30));
        addApartament.setBounds(50,10,400,40);

        JLabel id = new JLabel("Id Apartament");
        id.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        id.setBounds(50,60,150,40);

        JLabel street = new JLabel("Strada Noua");
        street.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        street.setBounds(50,110,150,40);

        JLabel no = new JLabel("Nr Bloc Nou");
        no.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        no.setBounds(50,160,150,40);

        JLabel noAp = new JLabel("Nr Ap Nou");
        noAp.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        noAp.setBounds(50,210,100,40);

        JLabel localitate = new JLabel("Localitate Noua");
        localitate.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        localitate.setBounds(50,260,150,40);

        JLabel judet = new JLabel("Judet Nou");
        judet.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        judet.setBounds(50,310,150,40);

        JLabel tara = new JLabel("Tara Noua");
        tara.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        tara.setBounds(50,360,150,40);

        JLabel nrCamere = new JLabel("Nr Camere nou");
        nrCamere.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        nrCamere.setBounds(50,410,100,40);

        JLabel etaj = new JLabel("Etaj nou");
        etaj.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        etaj.setBounds(50,460,100,40);

        JTextField idTextField=new JTextField();
        idTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        idTextField.setBounds(200,60,60,40);

        JTextField streetTextField=new JTextField();
        streetTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        streetTextField.setBounds(200,110,270,40);

        JTextField noTextField1=new JTextField();
        noTextField1.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        noTextField1.setBounds(200,160,60,40);

        JTextField noApTextField2=new JTextField();
        noApTextField2.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        noApTextField2.setBounds(200,210,60,40);

        JTextField localitateTextField3=new JTextField();
        localitateTextField3.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        localitateTextField3.setBounds(200,260,270,40);

        JTextField judetTextField=new JTextField();
        judetTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        judetTextField.setBounds(200,310,270,40);

        JTextField taraTextField=new JTextField();
        taraTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        taraTextField.setBounds(200,360,270,40);

        JTextField nrCamereTextField=new JTextField();
        nrCamereTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        nrCamereTextField.setBounds(200,410,60,40);

        JTextField etajTextField=new JTextField();
        etajTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        etajTextField.setBounds(200,460,60,40);

        ImageIcon image = new ImageIcon(file);
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        back = new JButton( newIcon);
        back.setBounds(920,480,50,50);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusable(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    frameApartaments();
                }
            }
        });

        frameApp.add(addApartament);
        frameApp.add(id);
        frameApp.add(street);
        frameApp.add(no);
        frameApp.add(noAp);
        frameApp.add(localitate);
        frameApp.add(judet);
        frameApp.add(tara);
        frameApp.add(nrCamere);
        frameApp.add(etaj);

        frameApp.add(idTextField);
        frameApp.add(streetTextField);
        frameApp.add(noTextField1);
        frameApp.add(noApTextField2);
        frameApp.add(localitateTextField3);
        frameApp.add(judetTextField);
        frameApp.add(taraTextField);
        frameApp.add(nrCamereTextField);
        frameApp.add(etajTextField);

        frameApp.add(back);

        frameApp.setLayout(null);
        frameApp.setVisible(true);
    }
    public void frameDeleteApartaments() {

        frameApp.getContentPane().removeAll();
        frameApp.repaint();

        JLabel deleteApartament = new JLabel("STERGE APARTAMENT");
        deleteApartament.setFont(new Font("HelveticaNeue-CondensedBlack", Font.BOLD, 30));
        deleteApartament.setBounds(50,20,400,40);

        JLabel id = new JLabel("ID");
        id.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN,20));
        id.setBounds(50,150,100,40);

        JLabel info = new JLabel("id, strada, nr bloc, nr ap, loc., jud/sector, tara,nr camere, etaj, disponibilitate(true/false), disponibilitatea pe luni(true/false)");
        info.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN,15));
        info.setBounds(50,250,1500,40);
        info.setForeground(Color.BLACK);
        info.setBackground(Color.WHITE);

        JTextField idTextField=new JTextField();
        idTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        idTextField.setBounds(100,150,60,40);

        JTextArea textArea1 = new JTextArea();
        textArea1.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        textArea1.setBounds(50,300,1500,40);


        ImageIcon image = new ImageIcon(file);
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        back = new JButton( newIcon);
        back.setBounds(920,480,50,50);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusable(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    frameApartaments();
                }
            }
        });

        frameApp.add(deleteApartament);
        frameApp.add(id);
        frameApp.add(idTextField);
        frameApp.add(textArea1);
        frameApp.add(info);

        frameApp.add(back);

        frameApp.setLayout(null);
        frameApp.setVisible(true);

    }
    public void frameShowApartaments() {
        frameApp.getContentPane().removeAll();                                   // remove all components from frame
        frameApp.repaint();

        class TableWithScroll{

            public TableWithScroll(Object[] apartaments,int countRow){

                String[] col = {"Id","Tip","Strada","Nr Bloc","Nr Ap","Localitate","Judet","Tara","Nr Camere","Etaj"};  // colums of tabel
                Object[][] dates = new Object[countRow][10];
                for(int i = 0; i < countRow; i++) {
                        dates[i][0] = ((Apartament) apartaments[i]).getId();
                        dates[i][2] = ((Apartament) apartaments[i]).getAdresa().getStreet();
                        dates[i][1] = ((Apartament) apartaments[i]).getTip();
                        dates[i][3] = ((Apartament) apartaments[i]).getAdresa().getNumber();
                        dates[i][4] = ((Apartament) apartaments[i]).getAdresa().getApNumber();
                        dates[i][5] = ((Apartament) apartaments[i]).getAdresa().getCity();
                        dates[i][6] = ((Apartament) apartaments[i]).getAdresa().getDistrict();
                        dates[i][7] = ((Apartament) apartaments[i]).getAdresa().getCountry();
                        dates[i][8] = ((Apartament) apartaments[i]).getNrCamere();
                        dates[i][9] = ((Apartament) apartaments[i]).getEtaj();

               }                                              // dates of tabel

                JTable table = new JTable(dates,col);                           // crete tabel
                JPanel panel = new JPanel();                                    // create panel where will be the table

                table.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20)); // set font of table
                table.setBackground(new Color(255, 255, 255, 255));        // set background of table, WHITE
                table.setGridColor(Color.black);                           // set grid color of tabel
                table.setForeground(Color.black);                          // set foreground, color of dates from tabel

                for(int i = 0; i < countRow; i++) {
                    table.setRowHeight(i, 40);                          // set row Height 40 pixels
                }
                TableColumnModel columns = table.getColumnModel();           // sets columns a preferred width
                columns.getColumn(0).setPreferredWidth(60);
                columns.getColumn(1).setPreferredWidth(270);
                columns.getColumn(2).setPreferredWidth(270);
                columns.getColumn(3).setPreferredWidth(60);
                columns.getColumn(4).setPreferredWidth(60);
                columns.getColumn(5).setPreferredWidth(270);
                columns.getColumn(6).setPreferredWidth(270);
                columns.getColumn(7).setPreferredWidth(270);
                columns.getColumn(8).setPreferredWidth(60);
                columns.getColumn(9).setPreferredWidth(60);


                JTableHeader header = table.getTableHeader();
                header.setPreferredSize(new Dimension(800,40));         // set size to header of tabel
                header.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));// set font to header

                JScrollPane scrollPane = new JScrollPane(table);                    // create scroll pane with the table

                int height = 40*(countRow+1);                                   // set size to scroll pane
                if(height>420) height = 420;
                int width = 900;
                scrollPane.setPreferredSize(new Dimension(width,height));

                scrollPane.setBackground(new Color(255, 255, 255, 255)); // set background to scroll pane

                panel.add(scrollPane);                                         // add scroll pane with table in panel
                panel.setBounds(10,50,frameApp.getWidth()-10,420); // set bounds
                panel.setBackground(new Color(213, 134, 145, 0));   // set background to panel, TRANSPARENT

                frameApp.add(panel);    // add to frame, panel with the scroll pane and table
            }
        }                                               // class Table with Scroll

        Object[] objects=new Object[serviceApartament.showApartaments().size()+1];             // creates objects to add in table

        int countRow=serviceApartament.showApartaments().size();
        for(int i=0;i<serviceApartament.showApartaments().size();i++)
        //{
            objects[i]=serviceApartament.showApartaments().get(i);
            //countRow+=serviceApartament.showApartaments().get(i).getAuthors().size();
        //}
        new TableWithScroll(objects,countRow);

        ImageIcon image = new ImageIcon(file);
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        back = new JButton( newIcon);
        back.setBounds(920,480,50,50);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusable(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    frameApartaments();
                }
            }
        });

        frameApp.add(back);

        frameApp.setLayout(null);                                                // set frame visible
        frameApp.setVisible(true);
    }


    public void frameCase(){
        frameApp.getContentPane().removeAll();
        frameApp.repaint();

        add.setBounds(50,110,200,50);
        modify.setBounds(50,170,200,50);
        delete.setBounds(50,230,200,50);
        show.setBounds(50,290,200,50);

        add.setBackground(Color.WHITE);
        modify.setBackground(Color.WHITE);
        delete.setBackground(Color.WHITE);
        show.setBackground(Color.WHITE);

        add.setBorderPainted(false);
        modify.setBorderPainted(false);
        delete.setBorderPainted(false);
        show.setBorderPainted(false);

        add.setFocusable(false);
        modify.setFocusable(false);
        delete.setFocusable(false);
        show.setFocusable(false);

        ImageIcon image = new ImageIcon(file);
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        back = new JButton( newIcon);
        back.setBounds(920,480,50,50);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusable(false);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == add){
                    frameAddHouses();
                }
            }
        });
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == modify){
                    frameModifyHouses();
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == delete){
                    try {
                        frameDeleteHouses();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == show){
                    frameShowHouses();
                }
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    frameLibrary();
                }
            }
        });

        frameApp.add(add);
        frameApp.add(modify);
        frameApp.add(delete);
        frameApp.add(show);
        frameApp.add(back);

        frameApp.setLayout(null);
        frameApp.setVisible(true);

    }

    public void frameAddHouses(){
        frameApp.getContentPane().removeAll();
        frameApp.repaint();

        JLabel addHouse = new JLabel("ADAUGA CASA");
        addHouse.setFont(new Font("HelveticaNeue-CondensedBlack", Font.BOLD, 30));
        addHouse.setBounds(50,10,350,40);

        JLabel id = new JLabel("Id");
        id.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        id.setBounds(50,60,100,40);

        JLabel street = new JLabel("Strada");
        street.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        street.setBounds(50,110,100,40);

        JLabel no = new JLabel("Numar");
        no.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        no.setBounds(50,160,100,40);

        JLabel localitate = new JLabel("Localitate");
        localitate.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        localitate.setBounds(50,260,100,40);

        JLabel judet = new JLabel("Judet");
        judet.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        judet.setBounds(50,310,100,40);

        JLabel tara = new JLabel("Tara");
        tara.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        tara.setBounds(50,360,100,40);

        JTextField idTextField=new JTextField();
        idTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        idTextField.setBounds(150,60,60,40);

        JTextField streetTextField=new JTextField();
        streetTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        streetTextField.setBounds(150,110,270,40);

        JTextField noTextField1=new JTextField();
        noTextField1.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        noTextField1.setBounds(150,160,60,40);

        JTextField localitateTextField3=new JTextField();
        localitateTextField3.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        localitateTextField3.setBounds(150,260,270,40);

        JTextField judetTextField=new JTextField();
        judetTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        judetTextField.setBounds(150,310,270,40);

        JTextField taraTextField=new JTextField();
        taraTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        taraTextField.setBounds(150,360,270,40);

        ImageIcon image = new ImageIcon(file);
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        back = new JButton( newIcon);
        back.setBounds(920,480,50,50);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusable(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    frameCase();
                }
            }
        });

        frameApp.add(addHouse);
        frameApp.add(id);
        frameApp.add(street);
        frameApp.add(no);
        frameApp.add(localitate);
        frameApp.add(judet);
        frameApp.add(tara);

        frameApp.add(idTextField);
        frameApp.add(streetTextField);
        frameApp.add(noTextField1);
        frameApp.add(localitateTextField3);
        frameApp.add(judetTextField);
        frameApp.add(taraTextField);

        frameApp.add(back);

        frameApp.setLayout(null);
        frameApp.setVisible(true);
    }
    public void frameModifyHouses(){
        frameApp.getContentPane().removeAll();
        frameApp.repaint();

        JLabel addHouse = new JLabel("MODIFICA CASA");
        addHouse.setFont(new Font("HelveticaNeue-CondensedBlack", Font.BOLD, 30));
        addHouse.setBounds(50,10,400,40);

        JLabel id = new JLabel("Id Casa");
        id.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        id.setBounds(50,60,150,40);

        JLabel street = new JLabel("Strada Noua");
        street.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        street.setBounds(50,110,150,40);

        JLabel no = new JLabel("Nr Nou");
        no.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        no.setBounds(50,160,150,40);

        JLabel localitate = new JLabel("Localitate Noua");
        localitate.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        localitate.setBounds(50,260,150,40);

        JLabel judet = new JLabel("Judet Nou");
        judet.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        judet.setBounds(50,310,150,40);

        JLabel tara = new JLabel("Tara Noua");
        tara.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        tara.setBounds(50,360,150,40);

        JTextField idTextField=new JTextField();
        idTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        idTextField.setBounds(200,60,60,40);

        JTextField streetTextField=new JTextField();
        streetTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        streetTextField.setBounds(200,110,270,40);

        JTextField noTextField1=new JTextField();
        noTextField1.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        noTextField1.setBounds(200,160,60,40);

        JTextField localitateTextField3=new JTextField();
        localitateTextField3.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        localitateTextField3.setBounds(200,260,270,40);

        JTextField judetTextField=new JTextField();
        judetTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        judetTextField.setBounds(200,310,270,40);

        JTextField taraTextField=new JTextField();
        taraTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        taraTextField.setBounds(200,360,270,40);

        ImageIcon image = new ImageIcon(file);
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        back = new JButton( newIcon);
        back.setBounds(920,480,50,50);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusable(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    frameCase();
                }
            }
        });

        frameApp.add(addHouse);
        frameApp.add(id);
        frameApp.add(street);
        frameApp.add(no);
        frameApp.add(localitate);
        frameApp.add(judet);
        frameApp.add(tara);

        frameApp.add(idTextField);
        frameApp.add(streetTextField);
        frameApp.add(noTextField1);
        frameApp.add(localitateTextField3);
        frameApp.add(judetTextField);
        frameApp.add(taraTextField);

        frameApp.add(back);

        frameApp.setLayout(null);
        frameApp.setVisible(true);
    }
    public void frameDeleteHouses() throws IOException {
        frameApp.getContentPane().removeAll();
        frameApp.repaint();

        JLabel deleteHouse = new JLabel("STERGE CASA");
        deleteHouse.setFont(new Font("HelveticaNeue-CondensedBlack", Font.BOLD, 30));
        deleteHouse.setBounds(50,20,400,40);

        JLabel id = new JLabel("ID");
        id.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN,20));
        id.setBounds(50,150,100,40);

        JLabel info = new JLabel("id, strada, nr, loc., jud/sector, tara, disponibilitate(true/false), disponibilitatea pe luni(true/false)");
        info.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN,15));
        info.setBounds(50,250,1500,40);
        info.setForeground(Color.BLACK);
        info.setBackground(Color.WHITE);

        JTextField idTextField=new JTextField();
        idTextField.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        idTextField.setBounds(100,150,60,40);

        JTextArea textArea1 = new JTextArea();
        textArea1.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));
        textArea1.setBounds(50,300,1500,40);


        ImageIcon image = new ImageIcon(file);
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        back = new JButton( newIcon);
        back.setBounds(920,480,50,50);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusable(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    frameCase();
                }
            }
        });

        frameApp.add(deleteHouse);
        frameApp.add(id);
        frameApp.add(idTextField);
        frameApp.add(textArea1);
        frameApp.add(info);

        frameApp.add(back);

        String linie = info.getText();
        String []parti = linie.split(",");
        serviceHouse.deleteHouse(Integer.parseInt(parti[0]));

        frameApp.setLayout(null);
        frameApp.setVisible(true);

    }
    public void frameShowHouses(){
        frameApp.getContentPane().removeAll();                                   // remove all components from frame
        frameApp.repaint();

        class TableWithScroll{

            public TableWithScroll(Object[] houses,int countRow){

                String[] col = {"Id","Tip","Strada","Nr","Localitate","Judet","Tara","Inchiriata"};  // colums of tabel
                Object[][] dates = new Object[countRow][8];
                for(int i = 0; i < countRow; i++) {
                    dates[i][0] = ((House) houses[i]).getId();
                    dates[i][2] = ((House) houses[i]).getAdresa().getStreet();
                    dates[i][1] = ((House) houses[i]).getTip();
                    dates[i][3] = ((House) houses[i]).getAdresa().getNumber();
                    dates[i][4] = ((House) houses[i]).getAdresa().getCity();
                    dates[i][5] = ((House) houses[i]).getAdresa().getDistrict();
                    dates[i][6] = ((House) houses[i]).getAdresa().getCountry();
                    dates[i][7] = ((House) houses[i]).isBorrowed();

                }                                              // dates of tabel

                JTable table = new JTable(dates,col);                           // crete tabel
                JPanel panel = new JPanel();                                    // create panel where will be the table

                table.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20)); // set font of table
                table.setBackground(new Color(255, 255, 255, 255));        // set background of table, WHITE
                table.setGridColor(Color.black);                           // set grid color of tabel
                table.setForeground(Color.black);                          // set foreground, color of dates from tabel

                for(int i = 0; i < countRow; i++) {
                    table.setRowHeight(i, 40);                          // set row Height 40 pixels
                }
                TableColumnModel columns = table.getColumnModel();           // sets columns a preferred width
                columns.getColumn(0).setPreferredWidth(60);
                columns.getColumn(1).setPreferredWidth(270);
                columns.getColumn(2).setPreferredWidth(270);
                columns.getColumn(3).setPreferredWidth(60);
                columns.getColumn(4).setPreferredWidth(270);
                columns.getColumn(5).setPreferredWidth(270);
                columns.getColumn(6).setPreferredWidth(270);
                columns.getColumn(7).setPreferredWidth(270);



                JTableHeader header = table.getTableHeader();
                header.setPreferredSize(new Dimension(800,40));         // set size to header of tabel
                header.setFont(new Font("HelveticaNeue-CondensedBlack", Font.PLAIN, 20));// set font to header

                JScrollPane scrollPane = new JScrollPane(table);                    // create scroll pane with the table

                int height = 40*(countRow+1);                                   // set size to scroll pane
                if(height>420) height = 420;
                int width = 900;
                scrollPane.setPreferredSize(new Dimension(width,height));

                scrollPane.setBackground(new Color(255, 255, 255, 255)); // set background to scroll pane

                panel.add(scrollPane);                                         // add scroll pane with table in panel
                panel.setBounds(10,50,frameApp.getWidth()-10,420); // set bounds
                panel.setBackground(new Color(213, 134, 145, 0));   // set background to panel, TRANSPARENT

                frameApp.add(panel);    // add to frame, panel with the scroll pane and table
            }
        }                                               // class Table with Scroll

        Object[] objects=new Object[serviceHouse.showHouses().size()+1];             // creates objects to add in table

        int countRow=serviceHouse.showHouses().size();
        for(int i=0;i<serviceHouse.showHouses().size();i++)
            objects[i]=serviceHouse.showHouses().get(i);

        new TableWithScroll(objects,countRow);

        ImageIcon image = new ImageIcon(file);
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        back = new JButton( newIcon);
        back.setBounds(920,480,50,50);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusable(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    frameCase();
                }
            }
        });

        frameApp.add(back);

        frameApp.setLayout(null);                                                // set frame visible
        frameApp.setVisible(true);
    }




    public void runApp(){
        frameLogin();
    }
}
