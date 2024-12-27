import java.util.ArrayList; // import the ArrayList class
public abstract class Salle {
    //par chaque salle monter un table de patients
    protected ArrayList<Patient> patients;
    public Salle(){
        patients = new ArrayList<>();
    }

    public void ajouterPatient(Patient p) {
        patients.add(p);
    }

    public void retirerPatient(Patient p) {
        patients.remove(p);
    }

    public void afficherPatients() {
        if (patients.isEmpty()) {
            System.out.println("Aucun patient dans cette salle.");
        } else {
            for (Patient p : patients) {
                System.out.println(p);
            }
        }
    }

    public int getNombreDePatients() {
        return patients.size();
    }
}
