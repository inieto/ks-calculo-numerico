package resolucion.raices;

import static org.junit.Assert.*;
import static java.lang.Math.*;
import interfaces.Funcion;

import org.junit.Test;

import resolucion.raices.Secante;

public class SecanteEjs {
	private double tolerancia = 0.001;

	@Test
	public void ejercicio1() {
		Secante algoritmo = new Secante();
		algoritmo.setTolerancia(tolerancia);
		algoritmo.setAproxInicial0(2);
		algoritmo.setAproxInicial1(-1);
		//Lo probé con (X0,X1): (0,PI/4), (0,2*PI), (PI/4 , 0.7395361335152383), (-1,2), (2,-1) y anda con todos!
		//0.7395361335152383 era la segunda aproximación del ej de la Tangente
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
		
		System.out.println(algoritmo);
		double raiz = algoritmo.calcular();
		assertEquals(0.73908513, raiz, tolerancia);
	}
}
