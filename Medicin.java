public abstract class Medicin implements Operer{
    protected Hopital hopital;
    protected String nom;
    protected String prenom;
    protected int age;
    protected String sexe;
    protected Patient[] tabPatients;
    protected int num_actu=0;
    protected static int count_p;

    public Medicin(Hopital hospital, String nom, String prenom, int age, String sexe, int max){
        hopital=hospital;
        this.nom=nom;
        this.prenom=prenom;
        this.age=age;
        this.sexe=sexe;
        tabPatients= new Patient[max];
        count_p++;
        hopital.ajouterMedecin(this);
    }

    public void ajouterPatient(Patient p){
        if(num_actu<tabPatients.length){
           tabPatients[num_actu]=p; 
           num_actu++;
        }
        else {System.out.println("le tableau de ce médicin est déjà rempli au maximum");}
    }

    public void retirerPatient(Patient p){
        if (p == null) {
            System.out.println("Patient null, impossible de retirer.");
            return;
        }
        for(int i=0;i<tabPatients.length;i++){
            if(tabPatients[i] != null && tabPatients[i]==p){
                tabPatients[i]=null;
                if(i!=tabPatients.length-1){
                    for(int r=i; r<tabPatients.length-1;r++){
                        tabPatients[r]=tabPatients[r+1];
                    }
                    tabPatients[tabPatients.length-1]=null;
                }
                num_actu--;
                return;
            }
        }
        System.out.println("Patient non trouvé dans la liste.");
    }

    public String toString(){
        return(prenom+" "+nom+" "+age+" "+sexe+"\n"+hopital.nom);
    }

    public void afficher_medicin(){
        System.out.println(prenom+" "+nom+" "+age+" "+sexe+"\n"+hopital.nom);
    }

    public void afficher_medicin_patients(){
        System.out.println(prenom+" "+nom+" "+age+" "+sexe+"\n"+hopital);
        for(int i=0;i<tabPatients.length;i++){
            if(tabPatients[i]!=null){
             System.out.println(tabPatients[i]);   
            }
        }
    }

    public void afficher_patients(){
        for(int i=0;i<tabPatients.length;i++){
            if(tabPatients[i]!=null){
             System.out.println(tabPatients[i]);   
            }
        }
    }

    private boolean estDansListe(Patient p) {
        for (Patient patient : tabPatients) {
            if (patient.equals(p)) {
                return true;
            }
        }
        return false;
    }
    
    public void rendreVisite(Patient p) {
        if (estDansListe(p)) {
            System.out.println("Le médicin a rendu visite au patient " + p.prenom + " " + p.nom);
        } else {
            System.out.println("Le patient n’est pas de son service.");
        }
    }

    public void soigner(Patient p){
        if (estDansListe(p)) {
            System.out.println("Le médicin a soigné le patient " + p.prenom + " " + p.nom);
        } else {
            System.out.println("Le patient n’est pas de son service.");
        }
    }

    public void anesthesier(Patient p, String typeAnesthesie) { //2 types - general et locale
        System.out.println("Préparation pour anesthésie...");
        //Si le patient est allergique, on utilisera un produit anesthésique A, sinon un produit B.
        if (p.allergique) {
            System.out.println("Utilisation du produit anesthésique A.");
        } else {
            System.out.println("Utilisation du produit anesthésique B.");
        }
        System.out.println("Anesthésie " + typeAnesthesie + " appliquée au patient " + p.prenom + " " + p.nom);
    }

    public void diagnostiquer(Patient p) {
        System.out.println("Le Dr. " + prenom + " " + nom + " commence le diagnostic pour le patient " + p.prenom + " " + p.nom);
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.effectuerDiagnostic(p);
    }

}
