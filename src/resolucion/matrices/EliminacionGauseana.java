package resolucion.matrices;

import java.util.Arrays;

import interfaces.AlgoritmoMatricial;
import excepciones.Incompleto;
import static java.lang.Math.*;

/** <b>Algoritmo de Eliminación Gauseana</b>
	Este algoritmo busca hallar las x1...xn (osea X) que cumplan A . X = B (matrices) con A(nxn), X(n) y B(n). 
	Lo logra triangulando inferiormente a A (osea, dejando todos 0s debajo de la diagonal ppal) y reemplazando luego.
	
	Si A no es cuadrada o alguna de sus filas es combinación lineal de otras, 
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
		
		for (int p = 0; p < this.n; p++) {	//por cada fila..
			this.subirFilaMasGrande(p);		//Algoritmo paso-1: A[p][i] =  max(i<j<n)  A[j][i]
			System.out.println("Reordeno: A:");
			imprimirMatriz(a);
			System.out.println("Reordeno: B:");
			imprimirMatriz(b);
			this.validarMalCondicionado(p);	//Si A[p][i] == 0 STOP
			this.triangulizarColumna(p);	//Ej <-- (Ej - mIJ.Ei) para todo j=i+...+n
			System.out.println("trianguarizada: A:");
			imprimirMatriz(a);
			System.out.println("trianguarizada: B:");
			imprimirMatriz(b);
		}
		return hallarVectorX();
	}
	
	/** Algoritmo paso 1: Pivotea la fila mas trande arriba, a partir de la fila "p"
	 */
	private void subirFilaMasGrande(int p) {
		//la fila "i" con el elemento max para la columna P, subirá al lugar P
		int max = p;
		for (int i = p + 1; i < this.n; i++) {	//arranco desde la fila siguiente a P para buscar el max
			if (abs(this.a[i][p]) > abs(this.a[max][p])) {	//la fila "i" tiene su columna "p" más grande?
				max = i;
			}
		}	//aca encontre el valor max de la columna "P" para poner en la fila "P".
		double[]temp= this.a[p]; this.a[p] = this.a[max]; this.a[max] = temp;	//switcheo las filas en A
		double	t	= this.b[p]; this.b[p] = this.b[max]; this.b[max] = t;		//switcheo las filas en b
	}
	
	/** Algoritmo paso 2 dice: Si |A[p][i]| == 0 STOP
	 * 0 es ideal, porque en las operaciones sobre double quedan los decimales y nunca dan 0 exacto.
	 * Sistema mal condicionado => infinitas soluciones
	 */
	private void validarMalCondicionado(int p) {
		if (abs(this.a[p][p]) <= 0.000001) {
			throw new RuntimeException("Sistema mal condicionado");
		}
	}
	
	/** Algoritmo paso 3: Tengo que llevar a 0 el resto de las "p" filas hacia abajo, de dicha columna
	 * Para esto: |a b| =>	| a b 			 | => | a b				| => |a b		 | => Matriz
	 * mIJ=c/a	  |c d|		|(c d)- c/a*(a b)|	  |(c d)- (c  c*b/a)|	 |0 d-(c*b/a)|	Triangulada!
	 */
	private void triangulizarColumna(int p) {
		for (int i = p + 1; i < this.n; i++) {			//Al resto de las filas de "p" para abajo
			double mIJ = this.a[i][p] / this.a[p][p];	//col_actual dividida por la de arriba. 
			this.b[i] -= mIJ * this.b[p];				//aplico la resta también en b
			for (int j = p; j < this.n; j++) {			//en cada columna de A
				this.a[i][j] -= mIJ * this.a[p][j];		//aplico la resta en cada columna de A
			}
		}//por cada iteración de estas avanza en diagonal uno para abajo y uno para la derecha
	}
	
	/** Algoritmo paso 7: Despeja las X con la matriz ya triangular. Ej:
		|3.X1 + 2.X2 = 4| \ X1 = (4-2.X2)/3 \ X1 = (4-2.2)/3 = 0
		|	0 + 3.X2 = 6| / X2 = 6/3		/ X2 = 2
	 */
	private double[] hallarVectorX() {
		double[] x = new double[this.n];			//lugar dde poner los resultados
		for (int i = this.n - 1; i >= 0; i--) {		//desde abajo para arriba (el último valor es el Xn despejado Xn=valor)
			double sum = 0.0;						//Auxiliar
			for (int j = i + 1; j < this.n; j++) {	//La primera vez no entra porque j toma el valor n+1 (en arrays es "n" solo porque se cuenta desde 0 a n-1)
				sum += this.a[i][j] * x[j];			//Sería el 2.X2 del ejemplo
			}
			x[i] = (this.b[i] - sum) / this.a[i][i]; //Sería el X1 = (4-2.X2)/3 del ejemplo
			System.out.println("X["+i+"]: " + x[i]);
		}
		return x;
	}
	
	private void imprimirMatriz(double[][] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println("|"+Arrays.toString(a[i])+"|");
		}
	}
	private void imprimirMatriz(double[] b) {
		for (int i = 0; i < b.length; i++) {
			System.out.println("|"+b[i]+"|");
		}
	}
	
	private void validarCompletitud() {
		if (this.a == null || this.b == null) {
			throw new Incompleto("Algun valor vino en null");
		}
	}
}
