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

		System.out.print("Min: ");
		double raiz = this.funcEjercicio.calcular(this.limiteMin);
		double medio = -1;
		
		if(abs(raiz) > this.tolerancia) {
		
			System.out.print("Max: ");
			raiz = this.funcEjercicio.calcular(this.limiteMax);
			
			if(abs(raiz) > this.tolerancia) {
			
				System.out.print("Med: ");
				medio = (this.limiteMin+this.limiteMax)/2;
				raiz = this.funcEjercicio.calcular(medio);
				
				while(abs(raiz) > tolerancia) {
					
					System.out.println("Nueva iteracion---------------");
					System.out.print("Min: ");
					if (raiz * this.funcEjercicio.calcular(this.limiteMin) > 0) { //mismo signo
						//Si tienen mismo signo no cumplo aún el teorema del valor medio.
						this.limiteMin = medio;
					} else {	//distinto signo
						this.limiteMax = medio;
					}
					
					medio = (this.limiteMin+this.limiteMax)/2;
					System.out.println("nuevo-min: "  + this.limiteMin + ", nuevo-Med: " + medio +", nuevo-Max: "+this.limiteMax);
					
					System.out.print("Med: ");
					raiz = this.funcEjercicio.calcular(medio);
				}
			}
		}
		System.out.println("Resultado=" + medio);
		return raiz;
	}
	
	private void validarCompletitud() {
		if (this.tolerancia == null || this.limiteMax == null
				|| this.limiteMin == null || this.funcEjercicio == null) {
			throw new Incompleto("Algun valor vino en null");
		}
	}
}
