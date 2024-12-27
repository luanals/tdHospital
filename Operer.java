public interface Operer {
    void anesthesier(Patient p, String typeAnesthesie);
    void operer(Patient p) throws PasDeChambreException;
}
