package aproximacion.cuadradosminimos;

import org.junit.Test;

public class CMPolinomioG1Ejs {

	@Test
	public void ejercicio1() {
		double x[] = {1, 2, 3, 4};
		double f[] = {1, 5, 8, 14};
		double d = 2.5;
		CMPolinomioG1 cmG1 = new CMPolinomioG1();
		cmG1.setValoresXConocidos(x);
		cmG1.setValoresFxConocidos(f);
		double result = cmG1.calcular(d);
		System.out.println("Valor aproximado de f("+d+"): " + result);
	}
	
	@Test
	public void ejercicio2() {	//para comparar con lagrange, aunque son polinomios distintos
		double x[] = {0, 0.5, 1, 1.5, 2};
		double f[] = {1, 0.938470, 0.765198, 0.511828, 0.223891};
		double d = 0.9;
		CMPolinomioG1 cmG1 = new CMPolinomioG1();
		cmG1.setValoresXConocidos(x);
		cmG1.setValoresFxConocidos(f);
		double result = cmG1.calcular(d);
		System.out.println("Valor aproximado de f("+d+"): " + result);
	}

}
