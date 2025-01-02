public class PasDeChambreException extends Exception{
    public String toString() {
        return ("\nLe patient n'a pas une chambre !\n Veuillez assigner une chambre avant de procéder.");
    }
}
