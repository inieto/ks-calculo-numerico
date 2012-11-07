package algoritmos.puntuales;

import static org.junit.Assert.*;
import static java.lang.Math.*;
import interfaces.FuncionEjercicio;

import org.junit.Test;

public class BiseccionEjs {
	private double tolerancia = 0.001;

	@Test
	public void ejercicio1() {
		Biseccion algoritmo = new Biseccion();
		algoritmo.setLimiteMin(-0.5);
		algoritmo.setLimiteMax(1);
		algoritmo.setTolerancia(tolerancia);
		algoritmo.setFuncionEjercicio(new FuncionEjercicio() {
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
		algoritmo.setFuncionEjercicio(new FuncionEjercicio() {
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

}
