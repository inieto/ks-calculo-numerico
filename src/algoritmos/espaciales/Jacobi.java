package algoritmos.espaciales;

import static java.lang.Math.abs;
import interfaces.AlgoritmoMatricial;

import java.util.Arrays;

import excepciones.Incompleto;

/** <b>Algoritmo de aproximación de Jacobi</b>
	Este algoritmo busca aproximar las x1...xn (osea X) que cumplan A . X = B (matrices) con A(nxn), X(n) y B(n). 
	Lo logra llevando A.x=b a la forma Xi = T.Xo + C, donde T=A/aii y C=B/aii (aii son los elems de la diag.ppal)
	Luego, itera desde una aproxInicial los valores de Xi hasta que "con suerte" converjan.
	"Con suerte" es porque dependiendo de la aproxInicial puede o no converger; "salvo" que la diagonal principal sea
	"dominante", osea, que cada elemento de la diagonal sea MAYOR a la suma de todos los otros valores de la columna.
	Si no es dominante podemos, o no aplicar el algoritmo, o probar distintas aproximaciones iniciales.
	Por último, NUNCA puede haber un 0 en la diagonal ppal antes de despejar. Es condición para usar el algoritmo.
	Este algoritmo verifica e informa si la diagonal es dominante o si su diagonal ppal tiene 0s 
	
	Si A no es cuadrada o alguna de sus filas es combinación lineal de otras, 
	entonces el sistema está mal condicionado y hay infinitas soluciones
*/
public class Jacobi implements AlgoritmoMatricial{
	protected double[][] a;
	protected double  [] b;
	protected int n;
	private Double tolerancia;
	private Integer limiteIntentos;
	private double[] aproxInicial;

	@Override
	public void setMatriz(double[][] a, double[] b) {
		if(a == null || b == null) return;
		this.a = a;
		this.b = b;
		this.n = b.length;	//averiguo "n" que vale para A, X y B
		if(a.length != b.length) 
			throw new RuntimeException("Sistema mal condicionado:"+a.length+"x"+b.length);
	}
	
	public void setTolerancia(double tol) {
		this.tolerancia = tol;
	}
	
	public void setLimiteIntentos(int lim) {
		this.limiteIntentos = lim;
	}
	
	public void setAproximacionInicial(double[] aprox) {
		if(aprox == null) return;
		if(aprox.length != this.n)
			throw new RuntimeException(
				"Sistema mal condicionado: X("+this.n+") vs Aprox("+aprox.length+")");
		this.aproxInicial = aprox;
	}
	
	@Override
	public double[] calcular() {
		double[] x0 = this.aproxInicial;
		double[] x1 = new double[this.n];
		validarCompletitud();

		System.out.println("Matriz Original: A");
		imprimirMatriz(this.a);
		System.out.println("Matriz Original: b");
		imprimirMatriz(this.b);
		
		this.ordenarMatriz();	//Primero, ordeno la matriz con los mayores elementos en la diagonal ppal
		System.out.println("Matriz Ordenada: A");
		imprimirMatriz(this.a);
		System.out.println("Matriz Ordenada: b");
		imprimirMatriz(this.b);

		this.verificarDominante();		//Nos avisa si la diagonal es dominante y va a converger.
		
		this.transformarMatriz();	//Pasa de AX=B a Y=TX+C
		System.out.println("Matriz Transformada: T");
		imprimirMatriz(this.a);
		System.out.println("Matriz Transformada: c");
		imprimirMatriz(this.b);

		System.out.println("Comienzo de iteraciones:");
		x1 = iterar(x0);
		System.out.println("X0: "+ Arrays.toString(x0)+". X1: "+ Arrays.toString(x1));
		int intentos = 1;
		while (superaTolerancia(x1,x0) && intentos < this.limiteIntentos) {	//por cada fila..
			x0 = x1;
			x1 = iterar(x0);
			System.out.println("X0: "+ Arrays.toString(x0)+". X1: "+ Arrays.toString(x1));
			intentos++;
		}
		if(intentos >= this.limiteIntentos) {
			System.out.println("La aproximacion inicial elegida "+Arrays.toString(this.aproxInicial)+" diverge. Utilizar otra!");
			System.out.println("Última iteración: X1="+Arrays.toString(x1));
		}
		return x1;
	}
	
