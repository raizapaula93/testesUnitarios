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
import org.junit.Assert;
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
	
	
	
	@Rule
	public ErrorCollector error = new ErrorCollector();//checa toda a a pilha de erros
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
		
	@Test //sinaliza para o framework que o método abaixo é um teste
	
	public void testeLocacao () throws Exception {//deixar um cursor em cima de um teste somente o executa
		//Se o teste não estiver esperando uma exception, deixe o JUnit gerenciar
		//cenário - inicializar as variáveis
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		Filme filme = new Filme("Filme 1",2,5.0);
		
		//ação
		Locacao locacao;
		
			locacao = service.alugarFilme(usuario, filme);
			//verificação
			error.checkThat(locacao.getValor(),is(equalTo(5.0)));
			error.checkThat(isMesmaData(locacao.getDataLocacao(),new Date()), is(true));
			error.checkThat(isMesmaData(locacao.getDataRetorno(),DataUtils.obterDataComDiferencaDias(1)), is(true));

		
	}
	
	@Test(expected=FilmeSemEstoqueException.class) //solução elegante -> Se estou esperando um exception, sinalizo para o Junit controlar
	
	public void testeLocacao_filmeSemEstoque () throws Exception {
		//cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		Filme filme = new Filme("Filme 1",0,5.0);
		
		//ação
		service.alugarFilme(usuario, filme);
			
	}
	
	@Test
	public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException {
		//cenario
		LocacaoService service = new LocacaoService();
		Filme filme = new Filme("Filme 2",1,4.0);
		Usuario usuario = new Usuario("Usuário 1");
		//acao
		try {
			service.alugarFilme(null, filme);
				Assert.fail();
		} catch(LocadoraException e) {
			assertThat(e.getMessage(), is("Usuário vazio"));;
		}
		System.out.println("Forma robusta");
	}
	
	@Test
	
	public void testLocacao_FilmeVazio() throws LocadoraException, FilmeSemEstoqueException {
		//cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 1");
		
		
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		//acao
		service.alugarFilme(usuario, null);
		System.out.println("Forma nova");
	}
		
}