package algoritmos.puntuales;

import excepciones.Incompleto;
import interfaces.Algoritmo;
import interfaces.FuncionEjercicio;
import static java.lang.Math.*;

/** <b>Algoritmo del Punto Fijo</b>
	Para hallar valores de la "funcEjercicio" donde f(X) = X. En esos casos, f(X) se dice "predictor".
	Si f(X) es continua y existe en todo [a,b] para todo X de [a,b] entonces tiene al menos un punto fijo.
	Osea, como el f(X) da valores entre [a,b] y X va entre [a,b], en algun punto se cruzan y f(X)=X

	Si f(a) > a y f(b) < b (los puntos límites) construyo g(X)=f(X)-X tal que: 
	[g(a) = f(a) - a] > 0 (por f(a)>a) \_ situación del valor intermedio para g(X) en [a,b]
	[g(b) = f(b) - b] < 0 (por f(b)<b) /  por lo que al hallar g(X) = 0 tengo el X que hace f(X)=X
	Para este caso, hay que aplicar Bisección para g(X)=f(X)-X. 
	Hallar g(X)=0 encuentra las soluciones de f(X)=X

	Además, si |f'(X)| < 1, X en [a,b] entonces f(X) tiene un único punto fijo.
	Osea, si la derivada es decreciente entonces hay 1 solo punto fijo. Para este otro caso
	es el algoritmo de a continuacion:
*/
public class PuntoFijo implements Algoritmo{
	private Double tolerancia;
	private Double aproxInicial;
	private FuncionEjercicio funcEjercicio;

	@Override
	public void setTolerancia(double tol) {
		this.tolerancia = tol;
	}
	
	public void setAproxInicial(double aprox) {
		this.aproxInicial = aprox;
	}
	@Override
	public void setFuncionEjercicio(FuncionEjercicio func) {
		this.funcEjercicio = func;
	}
	
	@Override
	public double calcular() {
		validarCompletitud();
		double resultadoAnterior = this.aproxInicial;
		double ptoFijo = this.funcEjercicio.calcular(resultadoAnterior);
		System.out.println("Aprox. inicial: "+resultadoAnterior+". actual:"+ptoFijo);
		while(abs(ptoFijo-resultadoAnterior) > this.tolerancia) {
			resultadoAnterior = ptoFijo;
			ptoFijo = this.funcEjercicio.calcular(resultadoAnterior);
			System.out.println("Aprox. anterior: "+resultadoAnterior+". actual:"+ptoFijo);
		}
		System.out.println("ptoFijo: "+ptoFijo);
		return ptoFijo;
	}
	
	private void validarCompletitud() {
		if(this.tolerancia == null || this.funcEjercicio == null) {
			throw new Incompleto("Algun valor vino en null");
		}
	}
}
