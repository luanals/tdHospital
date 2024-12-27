public class Chambre extends Salle {
    private int nb_lits=2;
    protected int nb_lits_disponibles;
    protected int num_actu=0;
    protected static double prix=10;
    protected Patient[] tabChambre;

    public Chambre(){
        nb_lits_disponibles=2;
        tabChambre= new Patient[nb_lits];
    }

    public void ajouterPatient(Patient p){
        if(nb_lits_disponibles>0){
            tabChambre[num_actu]=p; 
           num_actu++;
           nb_lits_disponibles--;
        }
        else {System.out.println("la chambre est déjà rempli au maximum");}
    }

    public void retirerPatient(Patient p){
        for(int i=0;i<tabChambre.length;i++){
            if(tabChambre[i]==p){
                tabChambre[i]=null;
                if(i!=tabChambre.length-1){
                    for(int r=i; r<tabChambre.length-1;r++){
                        tabChambre[r]=tabChambre[r+1];
                    }
                    tabChambre[tabChambre.length-1]=null;
                }
                num_actu--;
            }
        }
        nb_lits_disponibles++;
    }
}
