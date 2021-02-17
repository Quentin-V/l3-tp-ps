package AlgLin;

/**
 * Classe décrivant décrire un système linéaire général qui est résolu en utilisant la
 * factorisation LDR
 *
 * @author Joshua Galien - Quentin Vauthier
 */
public class Helder extends SysLin {

    private SysTriangInfUnite L;
    private SysDiagonal D;
    private SysTriangSupUnite R;

    /**
     * Constructeur de la classe SysLin
     *
     * @param matriceSystem la matrice du système
     * @param secondMembre  le second membre du système
     * @throws IrregularSysLinException si le système linéaire n'est pas régulier
     */
    public Helder(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
        super(matriceSystem, secondMembre);

        L = new SysTriangInfUnite(matriceSystem, secondMembre);
        D = new SysDiagonal(matriceSystem, secondMembre);
        R = new SysTriangSupUnite(matriceSystem, secondMembre);
    }

    @Override
    public Vecteur resolution() throws IrregularSysLinException {
        factorLDR();
        return resolutionPartielle();
    }

    /**
     * Suppose que la matrice du système est déjà sous forme factorisée et n'effectue que la
     * résolution des systèmes élémentaires
     *
     * @return le vecteur résultant de la solution
     */
    public Vecteur resolutionPartielle() throws IrregularSysLinException {
        L = new SysTriangInfUnite(matriceSystem, secondMembre);
        Vecteur y = L.resolution();
        //setSecondMembre(y);

        D = new SysDiagonal(matriceSystem, y);
        Vecteur z = D.resolution();
        //setSecondMembre(z);

        R = new SysTriangSupUnite(matriceSystem, z);
        return R.resolution();
    }

    /**
     * Remplace les coefficients de la matrice d'origine du système par les
     * coefficients non nuls et non égaux à 1 des trois matrices L, D et R.
     */
    public void factorLDR() {

        Matrice L = new Matrice(getOrdre(), getOrdre()), D = new Matrice(getOrdre(), getOrdre()), R = new Matrice(getOrdre(), getOrdre());
        for (int i = 0; i < getOrdre(); i++) {
            D.remplacecoef(i, i, 1);
            for (int j = 0; j <= i; j++) {
                L.remplacecoef(i, j, 1);
                R.remplacecoef(j, i, 1);
            }
        }

        System.out.println("avant\n" + L + " \n" + D + "\n" + R);

        for (int i = 0; i < getOrdre(); i++) {
            double somme = 0;
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    somme += L.getCoef(i, k) * D.getCoef(k, k) * R.getCoef(k, j);
                }
                L.remplacecoef(i, j, (1 / D.getCoef(j, j)) * (matriceSystem.getCoef(i, j) - somme));
            }
            somme = 0;
            for (int k = 0; k < i; k++) {
                somme += L.getCoef(i, k) * D.getCoef(k, k) * R.getCoef(k, i);
                System.out.println("L.getCoef(i, k) = " + L.getCoef(i, k));
                System.out.println("D.getCoef(i, k) = " + D.getCoef(k, k));
                System.out.println("R.getCoef(i, k) = " + R.getCoef(k, i));
            }
            System.out.println("somme avant D " + somme);
            D.remplacecoef(i, i, matriceSystem.getCoef(i, i) - somme);
            somme = 0;
            for (int j = i + 1; j < getOrdre(); j++) {
                for (int k = 0; k < i; k++) {
                    somme += L.getCoef(i, k) * D.getCoef(k, k) * R.getCoef(k, j);
                }
                R.remplacecoef(i, j, (1 / D.getCoef(i, i)) * (matriceSystem.getCoef(i, j) - somme));
            }
        }

        System.out.println("après\n" + L + " \n" + D + "\n" + R);

        for (int i = 0; i < getOrdre(); i++) {
            for (int j = 0; j < getOrdre(); j++) {
                if (i == j) {
                    if (D.getCoef(i, j) != 0 && D.getCoef(i, j) != 1)
                        matriceSystem.remplacecoef(i, j, D.getCoef(i, j));
                }
                if (i < j) {
                    if (R.getCoef(i, j) != 0 && R.getCoef(i, j) != 1)
                        matriceSystem.remplacecoef(i, j, R.getCoef(i, j));
                }
                if (i > j) {
                    if (L.getCoef(i, j) != 0 && L.getCoef(i, j) != 1)
                        matriceSystem.remplacecoef(i, j, L.getCoef(i, j));
                }
            }
        }

