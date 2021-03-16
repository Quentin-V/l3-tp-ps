package AlgLin;

public class ConditionnementNormeInf implements FonctionMatriceGenerale {
    @Override
    public double conditionnement(Matrice matrice) throws IrregularSysLinException, IllegalOperationException {
        Matrice inverse = matrice.inverse();

        double norme_a = matrice.norme_inf();
        double norme_b = inverse.norme_inf();

        return norme_a * norme_b;
    }
}
