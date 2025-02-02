package resolucion.raices;

import excepciones.Incompleto;
import interfaces.Algoritmo;
import interfaces.Funcion;
import static java.lang.Math.*;

/** <b>Algoritmo de Bisección</b>
	Para hallar raices de la "funcEjercicio" usando el teorema del valor intermedio y el Qsort.
	(si f(a)>0 y f(b)<0 entonces hay un punto en [a,b] donde f(x)=0)
*/
public class Biseccion implements Algoritmo{
	private Double tolerancia;
	private Double limiteMin;
	private Double limiteMax;
	private Funcion funcEjercicio;

	@Override
	public void setTolerancia(double tol) {
		this.tolerancia = tol;
	}
	@Override
	public void setFuncionEjercicio(Funcion func) {
		this.funcEjercicio = func;
	}
	public void setLimiteMin(double limiteMin) {
		this.limiteMin = limiteMin;
	}
	public void setLimiteMax(double limiteMax) {
		this.limiteMax = limiteMax;
	}
	
	@Override
	public String toString() {
		return "Biseccion min=" + limiteMin + ", max=" + limiteMax + ", tolerancia=" + tolerancia + ", funcion " + funcEjercicio.desc();
	}
	
	@Override
	public double calcular() {
		validarCompletitud();
		double medio = (this.limiteMin+this.limiteMax)/2;
		double resultadoFuncion = this.funcEjercicio.calcular(this.limiteMin);
		System.out.println("f(min) = f("+this.limiteMin+") = "+resultadoFuncion);
		if(abs(resultadoFuncion) > this.tolerancia) {
			resultadoFuncion = this.funcEjercicio.calcular(this.limiteMax);
			System.out.println("f(Max) = f("+this.limiteMax+") = "+resultadoFuncion);
			if(abs(resultadoFuncion) > this.tolerancia) {
				medio = (this.limiteMin+this.limiteMax)/2;
				resultadoFuncion = this.funcEjercicio.calcular(medio);
				while(abs(resultadoFuncion) > tolerancia) {
					System.out.println("f(med) = f("+medio+") = "+resultadoFuncion);
					if (resultadoFuncion * this.funcEjercicio.calcular(this.limiteMin) > 0) { //mismo signo
						//Si tienen mismo signo no cumplo aún el teorema del valor medio.
						this.limiteMin = medio;
					} else {	//distinto signo
						this.limiteMax = medio;
					}
					System.out.println("nuevo-min: "+this.limiteMin+". nuevo-Max: "+this.limiteMax);
					medio = (this.limiteMin+this.limiteMax)/2;
					resultadoFuncion = this.funcEjercicio.calcular(medio);
				}
			}
		}
		System.out.println("raiz: "+medio);
		return resultadoFuncion;
	}
	
	private void validarCompletitud() {
		if (this.tolerancia == null || this.limiteMax == null
				|| this.limiteMin == null || this.funcEjercicio == null) {
			throw new Incompleto("Algun valor vino en null");
		}
	}
}
