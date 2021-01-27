package AlgLin;

public class SysDiagonal extends SysLin {
	SysDiagonal(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {
		return null;
	}
}
