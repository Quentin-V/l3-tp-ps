package AlgLin;

/**
 * Exception décrivant un système linéaire irrégulier
 *
 * @author Joshua Galien - Quentin Vauthier
 */
public class IrregularSysLinException extends Exception {
    @Override
    public String toString() {
        return "Le système est irrégulier";
    }
}
