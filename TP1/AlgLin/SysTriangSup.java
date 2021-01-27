package AlgLin;

public class SysTriangSup extends SysLin {
	SysTriangSup(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {
		Vecteur solution = new Vecteur(secondMembre.nbLigne());
		for(int i = solution.nbLigne()-1; i >= 0; --i) {
			double coef;
			if(i == solution.nbLigne()-1) coef = secondMembre.getCoef(i) / matriceSystem.getCoef(i, i);
			else {
				coef = secondMembre.getCoef(i);
				for(int j = solution.nbLigne()-1; j > i; --j)
					coef -= matriceSystem.getCoef(i, j) *  solution.getCoef(j);
				coef /= matriceSystem.getCoef(i,i);
			}
			solution.remplacecoef(i, coef);
		}
		return solution;
	}
}
