package resolucion.raices;

import static org.junit.Assert.*;
import static java.lang.Math.*;
import interfaces.Funcion;

import org.junit.Test;

import resolucion.raices.PuntoFijo;

public class PuntoFijoEjs {
	private double tolerancia = 0.0001;

	@Test
	public void ejercicio1() {
		PuntoFijo algoritmo = new PuntoFijo();
		algoritmo.setTolerancia(tolerancia);
		algoritmo.setAproxInicial(0);
		algoritmo.setFuncionEjercicio(new Funcion() {
			
			@Override
			public double calcular(double X) {
				double resultado = (pow(X,2) -1)/3;
				System.out.println("g("+X+") = ("+X+"^2-1)/3 = "+resultado);
				return resultado;
			}
			
			@Override
			public String desc() {
				return "g(x)=(x^2-1)/3, -1<=X<=1";
			}
		});
		
		System.out.println(algoritmo);
		double ptoFijo = algoritmo.calcular();
		assertEquals(-0.3, ptoFijo, tolerancia);
	}
	
	@Test
	public void ejercicio2() {
		PuntoFijo algoritmo = new PuntoFijo();
		algoritmo.setTolerancia(tolerancia);
		algoritmo.setAproxInicial(0);
		algoritmo.setFuncionEjercicio(new Funcion() {
			
			@Override
			public double calcular(double X) {
				double resultado = sqrt(10/(X+4));
				System.out.println("g("+X+") = sqrt(10/(" + X + "+4)) = " + resultado);
				return resultado;
			}
			
			@Override
			public String desc() {
				return "g(x)=(x^2-1)/3, -1<=X<=1";
			}
		});
		
		System.out.println(algoritmo);
		double ptoFijo = algoritmo.calcular();
		assertEquals(1.3652301, ptoFijo, tolerancia);
	}	
}
