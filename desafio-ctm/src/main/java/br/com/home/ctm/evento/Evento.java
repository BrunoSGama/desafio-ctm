package br.com.home.ctm.evento;

import br.com.home.ctm.enums.UnidadeTemporal;

public class Evento {
	
	private String nome;
	private int duracao;
	private UnidadeTemporal tempo;

	public Evento(String nome, int duracao, UnidadeTemporal tempo) {
		this.nome = nome;
		this.duracao = duracao;
		this.tempo = tempo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public UnidadeTemporal getTempo() {
		return tempo;
	}

	public void setTempo(UnidadeTemporal tempo) {
		this.tempo = tempo;
	}
	
	public int getDuracaoEmMinutos() {
		return tempo.emMinutos(duracao);
	}

	public String obterEvento() {
		return nome + " - " + duracao + " " + getTempo().obterUnidadeTemporal();
	}

}
