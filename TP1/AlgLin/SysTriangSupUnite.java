package AlgLin;

/**
 * @author Joshua Galien - Quentin Vauthier
 */
public class SysTriangSupUnite extends SysTriangSup {

    /**
     * Constructeur de la classe SysTriangSupUnite
     *
     * @param matriceSystem la matrice du système
     * @param secondMembre  le second membre du système
     * @throws IrregularSysLinException si le système linéaire n'est pas régulier
     */
    public SysTriangSupUnite(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
        super(matriceSystem, secondMembre);

        for (int i = 0; i < matriceSystem.nbColonne(); i++) {
            this.matriceSystem.remplacecoef(i, i, 1);
        }
    }

    /**
     * Méthode permettant de résoudre le système
     *
     * @return le vecteur correspondant à la résolution
     * @throws IrregularSysLinException si le système linéaire n'est pas régulier
     */
    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        return super.resolution();
    }
}
