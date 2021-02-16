package AlgLin;

/**
 * Classe décrivant décrire un système linéaire général qui est résolu en utilisant la
 * factorisation LDR
 *
 * @author Joshua Galien - Quentin Vauthier
 */
public class Helder extends SysLin {

    /**
     * Constructeur de la classe SysLin
     *
     * @param matriceSystem la matrice du système
     * @param secondMembre  le second membre du système
     * @throws IrregularSysLinException si le système linéaire n'est pas régulier
     */
    public Helder(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
        super(matriceSystem, secondMembre);
    }

    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        return null;
    }

    /**
     * Suppose que la matrice du système est déjà sous forme factorisée et n'effectue que la
     * résolution des systèmes élémentaires
     *
     * @return le vecteur résultant de la solution
     */
    public Vecteur resolutionPartielle() {
        return null;
    }

    /**
     * Remplace les coefficients de la matrice d'origine du système par les
     * coefficients non nuls et non égaux à 1 des trois matrices L, D et R.
     */
    public void factorLDR() {

    }

    /**
     * Modificateur sur l'attribut secondMembre
     *
     * @param newSecondMembre le nouveau second membre du système
     */
    public void setSecondMembre(Vecteur newSecondMembre) {
        this.secondMembre = newSecondMembre;
    }

    public static void main(String[] args) {
    }
}
