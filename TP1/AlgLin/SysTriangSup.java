package AlgLin;

public class SysTriangSup extends SysLin {
	SysTriangSup(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {
		return null;
	}
}
