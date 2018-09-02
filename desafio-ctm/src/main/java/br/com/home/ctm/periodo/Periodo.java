package br.com.home.ctm.periodo;

import static br.com.home.util.Constantes.NOVA_LINHA;

import java.util.ArrayList;
import java.util.List;

import br.com.home.ctm.evento.Evento;
import br.com.home.util.Tempo;

public class Periodo {
	private List<Evento> listaEvento = new ArrayList<Evento>();
	private int tempoRestante;
	private int inicio;
	private Periodo periodoSuplementar;

	public Periodo(int duracao, int inicio) {
		this.tempoRestante = duracao;
		this.inicio = inicio;
	}

	public void adicionarEvento(Evento evento) {
		if (tempoRestante < evento.getDuracaoEmMinutos()) {
			throw new IllegalStateException("Tempo insuficiente para adicionar esse evento: '" + evento.getNome() + "'");
		}
		listaEvento.add(evento);
		tempoRestante -= evento.getDuracaoEmMinutos();
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public List<Evento> getListaEvento() {
		return listaEvento;
	}

	public void setListaEvento(List<Evento> listaEvento) {
		this.listaEvento = listaEvento;
	}

	public boolean temTempo(Evento evento) {
		return tempoRestante >= evento.getDuracaoEmMinutos();
	}

	public void adicionarPeriodoSuplementar(Periodo periodo) {
		this.periodoSuplementar = periodo;
	}

	public String obterPeriodos() {
		StringBuilder str = new StringBuilder();
		int inicioProximoEvento = agendarEvento(listaEvento, inicio, str);
		
		if (periodoSuplementar != null) {
			int periodoSuplementarInicio = periodoSuplementar.getInicio();
			if (inicioProximoEvento > periodoSuplementarInicio) {
				periodoSuplementarInicio = inicioProximoEvento;
			}
			inicioProximoEvento = agendarEvento(periodoSuplementar.getListaEvento(), periodoSuplementarInicio, str);
		}
		return str.toString();
	}

	private int agendarEvento(List<Evento> listaEvento, int inicio, StringBuilder str) {
		int inicioProximoEvento = inicio;
		
		for(Evento evento: listaEvento) {
			str.append(Tempo.obterHora(inicioProximoEvento) + " " + evento.obterEvento() + NOVA_LINHA);
			inicioProximoEvento += evento.getDuracaoEmMinutos();
		}
		
		return inicioProximoEvento;
	}
}
