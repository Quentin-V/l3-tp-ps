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
        setSecondMembre(L.resolution());

        D = new SysDiagonal(matriceSystem, secondMembre);
        setSecondMembre(D.resolution());

        R = new SysTriangSupUnite(matriceSystem, secondMembre);
        return R.resolution();
    }

    /**
     * Remplace les coefficients de la matrice d'origine du système par les
     * coefficients non nuls et non égaux à 1 des trois matrices L, D et R.
     */
    public void factorLDR() {
        Matrice matriceL = new Matrice(this.getOrdre(), this.getOrdre());

        for (int i = 0; i < this.getOrdre(); i++) {
            for (int j = i + 1; j < this.getOrdre(); j++)
                for (int k = i; k < this.getOrdre(); k++) {
                    if (k == i)
                        matriceL.remplacecoef(j, k, this.matriceSystem.getCoef(j, k) / this.matriceSystem.getCoef(i, i));
                    this.matriceSystem.remplacecoef(j, k, this.matriceSystem.getCoef(j, k) - (matriceL.getCoef(j, i) * this.matriceSystem.getCoef(i, k)));
                }
            for (int j = 0; j < this.getOrdre(); j++)
                if (j > i)
                    this.matriceSystem.remplacecoef(i, j, this.matriceSystem.getCoef(i, j) / this.matriceSystem.getCoef(i, i));
                else if (j < i)
                    this.matriceSystem.remplacecoef(i, j, matriceL.getCoef(i, j));
        }
    }

    /**
     * Modificateur sur l'attribut secondMembre
     *
     * @param newSecondMembre le nouveau second membre du système
     */
    public void setSecondMembre(Vecteur newSecondMembre) {
        this.secondMembre.recopie(newSecondMembre);
    }

    public static void main(String[] args) throws IrregularSysLinException {
        double[][] mat = {{4, -20, -12}, {-8, 45, 44}, {20, -105, -79}};
        Matrice matrice = new Matrice(mat);
        System.out.println(matrice.toString());
        Vecteur vecteur = new Vecteur(new double[]{5, 4, 9});
        System.out.println(vecteur);
        Helder h = new Helder(matrice, vecteur);
        Vecteur res = h.resolution();
        System.out.println("résolution \n" + res);
        System.out.println("vérification : ");
        Matrice matrice1 = Matrice.addition(Matrice.produit(matrice, res), vecteur.produit(-1));
        Vecteur verif = new Vecteur(matrice1.nbLigne());
        for (int i = 0; i < matrice1.nbLigne(); i++) {
            verif.remplacecoef(i, matrice1.getCoef(i, 0));
        }
        System.out.println("vérification avec norme = " + (verif.L1() <= Matrice.EPSILON));

        h.matriceSystem = matrice;
        h.secondMembre = vecteur;
        h.factorLDR();
        System.out.println("résolution partielle \n\n" + h.resolutionPartielle());
    }
}
