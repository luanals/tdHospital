public class PasDeChambreException extends Exception{
    public String toString() {
        return ("\nle patient n'a pas une chambre !\n voulez-vous désigner une chambre pour le patient?");
    }
}
