package AlgLin;

public class SysTriangSupUnite extends SysTriangSup {
	SysTriangSupUnite(Matrice matriceSystem, Vecteur secondMembre) throws IrregularSysLinException {
		super(matriceSystem, secondMembre);
	}

	@Override
	public Vecteur resolution() throws IrregularSysLinException {
		return super.resolution();
	}
}
