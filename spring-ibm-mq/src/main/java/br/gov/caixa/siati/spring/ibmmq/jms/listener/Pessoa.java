package br.gov.caixa.siati.spring.ibmmq.jms.listener;

public class Pessoa {

	private String nome;
	private int maxLivros;

	public Pessoa() {
		nome = "Nome Desconhecido";
		maxLivros = 3;
	}

	public Pessoa(String nome, int maxLivros) {
		super();
		this.nome = nome;
		this.maxLivros = maxLivros;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String outroNome) {
		nome = outroNome;
	}

	public int getMaxLivros() {
		return maxLivros;
	}

	public void setMaxLivros(int outroValor) {
		maxLivros = outroValor;
	}

}
