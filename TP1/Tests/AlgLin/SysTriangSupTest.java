package AlgLin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SysTriangSupTest {

	@Test
	public void testConstruct() {
		Matrice m = new Matrice(new double[][]{
				{1,1,1},
				{0,1,1},
				{0,0,1}
		});
		Vecteur v = new Vecteur(3);
		try {
			new SysTriangSup(m,v);
		}catch (IrregularSysLinException irr) {
			fail("Le système n'est pas créé alors qu'il devrait");
		}

		//2ème test
		m = new Matrice(new double[][]{
				{1,1,1}, // Système irrégulier
				{0,1,1},
				{0,1,1}
		});
		v = new Vecteur(3);
		try {
			new SysTriangSup(m,v);
			fail("Le système est créé alors qu'il ne devrait pas");
		}catch (IrregularSysLinException ignored) {}
	}

	@Test
	void resolution() {
		Matrice m = new Matrice(new double[][]{
				{2, 1, 5},
				{0, 3, 4},
				{0, 0, 8}
		});
		Vecteur v = new Vecteur(new double[]{10, 5, -8});
		Vecteur s = new Vecteur(new double[]{6, 3, -1});
		try {
			SysTriangSup sys = new SysTriangSup(m, v);
			assertEquals(s, sys.resolution());
		}catch (Exception e) {
			fail();
		}

		// 2ème test
		m = new Matrice(new double[][]{
				{2, 3, 4},
				{0, 2, 2},
				{0, 0, 3}
		});
		v = new Vecteur(new double[]{7, 6, 6});
		s = new Vecteur(new double[]{-2, 1, 2});
		try {
			SysTriangSup sys = new SysTriangSup(m, v);
			assertEquals(s, sys.resolution());
		}catch (Exception e) {
			fail();
		}
	}
}