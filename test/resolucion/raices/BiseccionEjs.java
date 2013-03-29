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
				System.out.println("--> f("+X+") = "+X);
				return X;
			}
			@Override
			public String desc() {
				return "f(x)=x, 0<=X<=1";
			}
		});
		double raiz = algoritmo.calcular();
		assertEquals(0, raiz, tolerancia);
	}
	
	@Test
	public void ejercicio2() {
		Biseccion algoritmo = new Biseccion();
		algoritmo.setLimiteMin(-0.5);
		algoritmo.setLimiteMax(1);
		algoritmo.setTolerancia(tolerancia);
		algoritmo.setFuncionEjercicio(new Funcion() {
			@Override
			public double calcular(double X) {
				System.out.println("--> f("+X+")="+X+" - sen(pi* "+X+" ))");
				return X - sin(PI * X);
			}
			@Override
			public String desc() {
				return "g(x)=x-sen(pi*x), 0<=X<=1";
			}
		});
		double raiz = algoritmo.calcular();
		assertEquals(0, raiz, tolerancia);
	}
	
	@Test
	public void ejercicio3() {
		Biseccion algoritmo = new Biseccion();
		algoritmo.setLimiteMin(-0.5);
		algoritmo.setLimiteMax(1);
		algoritmo.setTolerancia(tolerancia);
		algoritmo.setFuncionEjercicio(new Funcion() {
			@Override
			public double calcular(double X) {
				System.out.println("--> f("+X+")= cos (PI * "+X+") -"+X+" + (1/"+X+")");
				return cos (PI * X) -X + (1/X);
			}
			@Override
			public String desc() {
				return "f(X)= cos (PI * X) -X + (1/X)";
			}
		});
		double raiz = algoritmo.calcular();
		assertEquals(0, raiz, tolerancia);
	}

	@Test
	public void ejercicio4() {
		Biseccion algoritmo = new Biseccion();
		algoritmo.setLimiteMin(-1.5);
		algoritmo.setLimiteMax(-0.5);
		algoritmo.setTolerancia(tolerancia);
		algoritmo.setFuncionEjercicio(new Funcion() {
			@Override
			public double calcular(double X) {
				System.out.println("--> f("+X+")= "+X+"^3 - "+X+"^2 + 2."+X+" + 5");
				return X*X*X - X*X + 2* X + 5;
			}
			@Override
			public String desc() {
				return "f(X)= X^3 - X^2 + 2.X + 5";
			}
		});
		double raiz = algoritmo.calcular();
		assertEquals(0, raiz, tolerancia);
	}
}
