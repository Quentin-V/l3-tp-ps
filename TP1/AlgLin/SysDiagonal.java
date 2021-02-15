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

        Matrice m = super.getMatriceSystem();
        Vecteur v = super.getSecondMembre();
        Vecteur res = new Vecteur(super.getMatriceSystem().nbLigne());

        double x;

        for (int i = 0; i < super.getMatriceSystem().nbLigne(); i++) {
            x = v.getCoef(i) / m.getCoef(i, i);
            res.remplacecoef(i, x);
        }

        return res;
    }

    public static void main(String[] args) throws IrregularSysLinException {
        double[][] mat = {{2, 0}, {0, 2}};
        Matrice a = new Matrice(mat);
        System.out.println(a.toString());
        Vecteur vecteur1 = new Vecteur(2);
        vecteur1.remplacecoef(0, 1);
        vecteur1.remplacecoef(1, 2);
        System.out.println(vecteur1);
        SysDiagonal diag = new SysDiagonal(a, vecteur1);
        System.out.println("résolution = \n" + diag.resolution());
    }
}
