package Domain;

public class Apartament extends Imobil implements Comparable  {
    private Address adresa;                                      //un apartament are atribute proprii
    private int nrCamere;
    private int etaj;


    public Apartament(){
        this.setTip("apartament");
        nrCamere = 0;
        etaj = 0;
        }

    public Apartament(Apartament ap)
     {
         super(ap);
         adresa = ap.adresa;
         nrCamere = ap.nrCamere;
         etaj = ap.etaj;
     }

    public Apartament(int id,
                      String street,
                      String number,
                      String apNumber,
                      String district,
                      String city,
                      String country,
                      int et,
                      int nr,
                      boolean borrowed,
                      boolean[] months)
    {
        super(id, "apartament",borrowed,months);
        adresa = new Address(street, number, apNumber,city, district, country);
        nrCamere = nr;
        etaj = et;
    }

    public Address getAdresa(){return adresa;}
    public int getNrCamere(){return nrCamere;}
    public int getEtaj(){return etaj;}
    public void setAdresa(Address a){adresa = a;}
    public void setNrCamere(int nr){nrCamere = nr;}
    public void setEtaj(int et){etaj = et;}

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Apartament))                   //daca obiectul dat ca parametru nu este Apartament
            return -1;                                  //returnez valoarea -1
        Apartament im = (Apartament)o;
        if(im.getId()==((Apartament) o).getId()  && im.getTip().equals(((Apartament) o).getTip())
        && im.getAdresa().equals(((Apartament) o).getAdresa()) && im.getEtaj() == ((Apartament)o).getEtaj())
            return 0;                                   //daca sunt egale returnez 0
        return 1;                                       //daca ambele obiecte sunt de tip Apartament dar nu sunt egale
                                                        //retunez 1
    }

    @Override
    public String toString(){
        if(!this.isBorrowed())
        {
            return  "\t id " + this.getTip() + " = " + this.getId() + "\n\t adresa: " + this.getAdresa() + "\n\t " +
                    "etajul " + this.getEtaj() + ", " + this.getNrCamere() + " camere"
                    + '\n' + "disponibil oricand";}
        else
        {
            for(int i=1;i<=12;i++)
                if(!this.months[i])
                {
                    return  "\t id " + this.getTip() + " = " + this.getId() + "\n\t adresa: " + this.getAdresa() + "\n\t " +
                            "etajul " + this.getEtaj() + ", " + this.getNrCamere() + " camere"
                            + '\n' + "disponibil" ;
                }
                else {
                    return "\t id " + this.getTip() + " = " + this.getId() + "\n\t adresa: " + this.getAdresa() + "\n\t " +
                            "etajul " + this.getEtaj() + ", " + this.getNrCamere() + " camere"
                            + '\n' + "disponibil in lunile" + String.valueOf(i);
                }
        }
        return null;
    }
}
