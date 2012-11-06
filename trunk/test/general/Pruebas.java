package general;

import static org.junit.Assert.*;
import static java.lang.Math.*;

import org.junit.Test;

public class Pruebas {

	@Test
	public void testTrigonometricModes() {
		System.out.println("sen(0)= " + sin(0));
		assertEquals(0,sin(0),0.0001);
		
		System.out.println("sen(90)= " + sin(90));
		assertFalse(new Double(1).equals(sin(90)));
		
		System.out.println("sen(PI/2)= " + sin(PI/2));
		assertEquals(1,sin(PI/2),0.0001);
		
		System.out.println("sen(PI)= " + sin(PI));
		assertEquals(0,sin(PI),0.0001);
		
		System.out.println("sen(3/2.PI)= " + sin(3*PI/2));
		assertEquals(-1,sin(3*PI/2),0.0001);
		
		System.out.println("sen(2PI)= " + sin(2*PI));
		assertEquals(0,sin(2*PI),0.0001);
	}

}
