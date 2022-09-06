package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)//MÉTODO PARA ORDENAR A EXECUÇÃO DOS TESTES POR ORDEM ALFABÉTICA
public class OrdemTest {//O JUNIT NÃO GARANTE A ORDEM DE EXECUÇÃO DOS TESTES
	
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
