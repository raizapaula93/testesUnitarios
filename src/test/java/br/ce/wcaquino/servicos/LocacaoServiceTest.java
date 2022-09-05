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

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	
	
	@Rule
	public ErrorCollector error = new ErrorCollector();//ccheca toda a a pilha de erros
		
	@Test //sinaliza para o framework que o m�todo abaixo � um teste
	
	public void testeLocacao () throws Exception {//deixar um cursor em cima de um teste somente o executa
		//cen�rio - inicializar as vari�veis
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usu�rio 1");
		Filme filme = new Filme("Filme 1",2,5.0);
		
		//a��o
		Locacao locacao;
		
			locacao = service.alugarFilme(usuario, filme);
			//verifica��o
			error.checkThat(locacao.getValor(),is(equalTo(6.0)));
			assertThat(locacao.getValor(),is(not(6.0)));
			error.checkThat(isMesmaData(locacao.getDataLocacao(),new Date()), is(true));
			error.checkThat(isMesmaData(locacao.getDataRetorno(),DataUtils.obterDataComDiferencaDias(1)), is(false));

		
	}
	@Test(expected=Exception.class) 
	
	public void testeLocacao_filmeSemEstoque () throws Exception {
		//cen�rio
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usu�rio 1");
		Filme filme = new Filme("Filme 1",0,5.0);
		
		//a��o
		service.alugarFilme(usuario, filme);
			
	}
	
}