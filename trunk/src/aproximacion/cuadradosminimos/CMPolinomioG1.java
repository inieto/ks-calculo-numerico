package aproximacion.cuadradosminimos;

import java.util.Arrays;

import excepciones.Incompleto;
import interfaces.Algoritmo;
import interfaces.Funcion;
import static java.lang.Math.*;

/** <b>Polinomio de Grado 2 por el Método de los Cuadrados Mínimos</b>
	Aproxima funciones entre intervalos. La idea es: hay una función f(x) que puede conocerse o no
	(es decir, puede venir la fórmula ó un conjunto de puntos Xo...Xn con sus f(Xo)...f(Xn)) y hay que hallar
	un polinomio que de por resultado una aproximación a esta función.
	
	La CAGADA con este método es que como hay que derivar a mano fórmulas no se puede hacer un algoritmo general
	Hay que hacer un algoritmo por cada grado del polinomio.
	
	Este algoritmo pretende devolver a la salida un String para copiar y pegar en un graficador, tipo Octave.
	También permite probar P(Xo)...P(Xn) que den iguales aproximados a f(Xo)...f(Xn)
*/
public class CMPolinomioG1 {
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
		System.out.println("Se generará un polinomio de aproximación de Grado1 del tipo f(x)=a.x+b por cuadrados mínimos");
		
		double sumYiXi = this.sumarizarVector(this.multVectores(this.f,this.x));
		double sumXi = this.sumarizarVector(this.x);
		double sumYi = this.sumarizarVector(this.f);
		double sumXiXi = this.sumarizarVector(this.multVectores(this.x,this.x));
		
		System.out.println("sum(Yi.Xi) :" + sumYiXi);
		System.out.println("sum(Xi) :" + sumXi);
		System.out.println("sum(Yi) :" + sumYi);
		System.out.println("sum(Xi^2) :" + sumXiXi);
		
		double a = (n * sumYiXi - sumXi * sumYi ) / (n * sumXiXi - sumXi*sumXi);
		double b = (sumYi * sumXiXi - sumXi * sumYiXi) / (n * sumXiXi - sumXi*sumXi);
		
		System.out.println("f(X) = "+a+" * X + "+b);
		double resultado = a*d+b;
		System.out.println("f("+d+") = "+a+" * "+d+" + "+b+" = "+ resultado);
		
		return resultado;
	}
	
	private double[] multVectores(double[] a, double[] b) {
		double[] resultado = new double[a.length];
		for(int i=0; i<a.length; i++)
			resultado[i] = a[i] * b[i];
		return resultado;
	}
	
	private double sumarizarVector(double[] a) {
		double resultado = 0;
		for(int i=0; i<a.length; i++)
			resultado += a[i];
		return resultado;
	}
	
	private void validarCompletitud() {
		if (this.x == null || this.f == null || this.x.length != this.f.length) {
			throw new Incompleto("Algun valor vino en null o tienen distinto tamaño");
		}
	}
}