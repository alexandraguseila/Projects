package Domain;


public class Imobil extends Object {

    private int id;                                         //fiecare imobil are un identificator propriu
    //private Adress adresa;                                //un imobil are adresa si tip, adica apartament
    String tip;                                             //sau casa
    private boolean borrowed;                               //atribut care exprima disponibilitatea imobilului
    protected boolean[] months = new boolean[13];             //pentru fiecare luna o sa stiu daca imobilul este
                                                            //inchiriat sau disponibil

    public Imobil(){                                        //constructor implicit fara parametrii
        tip = null;
        borrowed = false;
        for(int i=1;i<=12;i++)
            months[i] = false;
    }

    Imobil(Imobil im) {
        id = im.id;
        tip = im.tip;
        borrowed = im.borrowed;
        for(int i=1;i<=12;i++)
            months[i] = im.months[i];

    }

    public Imobil(int identificator, String type, boolean borrowed, boolean[] months)
    {                                               //constructor cu parametrii
        tip = type;
        id = identificator;
        this.borrowed = borrowed;
        for(int i=1;i<=12;i++)
            this.months[i] = months[i];

    }

    public int getId(){
        return id;
    }

    void setId(int identificator){
        id = identificator;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public boolean[] getMonths() {
        return months;
    }

    public void setMonths(boolean[] months) {
        this.months = months;
    }
}
