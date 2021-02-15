package Tests.AlgLin;

import AlgLin.IrregularSysLinException;
import AlgLin.Matrice;
import AlgLin.SysTriangInf;
import AlgLin.Vecteur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Classe de tests sur la classe SysTriangInf
 *
 * @author Joshua Galien - Quentin Vauthier
 */
class SysTriangInfTest {

	@Test
	public void testConstruct() {
		Matrice m = new Matrice(new double[][]{
				{1,0,0},
				{1,1,0},
				{1,1,1}
		});
		Vecteur v = new Vecteur(3);
		try {
			new SysTriangInf(m,v);
		}catch (IrregularSysLinException irr) {
			fail("Le système n'est pas créé alors qu'il devrait");
		}

		//2ème test
		m = new Matrice(new double[][]{
				{1,1,0}, // Système irrégulier
				{1,1,0},
				{1,1,1}
		});
		v = new Vecteur(3);
		try {
			new SysTriangInf(m,v);
			fail("Le système est créé alors qu'il ne devrait pas");
		}catch (IrregularSysLinException ignored) {}
	}

	@Test
	public void resolution() {
		Matrice m = new Matrice(new double[][]{
				{3, 0, 0},
				{2, 2, 0},
				{4, 3, 2}
		});
		Vecteur v = new Vecteur(new double[]{6, 6, 7});
		Vecteur s = new Vecteur(new double[]{2, 1, -2});
		try {
			SysTriangInf sys = new SysTriangInf(m, v);
			assertEquals(s, sys.resolution());
		}catch (Exception e) {
			fail();
		}

		// 2ème test
		m = new Matrice(new double[][]{
				{8, 0, 0},
				{4, 3, 0},
				{5, 1, 2}
		});
		v = new Vecteur(new double[]{-8, 5, 10});
		s = new Vecteur(new double[]{-1, 3, 6});
		try {
			SysTriangInf sys = new SysTriangInf(m, v);
			assertEquals(s, sys.resolution());
		}catch (Exception e) {
			fail();
		}
	}
}