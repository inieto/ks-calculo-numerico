package cuadraturas;

import static java.lang.Math.abs;
import interfaces.Funcion;

import java.util.Arrays;

import excepciones.Incompleto;

/** <b>Polinomio de Grado 1 de Trapecio</b>
	Aproxima de VALORES de Integrales (usando una recta). 
	Es menos preciso que Simpson porque usa una recta y solo 2 pares [x,f(x)]
	de ejecutar este algoritmo.
	 
	La idea es: hay una función f(x) que no se le puede sacar la integral (definida en un intervalo)
	Pero se pueden aproximar su integral usando este algoritmo.
	HACE UN PROMEDIO CUALQUIERA DE MIERDA... JAJA
*/
public class TrapecioG1 {
	private Double limiteMin;
	private Double limiteMax;
	private Funcion funcEjercicio;

	public void setFuncionEjercicio(Funcion func) {
		this.funcEjercicio = func;
	}
	public void setLimiteMin(double limiteMin) {
		this.limiteMin = limiteMin;
	}
	public void setLimiteMax(double limiteMax) {
		this.limiteMax = limiteMax;
	}
	
	public double calcular() {
		this.validarCompletitud();
		double[] x = new double[] { this.limiteMin, this.limiteMax };
		double[] f = new double[] { this.funcEjercicio.calcular(x[0]), this.funcEjercicio.calcular(x[1])};
		
		System.out.println("X:    "+Arrays.toString(x));
		System.out.println(this.funcEjercicio.desc());
		System.out.println("f(X): "+Arrays.toString(f));
		System.out.println("Se calculará la integral aproximada en el intervalo X por Trapecio");
		System.out.println("I1(f) en ["+x[0]+","+x[1]+"] = ((x1-x0)/2) * ( f(x0) + f(x1))");
		System.out.println("I1(f) en ["+x[0]+","+x[1]+"] = ("+(x[1]-x[0])+"/2) * ( "+f[0]+" + "+f[1]+")");
		double resultado = ((x[1] - x[0])/2) * (f[0] + f[1]);
		System.out.println("I1(f) en ["+x[0]+","+x[1]+"] = " + resultado);
		return resultado;
	}
	
	private void validarCompletitud() {
		if (this.limiteMax == null || this.limiteMin == null
				|| this.funcEjercicio == null) {
			throw new Incompleto("Algun valor vino en null");
		}
	}
}