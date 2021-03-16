package AlgLin;

public class HilbertMatrice {

	final static int ORDRE_MIN = 3;
	final static int ORDRE_MAX = 3;

	public static void main(String[] args) throws IrregularSysLinException, IllegalOperationException {

		for(int ordre = ORDRE_MIN; ordre <= ORDRE_MAX; ++ordre) {
			Matrice m = creerHilbert(ordre);

			Matrice inv = m.inverseNoVerif();

			//System.out.println("Matrice d'Hilbert : \n" + m);
			System.out.println("Inverse de la matrice Hilbert ("+ ordre +"): \n" + inv);

			Matrice produit = Matrice.produit(inv, m);

			//System.out.println("Produit : \n" + produit);
			System.out.println("Calcul de distance avec le produit de l'inverse (moyenne) : " + calculDistance(produit));
			System.out.println("Conditionnement de l'inverse : ");
			inv.conditionnement(Matrice::norme_1);
			inv.conditionnement(Matrice::norme_inf);
			System.out.println("Vérification du produit (Toutes les valeurs bonnes +/- Epsilon) : " + verifIdentite(produit));
			if(!verifIdentite(produit)) System.err.println("Erreur de vérification pour la matrice d'ordre " + ordre);
		}
	}

	static Matrice creerHilbert(int ordre) {
		Matrice m = new Matrice(ordre, ordre);
		for(int i = 0; i < m.nbLigne(); ++i) {
			for(int j = 0; j < m.nbColonne(); ++j) {
				m.remplacecoef(i, j, 1.0/(i+j+1));
			}
		}
		return m;
	}

	static boolean verifIdentite(Matrice m) {
		for(int i = 0; i < m.nbLigne(); ++i) {
			for(int j = 0; j < m.nbColonne(); ++j) {
				if(i == j) {
					if(!(m.getCoef(i,j) > 1 - Matrice.EPSILON && m.getCoef(i,j) < 1 + Matrice.EPSILON)) return false;
				}else {
					if(!(m.getCoef(i,j) > 0 - Matrice.EPSILON && m.getCoef(i,j) < 0 + Matrice.EPSILON)) return false;
				}

			}
		}
		return true;
	}

	static double calculDistance(Matrice m) {
		double distance = 0;
		for(int i = 0; i < m.nbLigne(); ++i) {
			for(int j = 0; j < m.nbColonne(); ++j) {
				double coeff = m.getCoef(i, j);
				int ref = i == j ? 1 : 0;
				if(coeff >= ref) distance += coeff - ref;
				else distance += ref - coeff;
			}
		}
		distance /= m.nbLigne() * m.nbColonne();
		return distance;
	}

}
