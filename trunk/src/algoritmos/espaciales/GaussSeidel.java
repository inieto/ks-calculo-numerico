package algoritmos.espaciales;

import interfaces.AlgoritmoMatricial;

/** <b>Algoritmo de aproximación de GaussSeidel</b>
	Este algoritmo busca aproximar las x1...xn (osea X) que cumplan A . X = B (matrices) con A(nxn), X(n) y B(n).
	Es una mejora del Jacobi, sobre el método de iteración para que vaya usando resultados parciales
	Así logra menos iteraciones para llegar al mismo resultado.
	
	Tiene las mismas limitaciones/condiciones que Jacobi.
*/
public class GaussSeidel extends Jacobi implements AlgoritmoMatricial{
	
	/**	Usa el valor calculado x1[i][j-1] para calcular X1[j] en lugar de usar X0[j] para lograr resultados más rápidos
	 * Osea, si la aprox inicial es X0={0,0,0} y va calculando X1={1,...} usa ese "1" hallado en la iteración anterior
	 * en lugar de usar el "0" de X0 que hubiera usado en Jacobi*/
	@Override
	protected double[] iterar(double[] x0) {
		double[] x1 = new double[this.n];
		double[][] t = this.a;
		double  [] c = this.b;
		for (int i = 0; i < this.n; i++) {
			for(int j = 0; j < i; j++) {	//Recién entra a partir de i=1, habiendo calculado X1[0]
				x1[i] += (t[i][j] * x1[j]);	//Le sumo los pre-calculados de X1
			}
			for (int j = i; j < this.n; j++) {	//para el resto que aún no se calculó,
				x1[i] += (t[i][j] * x0[j]); 	//le sumo los de la iteración anterior X0
			}
			x1[i] += c[i]; 
		}
		//System.out.println("Hola! Soy GaussSeidel!");
		return x1;
	}
}
