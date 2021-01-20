package AlgLin;

public class Vecteur extends Matrice {

	/**
	 * Constructeur de Vecteur avec en paramètre le nombre de lignes
	 * @param nbligne le nombre de lignes du vecteur
	 */
	Vecteur(int nbligne) {
		super(nbligne, 1);
	}

	/**
	 * Constructeur de Vecteur à partir d'un tableau
	 * @param tableau le tableau à recopier dans le vecteur
	 */
	Vecteur(double[] tableau) {
		super(tableau.length, 1);
		for (int i = 0; i < tableau.length; ++i) {
			this.coefficient[i][0] = tableau[i];
		}
	}

	/**
	 * Construit un vecteur à partir d'un fichier respectant le format "nbLig 1 val1 val2 ..."
	 * @param fichier le fichier source
	 * @throws Exception renvoie une exception si le vecteur a plus d'une colonne (erreur dans le fichier source)
	 */
	Vecteur(String fichier) throws Exception {
		super(fichier);
		if (nbColonne() != 1)
			throw new Exception("Il y a trop de colonne définies dans le fichier pour que cela soit un vecteur");
	}

	/**
	 * Retourne le nombre de ligne d'un vecteur
	 * @return le nombre de ligne du vecteur
	 */
	@Override
	public int nbLigne() {
		return super.nbLigne();
	}

	/**
	 * Accesseur sur les coefficients du vecteur
	 * @param ligne le numéro de la ligne à laquelle on veut accéder
	 * @return le coefficient à la position indiquée
	 */
	public double getCoef(int ligne) {
		return super.getCoef(ligne, 0);
	}

	/**
	 * Remplace un coefficient à la position donnée avec la valeur donnée
	 * @param ligne la position à modifier
	 * @param value la nouvelle valeur
	 */
	public void remplacecoef(int ligne, double value) {
		super.remplacecoef(ligne, 0, value);
	}

	/**
	 * Représentation d'un vecteur en chaîne de caractères
	 * @return la chaîne repésentant le vecteur
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * Retourne le produit scalaire de deux vecteurs
	 * @param v1 le premier vecteur
	 * @param v2 le vecteur à multiplier au premier
	 * @return le produit scalaire de deux vecteurs
	 */
	static double scalaire(Vecteur v1, Vecteur v2) {
		double scal = 0;
		int v1T = v1.nbLigne();
		int v2T = v2.nbLigne();
		int grandeTaille = Math.max(v1T, v2T);
		for(int i = 0; i < grandeTaille; ++i) {
			if(i > v1T || i > v2T) break;
			scal += v1.coefficient[i][0] * v2.coefficient[i][0];
		}
		return scal;
	}


    public static void main(String[] args) throws Exception{
        Vecteur v = new Vecteur("./ressources/vecteur.txt");

        System.out.println(v);
    }
}
