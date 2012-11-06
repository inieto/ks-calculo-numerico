package excepciones;

public class Incompleto extends RuntimeException {

	public Incompleto(String causa) {
		super(causa);
	}
}
