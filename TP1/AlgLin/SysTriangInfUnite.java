package AlgLin;

/**
 * @author Joshua Galien - Quentin Vauthier
 */
public class SysTriangInfUnite extends SysTriangInf {

    /**
     * Constructeur de la classe SysTriangInfUnite
     *
     * @param matriceSystem la matrice du système
     * @param secondMembre  le second membre du système
     * @throws IrregularSysLinException si le système linéaire n'est pas régulier
     */
    public SysTriangInfUnite(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
        super(matriceSystem, secondMembre);

        for (int i = 0; i < matriceSystem.nbColonne(); i++) {
            if(matriceSystem.getCoef(i, i) != 1){
                throw new IrregularSysLinException();
            }
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
