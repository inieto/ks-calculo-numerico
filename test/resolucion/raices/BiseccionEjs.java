package resolucion.raices;

import static org.junit.Assert.*;
import static java.lang.Math.*;
import interfaces.Funcion;

import org.junit.Test;

import resolucion.raices.Biseccion;

public class BiseccionEjs {
	private double tolerancia = 0.001;

	@Test
	public void ejercicio1() {
		Biseccion algoritmo = new Biseccion();
		algoritmo.setLimiteMin(-0.5);
		algoritmo.setLimiteMax(1);
		algoritmo.setTolerancia(tolerancia);
		algoritmo.setFuncionEjercicio(new Funcion() {
			@Override
			public double calcular(double X) {
				double result = X;
				System.out.println("f("+X+")=" + result);
				return result;				
				
			}
			@Override
			public String desc() {
				return "f(x)=x";
			}
		});
		
		System.out.println(algoritmo);
		double raiz = algoritmo.calcular();
		assertEquals(0, raiz, tolerancia);
	}

	@Test
	public void ejercicio2() {
		Biseccion algoritmo = new Biseccion();
		algoritmo.setLimiteMin(-1);
		algoritmo.setLimiteMax(-0.5);
		algoritmo.setTolerancia(tolerancia);
		algoritmo.setFuncionEjercicio(new Funcion() {
			@Override
			public double calcular(double X) {
				double result = X - sin(PI * X);
				System.out.println("f("+X+")=" + result);
				return result;
			}
			@Override
			public String desc() {
				return "g(x)=x-sen(pi*x)";
			}
		});
		
		System.out.println(algoritmo);
		double raiz = algoritmo.calcular();
		assertEquals(0, raiz, tolerancia);
	}
	
	@Test
	public void ejercicio4() {
		Biseccion algoritmo = new Biseccion();
		algoritmo.setLimiteMin(-5);
		algoritmo.setLimiteMax(0);
		algoritmo.setTolerancia(tolerancia);
		algoritmo.setFuncionEjercicio(new Funcion() {
			@Override
			public double calcular(double X) {
				double result = pow(X, 2) - 1;
				System.out.println("f("+X+")=" + result);
				return result;
			}
			@Override
			public String desc() {
				return "g(x)=pow(X, 2) - 1";
			}
		});
		
		System.out.println(algoritmo);
		double raiz = algoritmo.calcular();
		assertEquals(0, raiz, tolerancia);
	}
		
}
