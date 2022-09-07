package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
	
		
	@Rule
	public ErrorCollector error = new ErrorCollector();//checa toda a a pilha de erros
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		service = new LocacaoService();
		
	}
			
	@Test 
	
	public void testeLocacao () throws Exception {
		
		
		//cenario
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1",1 , 5.0)) ;
		
		//ação
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		//verificação
		error.checkThat(locacao.getValor(),is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(),new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(),DataUtils.obterDataComDiferencaDias(1)), is(true));

		
	}
	
	
	
	@Test(expected=FilmeSemEstoqueException.class) 
	public void testeLocacao_filmeSemEstoque () throws Exception {
		
		//cenário
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1",0,5.0));
		
		//ação
		service.alugarFilme(usuario, filmes); 
			
	}
	
	@Test
	public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException {
		//cenario

		List<Filme> filmes = Arrays.asList(new Filme("Filme 1",2,5.0));
		
		
		//acao
		try {
			service.alugarFilme(null, filmes);
				Assert.fail();
		} catch(LocadoraException e) {
			assertThat(e.getMessage(), is("Usuário vazio"));;
		}
	
	}
	
	@Test
	
	public void testLocacao_FilmeVazio() throws LocadoraException, FilmeSemEstoqueException {
		//cenário
		
		Usuario usuario = new Usuario("Usuário 1");
		
		
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		//acao
		service.alugarFilme(usuario, null);
		
	}
		
}