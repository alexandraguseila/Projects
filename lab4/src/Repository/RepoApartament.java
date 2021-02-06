package Repository;
import Domain.Apartament;

import java.io.*;
import java.util.ArrayList;

public class RepoApartament extends ArrayList<Apartament> {
    private static ArrayList<Apartament> repo = new ArrayList<>();
    private String filename;

    public RepoApartament(String filename){
        this.filename = filename;
        readFromFile();

    }
    public RepoApartament(ArrayList<Apartament> newRepo, String filename) throws IOException {
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
                String apartamentNumber = parts[4];                     //nr apartament
                String city = parts[5];                                   //orasul
                String district = parts[6];                                //judet/sector/cartier
                String country = parts[7];
                int nrCamere = Integer.parseInt(parts[8]);
                int etaj = Integer.parseInt(parts[9]);
                boolean disponibil;
                if(parts[10].equals("false"))
                    disponibil = false;
                else
                    disponibil = true;
                boolean[] months = new boolean[13];
                for(int i=1;i<=12;i++)
                    if(parts[10 + i].equals("false"))
                        months[i] = false;
                    else
                        months[i] = true;


                Apartament ap = new Apartament(id, street,number,apartamentNumber,city,district,country, nrCamere, etaj,disponibil,months);
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
                    + "," + repo.get(i).getAdresa().getNumber() + "," + repo.get(i).getAdresa().getApNumber()
                    + "," + repo.get(i).getAdresa().getCity() + "," + repo.get(i).getAdresa().getDistrict()
                    + "," + repo.get(i).getAdresa().getCountry() + "," + repo.get(i).getEtaj() + "," + repo.get(i).getNrCamere()
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

    public void addApartament(Apartament p) throws IOException {
        this.repo.add(p);
        writeOnFile();
    }
    public void updateApartament(int id, Apartament newAp) throws IOException{
        for (Apartament a : repo) {

            if (a.getId() == id) {
                a.setAdresa(newAp.getAdresa());
                a.setEtaj(newAp.getEtaj());
                a.setNrCamere(newAp.getNrCamere());
                a.setBorrowed(newAp.isBorrowed());
                a.setMonths(newAp.getMonths());
            }
        }
        writeOnFile();
    }
    public void deleteApartament(int id) throws IOException {
        for(int i=0; i<repo.size();i++){
            if(repo.get(i).getId()==id)
            {
                this.repo.remove(repo.get(i));
            }
        }
        writeOnFile();
    }
    public ArrayList<Apartament> show(){

        return repo;
    }
    public int size(){
        return repo.size();
    }

    public Apartament get(int i){
        return repo.get(i);
    }

}
