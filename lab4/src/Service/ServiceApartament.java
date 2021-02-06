package Service;

import Domain.Apartament;
import Repository.RepoApartament;

import java.io.IOException;
import java.util.ArrayList;

public class ServiceApartament {
    private RepoApartament apartaments;

    public ServiceApartament(RepoApartament repo)
    {
        this.apartaments = repo;
    }
    public void addApartament(Apartament a) throws IOException {
        apartaments.addApartament(a);
    }
    public void deleteApartament(int id) throws IOException {
        apartaments.deleteApartament(id);
    }
    public void updateApartament(int id, Apartament newAp) throws IOException {
        apartaments.updateApartament(id, newAp);
    }
    public ArrayList<Apartament> showApartaments(){
        return apartaments.show();
    }
    public int size(){
        return apartaments.size();
    }
}
