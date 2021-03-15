package AlgLin;

public class ConditionnementNorme1 implements FonctionMatriceGenerale {
    @Override
    public double conditionnement(Matrice matrice) throws IrregularSysLinException, IllegalOperationException {
        Matrice inverse = matrice.inverse();

        double norme_a = matrice.norme_1();
        double norme_b = inverse.norme_1();

        return norme_a * norme_b;
    }
}
