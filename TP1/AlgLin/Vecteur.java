package AlgLin;

public class Vecteur extends Matrice {

	Vecteur(int nbligne) {
		super(nbligne, 1);
	}

	Vecteur(double[] tableau) {
		super(tableau.length, 1);
		for (int i = 0; i < tableau.length; ++i) {
			this.coefficient[i][0] = tableau[i];
		}
	}

	@Override
	public int nbLigne() {
		return super.nbLigne();
	}

	public double getCoef(int ligne) {
		return super.getCoef(ligne, 0);
	}

	@Override
	public void remplacecoef(int ligne, int colonne, double value) {
		super.remplacecoef(ligne, 0, value);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	static double scalaire(Vecteur v1, Vecteur v2) {
		double scal = 0;
		int v1T = v1.nbLigne();
		int v2T = v2.nbLigne();
		int grandeTaille = Math.max(v1T, v2T);
		for(int i = 0; i < grandeTaille; ++i) {
			if(i > v1T || i > v2T) continue;
			scal += v1.coefficient[i][0] * v2.coefficient[i][0];
		}
		return scal;
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
