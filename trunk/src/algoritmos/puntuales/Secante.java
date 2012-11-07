package algoritmos.puntuales;

import excepciones.Incompleto;
import interfaces.Algoritmo;
import interfaces.Funcion;
import static java.lang.Math.*;

/** <b>Algoritmo de la Secante</b>
	El algoritmo de la tangente falla si f'(Xo) se vuelve 0, por la división por 0. Entonces reemplaza f'(Xo) por:
	f'(Xo) = ( f(X)-f(Xo) ) / ( X-Xo ) //cociente incremental. Queda:
	Raiz = X1 - (X1-Xo) . ___f(X1)___ 
						  f(X1)-f(Xo)
*/
public class Secante implements Algoritmo{
	private Double tolerancia;
	private Double aproxInicial0;
	private Double aproxInicial1;
	private Funcion funcEjercicio;

	@Override
	public void setTolerancia(double tol) {
		this.tolerancia = tol;
	}
	
	public void setAproxInicial0(double aprox) {
		this.aproxInicial0 = aprox;
	}
	
	public void setAproxInicial1(double aprox) {
		this.aproxInicial1 = aprox;
	}
	
	@Override
	public void setFuncionEjercicio(Funcion func) {
		this.funcEjercicio = func;
	}
	@Override
	public double calcular() {
		validarCompletitud();
		double x0 = this.aproxInicial0;
		double x1 = this.aproxInicial1;
		double raiz = x1 - ( (x1-x0) * funcEjercicio.calcular(x1) )	//Raiz = X1 - (X1-Xo) . ___f(X1)___ 
				/ ( funcEjercicio.calcular(x1)-funcEjercicio.calcular(x0) ); //				f(X1)-f(Xo)
		System.out.println("Aprox. Xo: "+x0+". X1: "+x1+". X: "+raiz);
		while(abs(raiz-x1) > this.tolerancia) {
			x0 = x1;
			x1 = raiz;
			raiz = x1 - ( (x1-x0) * funcEjercicio.calcular(x1) ) 
					/ ( funcEjercicio.calcular(x1)-funcEjercicio.calcular(x0) );
			System.out.println("Aprox. Xo: "+x0+". X1: "+x1+". X: "+raiz);
		}
		System.out.println("raiz: "+raiz);
		return raiz;
	}
	
	private void validarCompletitud() {
		if (this.tolerancia == null || this.funcEjercicio == null
				|| this.aproxInicial0 == null || this.aproxInicial1 == null) {
			throw new Incompleto("Algun valor vino en null");
		}
	}
}
