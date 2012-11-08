package aproximacion.interpolacion;

import java.util.Arrays;

import excepciones.Incompleto;
import interfaces.Algoritmo;
import interfaces.Funcion;
import static java.lang.Math.*;

/** <b>Generador de Polinomios interpoladores de Lagrange</b>
	Hasta acá se aproximaban raices escalares o soluciones vectoriales a sistemas de ecuaciones.
	
	Ahora se aproximan funciones entre intervalos. La idea es: hay una función f(x) que puede conocerse o no
	(es decir, puede venir la fórmula ó un conjunto de puntos Xo...Xn con sus f(Xo)...f(Xn)) y hay que hallar
	un polinomio que de por resultado una aproximación a esta función
	
	Copiar de carpeta la definición del polinómio (pag12)
	P(x) = sum( f(X) . Lnk(X)) con Lnk(X)=prod( (X-Xi)/(Xk-Xi) )
	y luego el polinomio devuelto por este algoritmo 
	
	Este algoritmo pretende devolver a la salida un String para copiar y pegar en un graficador, tipo Octave.
	También permite probar P(Xo)...P(Xn) que den iguales aproximados a f(Xo)...f(Xn)
*/
public class Lagrange {
	private double x[];		//puntos Xo...Xn conocidos
	private double f[];		//puntos f(Xo)...f(Xn) conocidos

	public void setValoresXConocidos(double[] x) {
		this.x = x;
	}
	public void setValoresFxConocidos(double[] f) {
		this.f = f;
	}
	
	public double calcular(double d) {
		this.validarCompletitud();
		int n = this.x.length;
		System.out.println("Hay "+n+" pares [x,f(X)] conocidos.");
		System.out.println("X:    "+Arrays.toString(this.x));
		System.out.println("f(X): "+Arrays.toString(this.f));
		System.out.println("Se generará un polinomio de grado "+(n-1)+" con "+n+" términos");
		
		double resultado = 0;	//neutro de la suma
		StringBuilder sbResultado = new StringBuilder("P(X) = ");
		for (int i=0; i < n; i++) {
			//Primero armo la productoria y luego se la multiplico en la sumatoria.
			double productoria = 1;	//neutro de la multiplicación
			StringBuilder sbProductoria = new StringBuilder();
			for (int j=0; j < n; j++) {
				if(j==i) continue;	//salvar el caso i=j según carpeta
				productoria *= (d - x[j]) / (x[i] - x[j]);
				sbProductoria.append(" * ( (X - "+x[j]+") / ("+x[i]+" - "+x[j]+") )");
			}
			resultado += f[i] * productoria;
			sbResultado.append(f[i]).append(sbProductoria);
			if(i < n-1) sbResultado.append(" + \n");
		}
		System.out.println(sbResultado);
		return resultado;
	}
	
	private void validarCompletitud() {
		if (this.x == null || this.f == null || this.x.length != this.f.length) {
			throw new Incompleto("Algun valor vino en null o tienen distinto tamaño");
		}
	}
}