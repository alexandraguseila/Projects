package Repository;

import Domain.Address;
import Domain.Apartament;
import Domain.House;
import Domain.Imobil;

import java.io.*;
import java.util.ArrayList;

public class RepoHouse {
    private ArrayList<House> repo = new ArrayList<>();
    private String filename;

    public RepoHouse(String filename){
        this.filename = filename;
        readFromFile();

    }
    public RepoHouse(ArrayList<House> newRepo, String filename) throws IOException {
        this.repo = newRepo;
        this.filename = filename;
        writeOnFile();
    }

    public void readFromFile(){
        try{
            BufferedReader fileIn=new BufferedReader(new FileReader(this.filename));
            String s;
            while((s=fileIn.readLine())!=null){
                String[] parts =s.split(",");

                int id=Integer.parseInt(parts[0]);
                String tip=parts[1];
                String street = parts[2];                                //strada
                String number = parts[3];                               //numarul blocului sau al casei
                String city = parts[4];                                   //orasul
                String district = parts[5];                                //judet/sector/cartier
                String country = parts[6];
                boolean disponibil;
                if(parts[7].equals("false"))
                    disponibil = false;
                else
                    disponibil = true;
                boolean[] months = new boolean[13];
                for(int i=1;i<=12;i++)
                    if(parts[7 + i].equals("false"))
                        months[i] = false;
                    else
                        months[i] = true;


                House ap = new House(id,disponibil,months,street,number,city,district,country);
                repo.add(ap);
            }
            fileIn.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeOnFile() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename));
        for(int i=0;i<this.repo.size();i++)
        {
            String line=String.valueOf(repo.get(i).getId())+","+repo.get(i).getTip()+","+repo.get(i).getAdresa().getStreet()
                    + "," + repo.get(i).getAdresa().getNumber()
                    + "," + repo.get(i).getAdresa().getCity() + "," + repo.get(i).getAdresa().getDistrict()
                    + "," + repo.get(i).getAdresa().getCountry()
                    + "," + String.valueOf(repo.get(i).isBorrowed() )
                    + "," + String.valueOf(repo.get(i).getMonths()[1] )
                    + "," + String.valueOf(repo.get(i).getMonths()[2] )
                    + "," + String.valueOf(repo.get(i).getMonths()[3] )
                    + "," + String.valueOf(repo.get(i).getMonths()[4] )
                    + "," + String.valueOf(repo.get(i).getMonths()[5] )
                    + "," + String.valueOf(repo.get(i).getMonths()[6] )
                    + "," + String.valueOf(repo.get(i).getMonths()[7] )
                    + "," + String.valueOf(repo.get(i).getMonths()[8] )
                    + "," + String.valueOf(repo.get(i).getMonths()[9] )
                    + "," + String.valueOf(repo.get(i).getMonths()[10] )
                    + "," + String.valueOf(repo.get(i).getMonths()[11] )
                    + "," + String.valueOf(repo.get(i).getMonths()[12] );

            line+="\n";

            writer.write(line);
        }
        writer.close();
    }

    public void addHouse(House p) throws IOException {
        this.repo.add(p);
        writeOnFile();
    }
    public void updateHouse(int id, House newHouse) throws IOException{
        for (House a : repo) {

            if (a.getId() == id) {
                a.setAdresa(newHouse.getAdresa());
                a.setBorrowed(newHouse.isBorrowed());
                a.setMonths(newHouse.getMonths());
            }
        }
        writeOnFile();
    }
    public void deleteHouse(int id) throws IOException {
        for(int i=0; i<repo.size();i++){
            if(repo.get(i).getId()==id)
            {
                this.repo.remove(repo.get(i));
            }
        }
        writeOnFile();
    }
    public ArrayList<House> show(){

        return this.repo;
    }

    public int size(){
        return this.repo.size();
    }

    public House get(int i){
        return repo.get(i);
    }

}

