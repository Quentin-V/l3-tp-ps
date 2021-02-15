package AlgLin;

/**
 * Classe abstraite permettant de définir un système linéaire
 *
 * @author Joshua Galien - Quentin Vauthier
 */
public abstract class SysLin {
    /**
     * Ordre du système
     */
    private int ordre;

    /**
     * Matrice du sysème
     */
    protected Matrice matriceSystem;

    /**
     * Second membre du système
     */
    protected Vecteur secondMembre;

    /**
     * Constructeur de la classe SysLin
     *
     * @param matriceSystem la matrice du système
     * @param secondMembre  le second membre du système
     * @throws IrregularSysLinException si le système linéaire n'est pas régulier
     */
    public SysLin(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
        if (matriceSystem.nbLigne() != matriceSystem.nbColonne() || matriceSystem.nbLigne() != secondMembre.nbLigne())
            throw new IrregularSysLinException();
        this.matriceSystem = matriceSystem;
        this.secondMembre = secondMembre;
    }

    /**
     * Accesseur sur l'attribut ordre
     *
     * @return l'ordre du système
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * Accesseur sur l'attribut matriceSystem
     *
     * @return la matrice du système
     */
    public Matrice getMatriceSystem() {
        return matriceSystem;
    }

    /**
     * Accesseur sur l'attribut secondMembre
     *
     * @return le second membre du système
     */
    public Vecteur getSecondMembre() {
        return secondMembre;
    }

    /**
     * Méthode permettant de résoudre le système
     *
     * @return le vecteur de résolution
     * @throws IrregularSysLinException si le système linéaire n'est pas régulier
     */
    abstract public Vecteur resolution() throws IrregularSysLinException;
}
