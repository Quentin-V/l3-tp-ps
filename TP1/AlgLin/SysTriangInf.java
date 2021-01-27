package AlgLin;

public class SysTriangInf extends SysLin {
	SysTriangInf(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {
		Vecteur solution = new Vecteur(secondMembre.nbLigne());
		for(int i = 0; i < solution.nbLigne(); ++i) {
			double coef;
			if(i == 0) coef = secondMembre.getCoef(i) / matriceSystem.getCoef(i, i);
			else {
				coef = secondMembre.getCoef(i);
				for(int j = 0; j < i; ++j)
					coef -= matriceSystem.getCoef(i, j) *  solution.getCoef(j);
				coef /= matriceSystem.getCoef(i,i);
			}
			solution.remplacecoef(i, coef);
		}
		return solution;
	}
}