//        System.out.println("avant\n" + L.getMatriceSystem() + " \n" + D.getMatriceSystem() + "\n" + R.getMatriceSystem());
//
//        for (int i = 0; i < matriceSystem.nbColonne(); i++) {
//            double somme = 0;
//            for (int j = 0; j < i; j++) {
//                for (int k = 0; k < j; k++) {
//                    somme += L.getMatriceSystem().getCoef(i, k) * D.getMatriceSystem().getCoef(k, k) * R.getMatriceSystem().getCoef(k, j);
//                }
//                L.getMatriceSystem().remplacecoef(i, j, (1 / D.getMatriceSystem().getCoef(j, j)) * (matriceSystem.getCoef(i, j) - somme));
//            }
//            somme = 0;
//            for (int k = 0; k < i; k++) {
//                somme += L.getMatriceSystem().getCoef(i, k) * D.getMatriceSystem().getCoef(k, k) * R.getMatriceSystem().getCoef(k, i);
//            }
//            System.out.println("somme avant D " + somme);
//            D.getMatriceSystem().remplacecoef(i, i, matriceSystem.getCoef(i, i) - somme);
//            somme = 0;
//            for (int j = 0; j < i + 1; j++) {
//                for (int k = 0; k < i - 1; k++) {
//                    somme += L.getMatriceSystem().getCoef(i, k) * D.getMatriceSystem().getCoef(k, k) * R.getMatriceSystem().getCoef(k, j);
//                }
//                R.getMatriceSystem().remplacecoef(i, j, (1 / D.getMatriceSystem().getCoef(j, j)) * (matriceSystem.getCoef(i, j) - somme));
//            }
//        }
//
//        System.out.println("après\n" + L.getMatriceSystem() + " \n" + D.getMatriceSystem() + "\n" + R.getMatriceSystem());

//        for (int i = 0; i < matriceSystem.nbLigne(); i++) {
//            for (int j = 0; j < matriceSystem.nbColonne(); j++) {
//                if (i == j) {
//                    if (D.getMatriceSystem().getCoef(i, j) != 0 && D.getMatriceSystem().getCoef(i, j) != 1)
//                        matriceSystem.remplacecoef(i, j, D.getMatriceSystem().getCoef(i, j));
//                }
//                if (i < j) {
//                    if (R.getMatriceSystem().getCoef(i, j) != 0 && R.getMatriceSystem().getCoef(i, j) != 1)
//                        matriceSystem.remplacecoef(i, j, R.getMatriceSystem().getCoef(i, j));
//                }
//                if (i > j) {
//                    if (L.getMatriceSystem().getCoef(i, j) != 0 && L.getMatriceSystem().getCoef(i, j) != 1)
//                        matriceSystem.remplacecoef(i, j, L.getMatriceSystem().getCoef(i, j));
//                }
//            }
//        }
    }

    /**
     * Modificateur sur l'attribut secondMembre
     *
     * @param newSecondMembre le nouveau second membre du système
     */
    public void setSecondMembre(Vecteur newSecondMembre) {
        this.secondMembre = newSecondMembre;
    }

    public static void main(String[] args) throws IrregularSysLinException {
        double[][] mat = {{2, 1}, {1, 2}};
        Matrice matrice = new Matrice(mat);
        System.out.println(matrice.toString());
        Vecteur vecteur = new Vecteur(new double[]{3, 2});
        System.out.println(vecteur);
        Helder h = new Helder(matrice, vecteur);
        Vecteur res = h.resolution();
        System.out.println("résolution \n" + res);
        System.out.println("résolution partielle \n" + h.resolutionPartielle());

        System.out.println("vérification : \n ");

        Vecteur verif = (Vecteur)
                Matrice.produit(
                        Matrice.produit(matrice, res),
                        res.produit(-1));

        System.out.println(verif.L1());



    }
}
