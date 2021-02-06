package Domain;

public class House extends Imobil implements Comparable{
    private Address adresa;

    public House(){this.tip = "casa";}
    public House(int id,boolean borrowed,boolean[] months, Address adresa){
        super(id,"casa",borrowed,months);
        this.adresa = adresa;
    }

    public House(int id,boolean borrowed,boolean[] months, String street, String no, String city, String district, String country){
        super(id, "casa",borrowed,months);
        Address a = new Address(street,no, city, district,country);
        this.adresa = a;
    }

    public Address getAdresa() {
        return adresa;
    }

    public void setAdresa(Address adresa) {
        this.adresa = adresa;
    }

    @Override
    public int compareTo(Object o) {
        if(!( o instanceof House))
            return -1;
        House c = (House) o;
        if(c.getId() == ((House) o).getId()
                && c.getAdresa().equals(((House) o).getAdresa())
                && c.getTip().equals(((House) o).getTip()))
            return 0;
        return  1;
    }

    public String toString()
    {
        return  "\t id " + this.getTip() + " = " + this.getId() + "\n\t adresa: " + this.getAdresa() + "\n\t ";
    }
}
