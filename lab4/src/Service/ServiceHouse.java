package Service;

import Domain.House;
import Repository.RepoHouse;

import java.io.IOException;
import java.util.ArrayList;

public class ServiceHouse {
    private RepoHouse houses;

    public ServiceHouse(RepoHouse repo)
    {
        this.houses = repo;
    }
    public void addHouse(House a) throws IOException {
        houses.addHouse(a);
    }
    public void deleteHouse(int id) throws IOException {
        houses.deleteHouse(id);
    }
    public void updateHouse(int id, House newHouse) throws IOException {
        houses.updateHouse(id, newHouse);
    }
    public ArrayList<House> showHouses(){
        return houses.show();
    }
    public int size(){
        return houses.size();
    }
}
