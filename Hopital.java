public class Hopital {
    protected String nom;
    protected Medicin[] tabMedicins;
    protected int nbMax;
    protected Chambre[] tabChambres;
    protected SalleTravail salleTravail;
    protected SalleReveil salleReveil;
    protected BlocOperatoire blocOperatoire;
    protected int num_actu=0;

    public Hopital(String nom, int nbMax) {
        this.nom = nom;
        this.tabMedicins = new Medicin[50];
        this.tabChambres = new Chambre[nbMax];
        for (int i = 0; i < nbMax; i++) {
            tabChambres[i] = new Chambre();
        }
        this.salleTravail = new SalleTravail();
        this.salleReveil = new SalleReveil();
        this.blocOperatoire = new BlocOperatoire();
    }

    public void ajouterMedecin(Medicin m) {
        if(num_actu<tabMedicins.length){
            tabMedicins[num_actu]=m; 
            num_actu++;
        }
        else{System.out.println("Aucune place disponible pour ce médecin.");}
    }

    public boolean assignerPatientChambre(Patient p) {
        if (p == null) {
            System.out.println("Patient null, impossible d'assigner une chambre.");
            return false;
        }
        for (int i = 0; i < tabChambres.length; i++) {
            Chambre chambre = tabChambres[i];
            if (chambre != null && chambre.nb_lits_disponibles>0) {
                chambre.ajouterPatient(p);
                p.localisation="chambre "+i;
                System.out.println("Chambre désignée pour le patient " + p);
                return true;
            }
        }
        System.out.println("Aucune chambre disponible pour le patient " + p);
        return false;
    }

    public boolean retirerPatientChambre(Patient p) {
        for (int i = 0; i < tabChambres.length; i++) {
            Chambre chambre = tabChambres[i];
            if (chambre != null && chambre.nb_lits_disponibles<2) {
                chambre.retirerPatient(p);;
                p.localisation="aucune";
                return true;
            }
        }
        System.out.println("Patient "+p+" pas trouvé en chambre");
        return false;
    }

    public int getNombreDePatientsEnChambres() {
        int total = 0;
        for (Chambre chambre : tabChambres) {
            if (chambre != null) {
                total += chambre.num_actu;
            }
        }
        return total;
    }
    
    public void afficher_medicins(){
        for(int i=0;i<num_actu;i++){
            if(tabMedicins[i]!=null){
             System.out.println(tabMedicins[i]);   
            }
        }
    }

    public void afficher_patients_avec_chambres(){
        System.out.println("\nPatients avec chambres:");
        if(tabMedicins!=null){
           for(Medicin m:tabMedicins){
                if(m!=null){
                    if(m.tabPatients!=null){
                        for(Patient p:m.tabPatients){
                            if(p!=null){
                                if(p.chambre_reserve){
                                    System.out.println(p);
                                } 
                            }
                        }
                    }   
                }  
            } 
        }
    }

    public void afficher_patients_en_chambres(){
        System.out.println("\nPatients dans les chambres:");
        boolean hasPatients = false;

        for (Chambre chambre : tabChambres) {
            if (chambre != null) {
                for (Patient p : chambre.tabChambre) {
                    if (p != null){
                        System.out.println(p);
                        hasPatients = true;  
                    }
                    
                }
            }
        }
        if (!hasPatients) {
            System.out.println("Aucun patient dans les chambres.");
        }
    }

    public void afficher_patients_salles(){
        //afficher la liste des patients présents au bloc opératoire, en salle de réveil et en salle de travail.
        System.out.println("\nPatients présents en salle de travail:");
        salleTravail.afficherPatients();

        System.out.println("\nPatients présents en salle de réveil:");
        salleReveil.afficherPatients();

        System.out.println("\nPatients présents au bloc opératoire:");
        blocOperatoire.afficherPatients();
    }

    public void afficher_nb_patients(){
        System.out.println("\nnb de patients dans l'hopital : "+Patient.count_p);
        System.out.println("nb de patients en chambres : "+getNombreDePatientsEnChambres());
        System.out.println("nb de patients en salle de réveil : "+salleReveil.getNombreDePatients());
        System.out.println("nb de patients en salle de travail : "+salleTravail.getNombreDePatients());
        System.out.println("nb de patients au bloc opératoire : "+blocOperatoire.getNombreDePatients());
    }

    public void afficher_nb_medicins(){System.out.println("nb de medicins dans l'hopital : "+Medicin.count_p);}
    
    public SalleReveil getSalleReveil() {
        return salleReveil;
    }

    public SalleTravail getSalleTravail() {
        return salleTravail;
    }

    public BlocOperatoire getBlocOperatoire() {
        return blocOperatoire;
    }
}
