package AlgLin;

public class Vecteur extends Matrice {
    Vecteur(int nbligne, int nbcolonne) {
        super(nbligne, nbcolonne);
    }

    Vecteur(double[][] tableau) {
        super(tableau);
    }

    Vecteur(String fichier) throws Exception {
        super(fichier);
        if (nbColonne() != 1)
            throw new Exception("Il y a trop de colonne définies dans le fichier pour que cela soit un vecteur");
    }

    public static void main(String[] args) throws Exception{
        Vecteur v = new Vecteur("./ressources/vecteur.txt");

        System.out.println(v);
    }
}
