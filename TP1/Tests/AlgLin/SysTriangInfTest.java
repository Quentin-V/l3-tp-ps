package AlgLin;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SysTriangInfTest {

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