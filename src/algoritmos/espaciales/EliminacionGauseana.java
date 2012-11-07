package algoritmos.espaciales;

import interfaces.AlgoritmoMatricial;
import excepciones.Incompleto;
import static java.lang.Math.*;

/** <b>Algoritmo de Eliminación Gauseana</b>
	Este algoritmo busca hallar las x1...xn (osea X) que cumplan A . X = B (matrices)
	con A(nxn), X(n) y B(n). Si A no es cuadrada o alguna de sus filas es combinación lineal de otras, 
	entonces el sistema está mal condicionado y hay infinitas soluciones
*/
public class EliminacionGauseana implements AlgoritmoMatricial{
	private double[][] a;
	private double  [] b;
	private int n;

	@Override
	public void setMatriz(double[][] a, double[] b) {
		this.a = a;
		this.b = b;
		this.n = b.length;	//averiguo "n" que vale para A, X y B
		if(a.length != b.length) 
			throw new RuntimeException("Sistema mal condicionado:"+a.length+"x"+b.length);
	}
	@Override
	public double[] calcular() {
		validarCompletitud();
		
		for (int p = 0; p < this.n; p++) {		//por cada fila..
			this.subirFilaMasGrande(p);		//Algoritmo paso-1: A[p][i] =  max(i<j<n)  A[j][i]
			this.validarMalCondicionado(p);	//Si A[p][i] == 0 STOP
			this.triangulizarColumna(p);
		}
		return hallarVectorX();
	}
	
	/** Algoritmo paso 1: Pivotea la fila mas trande arriba, a partir de la fila "p"
	 */
	private void subirFilaMasGrande(int p) {
		//la fila con el elemento max para esa columna, subirá al lugar P
		int max = p;
		for (int i = p + 1; i < b.length; i++) {
			if (abs(this.a[i][p]) > abs(this.a[max][p])) {
				max = i;
			}
		}
		double[]temp= this.a[p]; this.a[p] = this.a[max]; this.a[max] = temp;
		double	t	= this.b[p]; this.b[p] = this.b[max]; this.b[max] = t;
	}
	
	/** Algoritmo paso 2 dice: Si |A[p][i]| == 0 STOP
	 * 0 es ideal, porque en las divisiones de double quedan los decimales.
	 * Sistema mal condicionado => infinitas soluciones
	 */
	private void validarMalCondicionado(int p) {
		if (abs(this.a[p][p]) <= 0.000001) {
			throw new RuntimeException("Sistema mal condicionado");
		}
	}
	
	/** Algoritmo paso 3: Tengo que llevar a 0 el resto de las "p" filas hacia abajo, de dicha columna
	 */
	private void triangulizarColumna(int p) {
		for (int i = p + 1; i < this.n; i++) {	//Al resto de las filas de esa columna las llevo a 0 para triangular la matriz...
			double mIJ = this.a[i][p] / this.a[p][p];
			this.b[i] -= mIJ * this.b[p];
			for (int j = p; j < this.n; j++) {
				this.a[i][j] -= mIJ * this.a[p][j];
			}
		}
	}
	
	/** Algoritmo paso 7: Despeja las X con la matriz ya triangular
	 */
	private double[] hallarVectorX() {
		double[] x = new double[this.n];
		for (int i = this.n - 1; i >= 0; i--) {
			double sum = 0.0;
			for (int j = i + 1; j < this.n; j++) {
				sum += this.a[i][j] * x[j];
			}
			x[i] = (this.b[i] - sum) / this.a[i][i];
		}
		return x;
	}
	
	private void validarCompletitud() {
		if (this.a == null) {
			throw new Incompleto("Algun valor vino en null");
		}
	}
}
