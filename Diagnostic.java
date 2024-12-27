import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Diagnostic {
    private String[] listeMedicaments;
    private String medicamentCorrect;
    private boolean patientGuerri;
    private int joursHospitalisation;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);


    public Diagnostic() {
        listeMedicaments = new String[10];
        for (int i = 0; i < 10; i++) {
            listeMedicaments[i] = "Medicament" + (i + 1);
        }
        
        Random random = new Random();
        medicamentCorrect = listeMedicaments[random.nextInt(10)];
        patientGuerri = false;
        joursHospitalisation = 0;
    }

    public void effectuerDiagnostic(Patient patient) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Voici les médicaments disponibles :");

    while (!patientGuerri && existeMedicamentsDisponibles()) {
        
        afficherMedicaments();
        System.out.println("Veuillez choisir un médicament (ex: Medicament1) :");
        String choix = scanner.nextLine();

        if (choix.equals(medicamentCorrect)) {
            patientGuerri = true;
            joursHospitalisation++;

            LocalDate dateEntree = LocalDate.parse(patient.date_entree, formatter);
            patient.date_sortie = dateEntree.plusDays(joursHospitalisation).format(formatter);

            System.out.println("Félicitations, le patient est guéri !");
            System.out.println("Date de sortie : " + patient.date_sortie);
            System.out.println("Jours d'hospitalisation : " + joursHospitalisation);
            patient.montant_regler();
        } else if (retirerMedicament(choix)) {
            joursHospitalisation++;
            System.out.println("Ce médicament n'a pas fonctionné. Essayez un autre.");
        } else {
            System.out.println("Choix invalide ou médicament déjà utilisé. Réessayez.");
        }
    }

    if (!patientGuerri) {
        System.out.println("Il n'y a plus de médicaments disponibles. Le diagnostic a échoué.");
    }

    scanner.close();
}

private void afficherMedicaments() {
    System.out.println("Médicaments disponibles :");
    for (String med : listeMedicaments) {
        if (med != null) {
            System.out.println("- " + med);
        }
    }
}

private boolean existeMedicamentsDisponibles() {
    for (String med : listeMedicaments) {
        if (med != null) {
            return true;
        }
    }
    return false;
}

private boolean retirerMedicament(String choix) {
    for (int i = 0; i < listeMedicaments.length; i++) {
        if (listeMedicaments[i] != null && listeMedicaments[i].equals(choix)) {
            listeMedicaments[i] = null;
            return true;
        }
    }
    return false; // Médicament introuvable ou déjà retiré
}

}
