package AlgLin;

/**
 * Classe permettant de définir un système triangulaire supérieur
 *
 * @author Joshua Galien - Quentin Vauthier
 */
public class SysTriangSup extends SysLin {
    /**
     * Constructeur de la classe SysTriangSup
     *
     * @param matriceSystem la matrice du système
     * @param secondMembre  le second membre du système
     * @throws IrregularSysLinException si le système linéaire n'est pas régulier
     */
    public SysTriangSup(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
        super(matriceSystem, secondMembre);
        for (int i = matriceSystem.nbLigne() - 1; i > 0; --i) {
            if (matriceSystem.getCoef(i, i) == 0) throw new IrregularSysLinException();
            for (int j = i - 1; j > 0; --j) {
                if (matriceSystem.getCoef(i, j) != 0) throw new IrregularSysLinException();
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
        Vecteur solution = new Vecteur(secondMembre.nbLigne());
        for (int i = solution.nbLigne() - 1; i >= 0; --i) {
            double coef;
            if (i == solution.nbLigne() - 1) coef = secondMembre.getCoef(i) / matriceSystem.getCoef(i, i);
            else {
                coef = secondMembre.getCoef(i);
                for (int j = solution.nbLigne() - 1; j > i; --j)
                    coef -= matriceSystem.getCoef(i, j) * solution.getCoef(j);
                coef /= matriceSystem.getCoef(i, i);
            }
            solution.remplacecoef(i, coef);
        }
        return solution;
    }
}
