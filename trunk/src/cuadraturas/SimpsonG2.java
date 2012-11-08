package cuadraturas;

import static java.lang.Math.abs;
import interfaces.Funcion;

import java.util.Arrays;

import excepciones.Incompleto;

/** <b>Polinomio de Grado 2 de Simpson</b>
	Aproxima de VALORES de Integrales (usando el polinomio interpolador de lagrange). Valores y no fórmulas 
	Es más preciso que Trapecio porque usa 3 pares [x,f(x)]
	de ejecutar este algoritmo.
	 
	La idea es: hay una función f(x) que no se le puede sacar la integral (definida en un intervalo)
	Pero se pueden aproximar su integral usando este algoritmo.
	
	La CAGADA con este método es que como hay que reemplazar y e integrar a mano los polinomios 
	de lagrange no se puede hacer un algoritmo genérico para un vector x cualquiera.
	Hay que hacer un algoritmo por cada grado del polinomio (y cada vector x).
	Este sirve para un conjunto de 3 valores.
*/
public class SimpsonG2 {
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
		double[] x = new double[] { this.limiteMin, (this.limiteMin + this.limiteMax) / 2, this.limiteMax };
		double[] f = new double[] { this.funcEjercicio.calcular(x[0]),
				this.funcEjercicio.calcular(x[1]), this.funcEjercicio.calcular(x[2]) };
		
		System.out.println("X:    "+Arrays.toString(x));
		System.out.println(this.funcEjercicio.desc());
		System.out.println("f(X): "+Arrays.toString(f));
		System.out.println("Se calculará la integral aproximada en el intervalo X por Simpson");
		double h = abs(x[0] - x[1]);
		System.out.println("I(f) en ["+x[0]+","+x[2]+"] = (h/3) * ( f(x0) + 4.f(x1) + f(x2) )");
		System.out.println("I(f) en ["+x[0]+","+x[2]+"] = ("+h+"/3) * ( "+f[0]+" + 4."+f[1]+" + "+f[2]+" )");
		double resultado = (h/3) * ( f[0] + 4 * f[1] + f[2] );
		System.out.println("I(f) en ["+x[0]+","+x[2]+"] = " + resultado);
		return resultado;
	}
	
	private void validarCompletitud() {
		if (this.limiteMax == null || this.limiteMin == null
				|| this.funcEjercicio == null) {
			throw new Incompleto("Algun valor vino en null");
		}
	}
}