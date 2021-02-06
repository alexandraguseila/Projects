package Repository;

import Domain.Imobil;

import java.util.ArrayList;

public interface IRepo {
    public abstract void readFromFile();
    public abstract  void writeOnFile();
    public void addObject(Imobil o);
    public void deleteObject(int id);
    public void updateObject(int id, Imobil o);
    ArrayList<Object> getAll();
    public boolean existObject(int id);
    public int findObjectId(int id);
    public int count();
}
