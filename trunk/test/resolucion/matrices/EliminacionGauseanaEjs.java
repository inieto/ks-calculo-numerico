package resolucion.matrices;

import static org.junit.Assert.*;


import org.junit.Test;

import resolucion.matrices.EliminacionGauseana;



public class EliminacionGauseanaEjs {
	@Test
	public void ejercicio1() {
		double[][] a = {{ 0, 1,  1 },
						{ 2, 4, -2 },
						{ 0, 3, 15 }};
		double[] b = { 4, 2, 36 };
		EliminacionGauseana eg = new EliminacionGauseana();
		eg.setMatriz(a, b);
		double[] x = eg.calcular();
		imprimirMatriz(x);
	}

	
	@Test
	public void ejercicio2() {
		double[][] a = {{ 1,-1, 2,-1 },
						{ 2,-2, 3,-3 },
						{ 1, 1, 1, 0 },
						{ 1,-1, 4, 3 }};
		double[] b = {-8,-20,-2, 4};
		EliminacionGauseana eg = new EliminacionGauseana();
		eg.setMatriz(a, b);
		double[] x = eg.calcular();
		imprimirMatriz(x);
	}

	private void imprimirMatriz(double[] x) {
		for (int i = 0; i < x.length; i++) {
			System.out.println(x[i]);
		}
	}
}
