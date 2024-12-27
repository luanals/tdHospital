public class ChirurgienThoracique extends Chirurgien {
    
    public  ChirurgienThoracique(Hopital hospital, String nom, String prenom, int age, String sexe){
        super(hospital,nom,prenom, age, sexe);
        tabPatients= new Patient[max];
    }

    public void operer(Patient p) throws PasDeChambreException{
        if(!p.chambre_reserve){throw new PasDeChambreException();}
        else{
            hopital.retirerPatientChambre(p);
            hopital.getSalleReveil().retirerPatient(p);
            p.setLocalisation("bloc opératoire");
            hopital.getBlocOperatoire().ajouterPatient(p);
            System.out.println("\nDébut de l'opération chirurgicale...");
            //appeler anesthesie - generale, aprés :
            anesthesier(p, "générale");
            //il ouvre la cage thoracique, traite le problème dans la cage thoracique, puis referme la cage thoracique
            System.out.println("Ouverture de la cage thoracique...");
            System.out.println("Traitement du problème...");
            System.out.println("Fermeture de la cage thoracique...");
            System.out.println("Opération terminée.\n");
            p.setLocalisation("salle de reveil");
            hopital.getBlocOperatoire().retirerPatient(p);
            hopital.getSalleReveil().ajouterPatient(p);
        }
    }
}
