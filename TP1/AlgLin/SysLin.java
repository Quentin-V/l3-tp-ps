package AlgLin;

public abstract class SysLin {
	private int ordre;
	protected Matrice matriceSystem;
	protected Vecteur secondMembre;

	SysLin(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		if (matriceSystem.nbLigne() != matriceSystem.nbColonne() || matriceSystem.nbLigne() != secondMembre.nbLigne())
			throw new IrregularSysLinException();
		this.matriceSystem = matriceSystem;
		this.secondMembre = secondMembre;
	}

	public int getOrdre() {
		return ordre;
	}

	public Matrice getMatriceSystem() {
		return matriceSystem;
	}

	public Vecteur getSecondMembre() {
		return secondMembre;
	}

	abstract public Vecteur resolution() throws IrregularSysLinException;
}
