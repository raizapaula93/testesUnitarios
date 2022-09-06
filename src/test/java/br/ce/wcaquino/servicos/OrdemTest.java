package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)//M�TODO PARA ORDENAR A EXECU��O DOS TESTES POR ORDEM ALFAB�TICA
public class OrdemTest {//O JUNIT N�O GARANTE A ORDEM DE EXECU��O DOS TESTES
	
	public static int contador = 0;
	
	@Test
	public void inicia() {
		contador=1;
	}
		
	@Test
	public void verifica() {
		assertEquals(1,contador);
	}
	
}
