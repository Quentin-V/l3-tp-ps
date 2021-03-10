package AlgLin;

public class IllegalOperationException extends Exception {
    public IllegalOperationException() {
        super("L'opération demandée n'est pas valide");
    }
}
