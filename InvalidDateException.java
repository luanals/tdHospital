public class InvalidDateException extends Exception {

    public InvalidDateException() {
        super("Date invalide");
    }

    // pour avoir des messages personalisés 
    public InvalidDateException(String message) {
        super(message);
    }
}

