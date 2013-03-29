package resolucion.matrices;

import static org.junit.Assert.*;

import java.util.Arrays;


import org.junit.Test;

import resolucion.matrices.Jacobi;



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
//		jacob.setAproximacionInicial(new double[]{0,0,0});
		double[] x = jacob.calcular();
		System.out.println("Solucion final "+ Arrays.toString(x));
		assertTrue(Arrays.equals(x, new double[]{-1,2,2}));	//{-1,2,2} es raiz hallada con EliminacionGauseana
	}
	
	@Test
	public void eJERCICIO3() {
		double[][] a = {{ 4,-1, 1 },
				{ -1, 4.25, 2.75 },
				{ 1, 2.75, 3.5 }};
		double[] b = {1,1,1};
		Jacobi jacob = new Jacobi();
		jacob.setMatriz(a, b);
		jacob.setTolerancia(this.tolerancia);
		jacob.setLimiteIntentos(this.limiteIntentos);
//		{0,0,0} diverge. {-0.9,2,2} llega a la raiz!. Todas las otras combinaciones con los números 2 divergen.
		jacob.setAproximacionInicial(new double[]{0.4, 0.4, -0.1});
//		jacob.setAproximacionInicial(new double[]{0,0,0});
		double[] x = jacob.calcular();
		System.out.println("Solucion final "+ Arrays.toString(x));
		assertTrue(Arrays.equals(x, new double[]{-1,2,2}));	//{-1,2,2} es raiz hallada con EliminacionGauseana
	}
	
	public void e2JERCICIO3() {
		double[][] a = {{ 4,-1, 1 },
						{ -1, 4.25, 2.75 },
						{ 1, 2.75, 3.5 }};
		double[] b = {1,1,1};
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
