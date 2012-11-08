package resolucion.raices;

import excepciones.Incompleto;
import interfaces.Algoritmo;
import interfaces.Funcion;
import static java.lang.Math.*;

/** <b>Algoritmo de la Tangente</b>
	Si f(X) es contínua en [a,b] y diferenciable 2 veces, entonces puedo aplicar este algoritmo.
	El algoritmo se basa en el desarrollo de Taylor de orden 2 (el desarrollo de orden 1 es igual a la fórmula de la tangente)
	Taylor(2): f(X) = f(Xo) + (X-Xo) . f'(Xo), X variable y Xo aproximación_incial.
	Cuando X es raiz, resulta f(X)=0. Entonces 0 = f(Xo) + (X-Xo) . f'(Xo). Despejo X = Xo - f(Xo)/f'(Xo)
	Esto resulta ser convergente por la naturaleza de las derivadas. 
	El algoritmo itera por fuerza bruta los valores de X hasta que las diferencias 
	entre el actual y anterior sea menor a la tolerancia
*/
public class Tangente implements Algoritmo{
	private Double tolerancia;
	private Double aproxInicial;
	private Funcion funcEjercicio;
	private Funcion funcDerivada;

	@Override
	public void setTolerancia(double tol) {
		this.tolerancia = tol;
	}
	
	public void setAproxInicial(double aprox) {
		this.aproxInicial = aprox;
	}
	@Override
	public void setFuncionEjercicio(Funcion func) {
		this.funcEjercicio = func;
	}
	
	public void setFuncionDerivada(Funcion derivada) {
		this.funcDerivada = derivada;
	}
	
	@Override
	public double calcular() {
		validarCompletitud();
		double resultadoAnterior = this.aproxInicial;
		double raiz = resultadoAnterior		// X = Xo - f(Xo)/f'(Xo)
				- this.funcEjercicio.calcular(resultadoAnterior)
				/ this.funcDerivada.calcular(resultadoAnterior);
		System.out.println("Aprox. inicial: "+resultadoAnterior+". actual:"+raiz);
		while(abs(raiz-resultadoAnterior) > this.tolerancia) {
			resultadoAnterior = raiz;
			raiz = resultadoAnterior		// X = Xo - f(Xo)/f'(Xo)
					- this.funcEjercicio.calcular(resultadoAnterior)
					/ this.funcDerivada.calcular(resultadoAnterior);
			System.out.println("Aprox. anterior: "+resultadoAnterior+". actual:"+raiz);
		}
		System.out.println("raiz: "+raiz);
		return raiz;
	}
	
	private void validarCompletitud() {
		if (this.tolerancia == null || this.funcEjercicio == null
				|| this.funcDerivada == null || this.aproxInicial == null) {
			throw new Incompleto("Algun valor vino en null");
		}
	}
}
