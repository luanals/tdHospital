public class Obstetricien extends Medicin {
    
    protected final int max = 4;

    public Obstetricien(Hopital hospital, String nom, String prenom, int age, String sexe){
        super(hospital,nom,prenom, age, sexe, 4);
        tabPatients= new Patient[max];
    }

    @Override
    public void ajouterPatient(Patient p){
        if(p instanceof FemmeEnceinte){
            FemmeEnceinte femmeEnceinte = (FemmeEnceinte) p;
            if(num_actu<max){
                if(femmeEnceinte.chambre_reserve){
                    tabPatients[num_actu]=p; 
                    num_actu++;
                } else{System.out.println("la patient ne pas réservé une chambre");}
            } else {System.out.println("le tableau de ce médicin est déjà rempli au maximum");}
        } else{System.out.println("le patient n'est pas une Femme Enceinte");}
    }
        
    public void retirerPatient(FemmeEnceinte p){
        if(p.accouche=true){
            for(int i=0;i<tabPatients.length;i++){
                if(tabPatients[i]==p){
                    tabPatients[i]=null;
                    if(i!=tabPatients.length-1){
                        for(int r=i; r<tabPatients.length-1;r++){
                            tabPatients[r]=tabPatients[r+1];
                        }
                        tabPatients[tabPatients.length-1]=null;
                    }
                    num_actu--;
                }
            }
        }else{System.out.println("La patient ne pas encore acouché");}
        
    }

    public void operer(Patient p) throws PasDeChambreException{
        if(!p.chambre_reserve){throw new PasDeChambreException();}
        else{
        if (p instanceof FemmeEnceinte) {
            hopital.getSalleTravail().retirerPatient(p);
            hopital.getSalleReveil().retirerPatient(p);
            p.setLocalisation("bloc opératoire");
            hopital.getBlocOperatoire().ajouterPatient(p);
            FemmeEnceinte f = (FemmeEnceinte) p;
            System.out.println("\nDébut de l'opération obstétrique...");
            //appeler anesthesie - locale , aprés :
            //l'opération est une césarienne
            anesthesier(p, "locale");
            System.out.println("Réalisation de la césarienne...");
            f.accouche=true;
            System.out.println("Opération terminée.\n");
            p.setLocalisation("salle de reveil");
            hopital.getBlocOperatoire().retirerPatient(p);
            hopital.getSalleReveil().ajouterPatient(p);
        }else{
            System.out.println("le patient n'est pas une Femme Enceinte");
        }
        }
    }
}