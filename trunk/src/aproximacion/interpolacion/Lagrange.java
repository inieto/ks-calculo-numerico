package aproximacion.interpolacion;

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
	
	public void calcular(){
		this.calcular(null);
	}
	
	public double calcular(Double d) {
		StringBuilder sb  = new StringBuilder("P(x) = ");
		StringBuilder sb2 = new StringBuilder("P(x) = ");
		String s = (d==null)?"X":String.valueOf(d);
		
		int n = x.length-1;
		double ft[] = (double[]) f.clone();
		for (int i=0; i<n; ++i) {
			for (int j=0; j<n-i; ++j) {
				ft[j] =   ft[j+1] * (d-x[j])     / (x[i+j+1]-x[j])
					+ ft[j]   * (d-x[i+j+1]) / (x[j]    -x[i+j+1]);
				sb.append("ft[j] = ft[j+1] * (d-x[j])   / (x[i+j+1]- x[j]) \n"
					+	  " 	 + ft[j] * (d-x[i+j+1]) / (x[j]    - x[i+j+1])");
				sb2.append("ft["+j+"] = ft["+(j+1)+"] * ("+s+"-x["+j+"])   / (x["+(i+j+1)+"i+j+1]- x["+j+"]) \n"
						+	  " 	 + ft["+j+"] * ("+s+"-x["+(i+j+1)+"]) / (x"+j+"j]    - x"+(i+j+1)+")");
			}
		}
		System.out.println(sb.toString());
		System.out.println(sb2.toString());
		return ft[0];
	}
	
	private void validarCompletitud() {
		if (this.x == null || this.f == null) {
			throw new Incompleto("Algun valor vino en null");
		}
	}
}