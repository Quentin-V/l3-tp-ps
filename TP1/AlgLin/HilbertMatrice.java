package AlgLin;

public class HilbertMatrice {

	public static void main(String[] args) throws IrregularSysLinException, IllegalOperationException {

		for(int ordre = 3; ordre < 20; ++ordre) {
			Matrice m = creerHilbert(ordre);

			Matrice inv = m.inverse();

			//System.out.println("Matrice d'Hilbert : \n" + m);
			//System.out.println("Inverse : \n" + inv);

			Matrice produit = Matrice.produit(inv, m);

			//System.out.println("Produit : \n" + produit);

			System.out.println("Ordre : " + ordre);
			System.out.println("Vérification du produit : " + verifIdentite(produit));
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

}
