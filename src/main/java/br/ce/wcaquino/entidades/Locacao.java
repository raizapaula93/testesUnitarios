package br.ce.wcaquino.entidades;

import java.util.Date;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> fa9094c86329a42b715acc98db2e659ccd92f1ce

public class Locacao {

	private Usuario usuario;
<<<<<<< HEAD
	private Filme filme;
=======
	private List<Filme> filmes;
>>>>>>> fa9094c86329a42b715acc98db2e659ccd92f1ce
	private Date dataLocacao;
	private Date dataRetorno;
	private Double valor;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getDataLocacao() {
		return dataLocacao;
	}
	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
<<<<<<< HEAD
	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
=======
	public List<Filme> getFilmes() {
		return filmes;
	}
	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}
	
>>>>>>> fa9094c86329a42b715acc98db2e659ccd92f1ce
}