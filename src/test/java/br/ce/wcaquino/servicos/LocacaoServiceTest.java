package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	private LocacaoService service;
	
	private static int contador = 0;//funciona pra deixar a vari�vel no escopo da classe, ent�o o teste n�o inicializar� varias vezes a msm vari�vel
	
	@Rule
	public ErrorCollector error = new ErrorCollector();//checa toda a a pilha de erros
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		System.out.println("Before");
		service = new LocacaoService();
		contador++;
		System.out.println(contador);
	}
	
	@After
	public void tearDown() {
		System.out.println("After");
	}
	
	@BeforeClass //inicia a classe antes de todos
	public static void setupClass() {
		System.out.println("Before Class");
		
	}
	
	@AfterClass //deixa esse processo por �ltimo
	public static void tearDownClass() {
		System.out.println("After Class");
	}
	
		
	@Test 
	
	public void testeLocacao () throws Exception {
		
		
		//cenario
		Usuario usuario = new Usuario("Usu�rio 1");
		Filme filme = new Filme("Filme 1",2,5.0);
		
		//a��o
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		//verifica��o
		error.checkThat(locacao.getValor(),is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(),new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(),DataUtils.obterDataComDiferencaDias(1)), is(true));

		
	}
	
	@Test(expected=FilmeSemEstoqueException.class) 
	
	public void testeLocacao_filmeSemEstoque () throws Exception {
		//cen�rio
		Usuario usuario = new Usuario("Usu�rio 1");
		Filme filme = new Filme("Filme 1",0,5.0);
		
		//a��o
		service.alugarFilme(usuario, filme);
			
	}
	
	@Test
	public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException {
		//cenario

		Filme filme = new Filme("Filme 2",1,4.0);
		Usuario usuario = new Usuario("Usu�rio 1");
		//acao
		try {
			service.alugarFilme(null, filme);
				Assert.fail();
		} catch(LocadoraException e) {
			assertThat(e.getMessage(), is("Usu�rio vazio"));;
		}
	
	}
	
	@Test
	
	public void testLocacao_FilmeVazio() throws LocadoraException, FilmeSemEstoqueException {
		//cen�rio
		
		Usuario usuario = new Usuario("Usu�rio 1");
		
		
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		//acao
		service.alugarFilme(usuario, null);
		System.out.println("Forma nova");
	}
		
}