package defaultTest;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {
	
	@Test
	public void test() {
	Assert.assertTrue(true);//testa se o resultado da assertiva � verdadeiro; retorno temq ser um boolean	
	Assert.assertFalse(false);//testa se o resultado da assertiva � falso; retorno temq ser um boolean
	
	Assert.assertEquals(1,1);
	Assert.assertEquals(0.51234,0.512,0.01);
	Assert.assertEquals(Math.PI,3.14,0.01);
	
	int i=5;
	Integer i2 = 5;
	Assert.assertEquals(Integer.valueOf(i), i2);
	Assert.assertEquals(i,i2.intValue());
	
	
	Assert.assertEquals("bola","bola");//1� esperado,2� atual
	Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
	Assert.assertTrue("bola".startsWith("bo"));
	
	Usuario u1 = new Usuario ("Usuario 1");
	Usuario u2 = new Usuario ("Usuario 1");
	Usuario u3 = null;
	
	Assert.assertEquals(u1,u2);	//comparar conte�dos
	
	Assert.assertSame(u1, u2);//comparar inst�ncias de objetos
	
	Assert.assertNull(u3);
		
		
	}

}
