package resolucion.raices;

import static org.junit.Assert.*;
import static java.lang.Math.*;
import interfaces.Funcion;

import org.junit.Test;

import resolucion.raices.PuntoFijo;




public class PuntoFijoEjs {
	private double tolerancia = 0.01;

	@Test
	public void ejercicio1() {
		PuntoFijo algoritmo = new PuntoFijo();
		algoritmo.setTolerancia(tolerancia);
		algoritmo.setAproxInicial(0);
		algoritmo.setFuncionEjercicio(new Funcion() {
			@Override
			public double calcular(double X) {
				double resultado = (pow(X,2) -1)/3;
				System.out.println("--> g("+X+") = ("+X+"^2-1)/3 = "+resultado);
				return resultado;
			}
			@Override
			public String desc() {
				return "g(x)=(x^2-1)/3, -1<=X<=1";
			}
		});
		
		double ptoFijo = algoritmo.calcular();
		assertEquals(-0.3, ptoFijo, tolerancia);
	}
}
