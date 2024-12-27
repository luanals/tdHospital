public class FemmeEnceinte extends Patient{
    protected String date_bebe;
    protected boolean accouche;
    protected static int count_f;

    public FemmeEnceinte(String n, String p, boolean a, String d, boolean m, String de, String ds, boolean c, Hopital hopital, String naissance_bebe, boolean accouche) throws InvalidDateException{
        super(n,p,a,d,m,de,ds,c,hopital);
        date_bebe = naissance_bebe;
        this.accouche = accouche;
        count_f++;
        if (chambre_reserve) {
            hopital.getSalleTravail().ajouterPatient(this);
            this.localisation = "salle de travail";
        }
    }

    //public String getNom(){return nom;} //méthode accesseur
    //public void setPrenom(String p){super.prenom=p;} //méthode mutateur
    //public String getPrenom(){return prenom;}
    //public void setDateNaissance(String date){super.date_naissance=date;}
    //public String getDateNaissance(){return date_naissance;}
    public void setChambre(boolean reserve){chambre_reserve=reserve;}
    public boolean getChambre(){return chambre_reserve;}
    public void setDateBebe(String d){date_bebe=d;}
    public String getDateBebe(){return date_bebe;}
    public void setAccouche(boolean accoucher){accouche=accoucher;}
    public boolean getAccouche(){return accouche;}



    public String toString(){
        return(prenom+" "+nom+" "+date_naissance+" "+chambre_reserve+" "+date_bebe);
    }

    public void afficher(){
        System.out.println(prenom+" "+nom+" "+date_naissance+" "+chambre_reserve+" "+date_bebe);
    }

    public static int nb_femmesEnceints(){
        return(FemmeEnceinte.count_f);
    }

}
