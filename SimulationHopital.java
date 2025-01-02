public class SimulationHopital {
    
    public static void main(String[] args){

        Hopital hopital = new Hopital("Hôpital Central", 6);

        ChirurgienOrthopedique co1 = new ChirurgienOrthopedique(hopital, "Santos", "Dilson", 46, "masc");
        Obstetricien o1 = new Obstetricien(hopital, "nom", "prenom", 60, "fem");
        Interniste drHouse = new Interniste(hopital, "House", "Gregory", 50, "masc");

        //adicionar em Hopital.tabPatients

        Patient p1=null;
        try {
            p1 = new Patient("Lopes Santiago", "Luana", false, "260602",true,"22/02/2024","22/03/2024",true,hopital);
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        }
        Patient p2=null;
        try {
            p2 = new Patient("Lopes Santiago", "Marina", false, "260602",true,"22/02/2024","23/02/2024",true, hopital);
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        }
        Patient p3=null;
        try {
            p3 = new Patient("Barbosa Sousa", "Thais", true, null,true,"22/02/2024","22/02/2025",true, hopital);
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        }
        Patient p4=null;
        try {
            p4 = new Patient("Santiago", "Luana", false, "260602",true,"22/02/2024","25/06/2024", true, hopital);
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        }

        FemmeEnceinte f1=null;
        try {
            f1 = new FemmeEnceinte("Enceinte", "Femme", false, "090989",true,"22/02/2024","22/02/2024", false, hopital, null, false);
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        }
        FemmeEnceinte f2=null;
        try {
            f2 = new FemmeEnceinte("NOM", "Prenom", false, "010191",true,"24/02/2024",null, true, hopital, "300205", true);
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        }
        FemmeEnceinte f3=null;
        try {
            f3 = new FemmeEnceinte("Ence", "Fe", true, "060799",true,"22/02/2024","22/02/2024", true, hopital, null, false);
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        }
        FemmeEnceinte f4=null;
        try {
            f4 = new FemmeEnceinte("Enceinte1", "Femme", false, "090989",true,"22/02/2024","22/02/2024", true, hopital, null, false);
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        }

        Patient patient=null;
        try {
            patient = new Patient("Doe", "John", false, null, true, "27/12/2024", null, true, hopital);
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        }

        co1.ajouterPatient(p1);
        co1.ajouterPatient(p2);
        co1.ajouterPatient(p3);
        co1.ajouterPatient(p4);
        //m1.ajouterPatient(f1);
        //m1.ajouterPatient(f2);
        co1.afficher_patients();


        //o1.ajouterPatient(p1);
        //o1.ajouterPatient(p2);
        //o1.ajouterPatient(p3);
        o1.ajouterPatient(f1);
        o1.ajouterPatient(f2);
        o1.ajouterPatient(f3);
        o1.afficher_patients();

        System.out.println("\nnb de patients:"+Patient.nb_patients()); //on doive compter les patients sans medicins? (on croit que dans l'appli les patients ne seront crées que pour etre ajoutés à un médecin?)
        System.out.println("nb de Femmes Enceintes:"+FemmeEnceinte.nb_femmesEnceints()); //on doive compter les femmes sans chambres?
        
        
        f1.montant_regler();
        f2.montant_regler();
        p3.montant_regler();
        p4.montant_regler();
        
        co1.afficher_medicin();
        System.out.print("\n");
        co1.afficher_medicin_patients();
        System.out.print("\n");
        co1.rendreVisite(p3);

        try {
            o1.operer(f1);
        } catch (PasDeChambreException e) {
            System.out.println(e);
        }
        f1.setChambre(true);
        System.out.println(f1.localisation);
        try {
            o1.operer(f1);
        } catch (PasDeChambreException e) {
            System.out.println(e);
        }
        
        try {
            co1.operer(p1);
        } catch (PasDeChambreException e) {
            System.out.println(e);
        }        

        //hopital.afficher_medicins();
        //hopital.afficher_nb_medicins();
        hopital.afficher_nb_patients();
        hopital.afficher_patients_avec_chambres();
        hopital.afficher_patients_en_chambres();
        hopital.afficher_patients_salles();

        
        drHouse.ajouterPatient(patient);

        drHouse.diagnostiquer(patient);

    }
}