	/** Ordenar la matriz, con suerte tendrá la diagonal dominante */
	private void ordenarMatriz() {
		for (int i = 0; i < this.n; i++) {	//por cada fila..
			//la fila "j" con el elemento max para la columna "i", subirá al lugar de la fila "i"
			int max = i;
			for (int j = i + 1; j < this.n; j++) {	//arranco desde la fila siguiente a "i" para buscar el max
				if (abs(this.a[j][i]) > abs(this.a[max][i])) {	//la fila "j" tiene su columna "i" más grande?
					max = j;
				}
			}	//aca encontre el valor max de la columna "i" para poner en la fila "i".
			double[]temp= this.a[i]; this.a[i] = this.a[max]; this.a[max] = temp;	//switcheo las filas en A
			double	t	= this.b[i]; this.b[i] = this.b[max]; this.b[max] = t;		//switcheo las filas en b
		}
	}
	
	/** Revisa que cada elem (absoluto) de la diagonal ppal sea mayor a la suma (absoluta) del resto de la columna*/
	private void verificarDominante() {
		boolean dominante = true;
		for (int j = 0; j < this.n && dominante; j++) {	//por cada columna
			double sum = 0.0;
			for (int i = 0; i < this.n && dominante; i++) {	//itero las filas sumando el absoluto de esa columna,
				if(i == j) {								//salvo la de la diagonal ppal,
					this.validarMalCondicionado(i);			//en esa solo tengo que validar que no sea 0, por precondiciones.
					continue;
				}
				sum += abs(this.a[i][j]);
				dominante &= (abs(this.a[j][j]) > sum);
			}
		}
		System.out.println("Diagonal Principal es dominante?: "+dominante);
	}
	
	/**Si |A[i][i]| == 0 STOP - Sistema mal condicionado => infinitas soluciones
	 * 0 es ideal, porque en las operaciones sobre double quedan los decimales y nunca dan 0 exacto */
	private void validarMalCondicionado(int i) {
		if (abs(this.a[i][i]) <= 0.000001) {
			throw new RuntimeException("Sistema mal condicionado. Diagonal ppal con ceros!");
		}
	}
	
	/** Transforma A.x=b a la forma Xi = T.Xo + C, donde T=A/aii y C=B/aii (aii son los elems de la diag.ppal)
	 * Ej:	a b|c => aX1+bX2=c => X1=0-(X2.b/a) +c/a =>   0  -b/a | c/a 
	 * 		d e|f	 dX1+eX2=f	  X2=-(X1.d/e)+0+f/e	-d/e   0  | f/e
	 * osea, en Aii pongo 0 y en el resto lo niego y lo divido por el ex Aii*/
	private void transformarMatriz() {
		for (int i = 0; i < this.n; i++) {
			double aII = this.a[i][i];			//me lo guardo 
			for (int j = 0; j < this.n; j++) {	//transformo A[i] en T[i]
				this.a[i][j] /= -aII;			//cada columna de A la niego y divido por a[i][i]
			}
			this.b[i] /= aII;	//transformo b[i] en c[i]
			this.a[i][i] = 0;
		}
	}
	
	private boolean superaTolerancia(double[] x1, double[] x0) {
		for(int i = 0; i < this.n; i++) {
			if (abs(x1[i]-x0[i]) > this.tolerancia)
				return true;
		}
		return false;
	}

	/**	Itera X1 = T . Xo + C
	 * (se supone que en esta instancia en this.a se guardó T y en this.b se guardó C).
	 */
	protected double[] iterar(double[] x0) {
		double[] x1 = new double[this.n];
		double[][] t = this.a;
		double  [] c = this.b;
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.n; j++) {
				x1[i] += (t[i][j] * x0[j]);
			}
			x1[i] += c[i]; 
		}
		return x1;
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
		if (this.a == null || this.b == null || this.tolerancia == null
				|| this.limiteIntentos == null || this.aproxInicial == null) {
			throw new Incompleto("Algun valor vino en null");
		}
	}
}
