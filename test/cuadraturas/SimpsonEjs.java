package cuadraturas;

import interfaces.Funcion;

import org.junit.Test;
import static java.lang.Math.*;

public class SimpsonEjs {

	@Test
	public void ejercicio1() {
		double x[] = {0, 1};
		double d = 2.5;
		SimpsonG2 sG2 = new SimpsonG2();
		sG2.setLimiteMin(x[0]);
		sG2.setLimiteMax(x[1]);
		sG2.setFuncionEjercicio(new Funcion() {
			
			@Override
			public String desc() {
				return "--> f(x) = (1 / sqrt(2*PI)) * e^ (-(x^2)/2)";
			}
			
			@Override
			public double calcular(double x) {
				System.out.println("f("+x+") = (1 / sqrt(2*PI)) * e^(-"+(x*x)+"/2)");
				return pow( E, -x*x/2 ) / sqrt(2*PI);
			}
		});
		double result = sG2.calcular();
		System.out.println("Valor aproximado de I(f): " + result);
	}
	
}
