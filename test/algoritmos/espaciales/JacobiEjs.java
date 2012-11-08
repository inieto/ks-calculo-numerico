package algoritmos.espaciales;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class JacobiEjs {
	private double tolerancia = 0.001;
	private int limiteIntentos = 15;
	
	@Test
	public void ejercicio1() {
		double[][] a = {{ 0, 1,  1 },
						{ 2, 4, -2 },
						{ 0, 3, 15 }};
		double[] b = { 4, 2, 36 };
		Jacobi jacob = new Jacobi();
		jacob.setMatriz(a, b);
		jacob.setTolerancia(this.tolerancia);
		jacob.setLimiteIntentos(this.limiteIntentos);
//		{0,0,0} diverge. {-0.9,2,2} llega a la raiz!. Todas las otras combinaciones con los números 2 divergen.
		jacob.setAproximacionInicial(new double[]{-0.9,2,2});
		double[] x = jacob.calcular();
		imprimirMatriz(x);
		assertTrue(Arrays.equals(x, new double[]{-1,2,2}));	//{-1,2,2} es raiz hallada con EliminacionGauseana
	}
	
	private void imprimirMatriz(double[] x) {
		for (int i = 0; i < x.length; i++) {
			System.out.println(x[i]);
		}
	}
}
