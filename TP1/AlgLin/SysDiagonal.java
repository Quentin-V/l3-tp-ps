package AlgLin;

public class SysDiagonal extends SysLin {

    public SysDiagonal(Matrice m, Vecteur v) throws IrregularSysLinException {
        super(m, v);
    }

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
