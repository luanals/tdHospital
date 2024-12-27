public abstract class Chirurgien extends Medicin{

    protected final int max = 5;
    
    public  Chirurgien(Hopital hospital, String nom, String prenom, int age, String sexe){
        super(hospital,nom,prenom, age, sexe, 5);
        tabPatients= new Patient[max];
    }

}