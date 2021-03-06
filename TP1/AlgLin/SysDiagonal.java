package AlgLin;

/**
 * Classe définissant un système linéaire diagonal
 *
 * @author Joshua Galien - Quentin Vauthier
 */
public class SysDiagonal extends SysLin {

    /**
     * Constructeur de la classe SysDiagonal
     *
     * @param matriceSysteme la matrice du système
     * @param secondMembre le second membre du système
     * @throws IrregularSysLinException si le système linéaire n'est pas régulier
     */
    public SysDiagonal(Matrice matriceSysteme, Vecteur secondMembre) throws IrregularSysLinException {
        super(matriceSysteme, secondMembre);
    }

    /**
     * Méthode permettant de résoudre le système
     *
     * @return le vecteur correspondant à la résolution
     * @throws IrregularSysLinException si le système linéaire n'est pas régulier
     */
    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        Vecteur res = new Vecteur(matriceSystem.nbLigne());

        double x;

        for (int i = 0; i < matriceSystem.nbLigne(); i++) {
            x = secondMembre.getCoef(i) / matriceSystem.getCoef(i, i);
            res.remplacecoef(i, x);
        }

        return res;
    }

    public static void main(String[] args) throws IrregularSysLinException {
        double[][] mat = {{2, 0}, {0, 2}};
        Matrice matrice = new Matrice(mat);
        System.out.println(matrice.toString());
        Vecteur vecteur = new Vecteur(new double[] {1,2});
        System.out.println(vecteur);
        SysDiagonal diag = new SysDiagonal(matrice, vecteur);
        System.out.println("résolution = \n" + diag.resolution());
    }
}
