package algoritmos.puntuales;

import static org.junit.Assert.*;
import static java.lang.Math.*;
import interfaces.FuncionEjercicio;

import org.junit.Test;

public class BiseccionEjs {
	private double tolerancia = 0.001;

	@Test
	public void ejercicio1() {
		Biseccion bis = new Biseccion();
		bis.setLimiteMin(-0.5);
		bis.setLimiteMax(1);
		bis.setTolerancia(tolerancia);
		bis.setFuncionEjercicio(new FuncionEjercicio() {
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
		double raiz = bis.calcular();
		assertEquals(0, raiz, tolerancia);
	}
	
	@Test
	public void ejercicio2() {
		Biseccion bis = new Biseccion();
		bis.setLimiteMin(-0.5);
		bis.setLimiteMax(1);
		bis.setTolerancia(tolerancia);
		bis.setFuncionEjercicio(new FuncionEjercicio() {
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
		double raiz = bis.calcular();
		assertEquals(0, raiz, tolerancia);
	}

}
