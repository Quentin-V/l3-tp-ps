package AlgLin;

public class Vecteur extends Matrice {
    Vecteur(int nbligne, int nbcolonne) {
        super(nbligne, nbcolonne);
    }

    Vecteur(double[][] tableau) {
        super(tableau);
    }

    Vecteur(String fichier) {
        super(fichier);
    }
}
