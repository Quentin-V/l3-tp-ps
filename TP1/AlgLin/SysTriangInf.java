package AlgLin;

/**
 * Classe définissant un système linéaire triangulaire inférieur
 *
 * @author Joshua Galien - Quentin Vauthier
 */
public class SysTriangInf extends SysLin {
    /**
     * Constructeur de la classe SysTriangInf
     *
     * @param matriceSystem la matrice du système
     * @param secondMembre  le second membre du système
     * @throws IrregularSysLinException si le système linéaire n'est pas régulier
     */
    public SysTriangInf(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
        super(matriceSystem, secondMembre);
        for (int i = 0; i < matriceSystem.nbLigne(); ++i) {
            if (matriceSystem.getCoef(i, i) == 0) throw new IrregularSysLinException();
            for (int j = i + 1; j < matriceSystem.nbLigne(); ++j) {
                if (matriceSystem.getCoef(i, j) != 0) throw new IrregularSysLinException();
            }
        }
    }

    /**
     * Méthode permettant la résolution du système
     *
     * @return le vecteur correspondant à la résolution
     * @throws IrregularSysLinException si le système linéaire n'est pas régulier
     */
    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        Vecteur solution = new Vecteur(secondMembre.nbLigne());
        for (int i = 0; i < solution.nbLigne(); ++i) {
            double coef;
            if (i == 0) coef = secondMembre.getCoef(i) / matriceSystem.getCoef(i, i);
            else {
                coef = secondMembre.getCoef(i);
                for (int j = 0; j < i; ++j)
                    coef -= matriceSystem.getCoef(i, j) * solution.getCoef(j);
                coef /= matriceSystem.getCoef(i, i);
            }
            solution.remplacecoef(i, coef);
        }
        return solution;
    }
}
