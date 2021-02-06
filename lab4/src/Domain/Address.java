package Domain;

public class Address {

    private String street = null;                   //strada
    private String number = null;                   //numarul blocului sau al casei
    private String apartamentNumber;                //nr apartament
    private String city = null;                     //orasul
    private String district = null;                 //judet/sector/cartier
    private String country = null;                  //tara

    public Address(){}                               //constructor implicit
                                                    //constructor telescopic
    public Address(String s, String no, String c, String d, String co) {
        street = s;
        number = no;
        city = c;
        district = d;
        country = co;
    }

                                                    //constructor cu parametrii
    public Address(String s, String no, String apNo, String c, String d, String co) {
        street = s;
        number = no;
        apartamentNumber = apNo;
        city = c;
        district = d;
        country = co;
    }

    public Address(Address a){
        street = a.street;
        number = a.number;
        apartamentNumber = a.apartamentNumber;
        city = a.city;
        district = a.district;
        country = a.country;
    }
                                                    //getter
    public String getStreet(){return street;}

    public String getNumber(){return number;}

    public String getApNumber() { return apartamentNumber;}

    public String getCity() { return city; }

    public String getDistrict() { return district; }

    public String getCountry() { return country; }
                                                    //setter

    public void setStreet(String street) { this.street = street; }

    public void setNumber(String number) { this.number = number; }

    public void setApartamentNumber(String apartamentNumber) { this.apartamentNumber = apartamentNumber; }

    public void setCity(String city) { this.city = city; }

    public void setDistrict(String district) { this.district = district; }

    public void setCountry(String country) { this.country = country; }

    @Override                                                   //metoda de afisare a unei adrese
    public String toString(){
        return "strada " + street + ", numarul " + number + ", apartament " + apartamentNumber +
        ", localitatea " + city + ", judetul/sectorul " + district + ", tara " + country; }

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Address))
            return false;
        Address adresa = (Address)obj;
        return (street.equals(adresa.street))
                && (number.equals(adresa.number))
                && (apartamentNumber.equals(adresa.apartamentNumber))
                && (city.equals(adresa.city))
                && (district.equals(adresa.district))
                && (country.equals(adresa.country)); }

    public int compareTo(Object obj){                       //compar doua adrese
        if (!(obj instanceof Address))
            return -1;                                      //returnez -1 daca obiectul dat ca parametru nu e adresa
        Address adresa = (Address)obj;
        if (street.equals(adresa.street)
                && (number.equals(adresa.number))
                && (apartamentNumber.equals(adresa.apartamentNumber))
                && (city.equals(adresa.city))
                && (district.equals(adresa.district))
                && (country.equals(adresa.country)))
            return 0;                                       //returnez  daca e aceeasi adresa
        else return 1; }                                    //returnez daca parametrul dat e adresa dar nu coincide
                                                            //cu adresa curenta
}
