package aproximacion.interpolacion;

import org.junit.Test;

public class LagrangeEjs {

	@Test
	public void ejercicio1() {
		double x[] = {0, 0.5, 1, 1.5, 2};
		double f[] = {1, 0.938470, 0.765198, 0.511828, 0.223891};
		double d = 0.9;
		Lagrange lag = new Lagrange();
		lag.setValoresXConocidos(x);
		lag.setValoresFxConocidos(f);
		double result = lag.calcular(d);
		System.out.println("Valor aproximado de f("+d+"): " + result);
	}

}
