public class Interniste extends Medicin {
    protected final int max = 1;

    public  Interniste(Hopital hospital, String nom, String prenom, int age, String sexe){
        super(hospital,nom,prenom, age, sexe, 1);
        tabPatients= new Patient[max];
    }
    
    public void operer(Patient p){
        System.out.println("un interniste ne peut pas opérer\n");
    };
}
