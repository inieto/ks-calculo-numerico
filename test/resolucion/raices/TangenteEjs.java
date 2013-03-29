package resolucion.raices;

import static org.junit.Assert.*;
import static java.lang.Math.*;
import interfaces.Funcion;

import org.junit.Test;

import resolucion.raices.Tangente;

public class TangenteEjs {
	private double tolerancia = 0.001;

	@Test
	public void ejercicio1() {
		Tangente algoritmo = new Tangente();
		algoritmo.setTolerancia(tolerancia);
		algoritmo.setAproxInicial(PI/4);
		
		algoritmo.setFuncionEjercicio(new Funcion() {
			@Override
			public double calcular(double X) {
				double resultado = cos(X)-X;
				System.out.println("--> f("+X+") = cos("+X+") - "+X+" = "+resultado);
				return resultado;
			}
			@Override
			public String desc() {
				return "f(x)=cos(x)-x, PI/4<=X<=PI/2";
			}
		});
		
		algoritmo.setFuncionDerivada(new Funcion() {
			@Override
			public double calcular(double X) {
				double resultado = -sin(X)-1;
				System.out.println("--> f'("+X+") = -sin("+X+") - 1 = "+resultado);
				return resultado;
			}
			@Override
			public String desc() {
				return "f'(x)=-sin(x)-1, PI/4<=X<=PI/2";
			}
		});
		
		System.out.println(algoritmo);
		double raiz = algoritmo.calcular();
		assertEquals(0.73908513, raiz, tolerancia);
	}

	
	@Test
	public void ejercicio2() {
		Tangente algoritmo = new Tangente();
		algoritmo.setTolerancia(tolerancia);
		algoritmo.setAproxInicial(-1);
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
		algoritmo.setFuncionDerivada(new Funcion() {
			@Override
			public double calcular(double X) {
				System.out.println("--> f("+X+")= 3*"+X+"^2 - 2*"+X+" + 2");
				return 3*X*X - 2*X + 2;
			}
			@Override
			public String desc() {
				return "f(X)= 3*X^2 - 2*X + 2";
			}
		});
		
		double raiz = algoritmo.calcular();
		assertEquals(-1.1324936902897467, raiz, tolerancia);
	}
}

