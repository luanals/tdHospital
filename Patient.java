import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Patient {

    protected final String nom;
    protected String prenom;
    protected String date_naissance;
    protected boolean allergique;
    protected boolean malade;
    protected static int count_p;
    protected String date_entree;
    protected String date_sortie;
    protected String localisation;
    protected boolean chambre_reserve;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);


    public Patient(String nom, String prenom, boolean alllergie, String date, boolean malade, String date_in, String date_out, boolean chambre, Hopital hopital) throws InvalidDateException{
        this.nom=nom;
        this.prenom=prenom;
        allergique=alllergie;
        date_naissance=date;
        this.malade=malade;
        date_entree = validateDate(date_in);
        date_sortie = (date_sortie != null) ? validateDate(date_out) : null; //variable = (condition) ? value_if_true : value_if_false;
        if (this.date_sortie != null && LocalDate.parse(this.date_sortie, formatter).isBefore(LocalDate.parse(this.date_entree, formatter))) {
            throw new InvalidDateException("La date de sortie ne peut pas être antérieure à la date d'entrée.");}

        count_p++;
        chambre_reserve = chambre;
        this.localisation = "accueil";
        if(!(this instanceof FemmeEnceinte)){
           if (chambre_reserve) {
            boolean added = hopital.assignerPatientChambre(this);
            if (added) {
                this.localisation = "chambre";
            }
            } 
        }
        
    }

    protected String validateDate(String date) throws InvalidDateException {
        String[] dateFormats = {"ddMMyyyy", "dd-MM-yyyy", "dd/MM/yyyy"}; //apres, peut-etre add plus de options

        for (String format : dateFormats) {
            try {
                LocalDate parsedDate = LocalDate.parse(date, formatter);
                return parsedDate.format(formatter);  // Returns avec le bon format
            } catch (DateTimeParseException e) {//Même si le format est correct, l’API de dates vérifie la validité des valeurs
                // Si n'arrive pas a faire le parsing, essaye le format suivant
                continue;
            }
        }

        // Si aucun format n’est valide, lance l’exception
        throw new InvalidDateException();
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String toString(){
        return(prenom+" "+nom+" "+date_naissance);
    }

    public void afficher(){
        System.out.println(prenom+" "+nom+" "+date_naissance);
    }

    public void afficher_fichePatient(){
        System.out.print("Nom, Prenom : "+nom+", "+prenom+"\nDate de Naissance : "+date_naissance+"\nAllergique au produit d'anesthesie : ");
        if(allergique){System.out.println("Oui");}
        else{System.out.println("Non");}
        System.out.println("date entree : "+date_entree+"    date sortie : "+date_sortie+"\n localisation : "+localisation);
        System.out.print("Le patient a-t-il une chambre ? ");
        if(chambre_reserve){System.out.println("Oui");}
        else{System.out.println("Non");}
    }

    public static boolean equals(Patient p1, Patient p2){
        if(p1.nom==p2.nom&&p1.prenom==p2.prenom&&p1.date_naissance==p2.date_naissance){
            //System.out.println("true");
            return true;
        }
        else{ //System.out.println("false");
            return false;}
    }

    public static int nb_patients(){
        return(Patient.count_p);
    }

    private long getDateDiff() {

        LocalDate d1 = LocalDate.parse(this.date_entree,formatter);
        LocalDate d2;
        if(date_sortie==null){
            d2 = LocalDate.now();
            date_sortie=d2.format(formatter);
        } else{
            d2 = LocalDate.parse(this.date_sortie,formatter);
        }       
        
        //long diffInMillies = d2.getTime() - d1.getTime();
        //return timeUnit.convert(diffInMillies,TimeUnit.DAYS);

        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        long diffDays = diff.toDays();
        return diffDays;
    }

    public void montant_regler(){

        long jours=this.getDateDiff();
        double montant= jours*Chambre.prix;
        System.out.println("Le montant a regler c'est de : "+montant);

    }
}