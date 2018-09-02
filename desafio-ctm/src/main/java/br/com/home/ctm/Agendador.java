package br.com.home.ctm;

import static br.com.home.util.Constantes.DURACAO_PERIODO_TARDE;
import static br.com.home.util.Constantes.INICIO_PERIODO_TARDE;
import static br.com.home.util.Constantes.DURACAO_EVENTO;
import static br.com.home.util.Constantes.UNIDADE_TEMPORAL_EVENTO;
import static br.com.home.util.Constantes.NOME_EVENTO;
import static br.com.home.util.Constantes.LINHA_ARQUIVO_PATTERN;
import static br.com.home.util.Constantes.DURACAO_ALMOCO;
import static br.com.home.util.Constantes.INICIO_PERIODO_ALMOCO;
import static br.com.home.util.Constantes.DURACAO_MAXIMA_EVENTO;
import static br.com.home.util.Constantes.DURACAO_PERIODO_MANHA;
import static br.com.home.util.Constantes.INICIO_PERIODO_MANHA;
import static br.com.home.util.Constantes.DURACAO_NETWORKING;
import static br.com.home.util.Constantes.UNIDADE_TEMPORAL_PERIODO_NETWORKING;
import static br.com.home.util.Constantes.INICIO_MINIMO_NETWORKING;
import static br.com.home.util.Constantes.NETWORKING_EVENTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import br.com.home.ctm.enums.UnidadeTemporal;
import br.com.home.ctm.evento.Evento;
import br.com.home.ctm.periodo.Periodo;
import br.com.home.ctm.track.Track;
import br.com.home.util.Logger;

public final class Agendador {
	public Agendador() {
	}

	private static Logger logger = Logger.getLogger();

	public Agenda cronograma(BufferedReader input) throws IOException {
		List<Evento> listaEventos = new ArrayList<Evento>();

		for (String line; (line = input.readLine()) != null;) {
			line = line.trim();
			Evento evento = parseInputLine(line);
			if (evento == null) {
				continue;
			}
			listaEventos.add(evento);
		}

		Agenda agenda = new Agenda();
		while (listaEventos.size() != 0) {
			Periodo manha = new Periodo(DURACAO_PERIODO_MANHA, INICIO_PERIODO_MANHA);
			preencherPeriodoComEvento(manha, listaEventos);

			Periodo almoco = new Periodo(DURACAO_ALMOCO, INICIO_PERIODO_ALMOCO);
			almoco.adicionarEvento(new Evento("Lunch", DURACAO_ALMOCO, UnidadeTemporal.MINUTOS));

			Periodo tarde = new Periodo(DURACAO_PERIODO_TARDE, INICIO_PERIODO_TARDE);
			preencherPeriodoComEvento(tarde, listaEventos);

			Evento networking = new Evento(NETWORKING_EVENTO, DURACAO_NETWORKING, UNIDADE_TEMPORAL_PERIODO_NETWORKING);
			Periodo networkingPeriodo = new Periodo(networking.getDuracaoEmMinutos(), INICIO_MINIMO_NETWORKING);
			networkingPeriodo.adicionarEvento(networking);
			tarde.adicionarPeriodoSuplementar(networkingPeriodo);

			Track track = new Track();
			track.adicionarPeriodo(manha);
			track.adicionarPeriodo(almoco);
			track.adicionarPeriodo(tarde);
			agenda.adicionarTrack(track);
		}

		return agenda;
	}

	private static void preencherPeriodoComEvento(Periodo periodo, List<Evento> listaEvento) {

		for (Iterator<Evento> iter = listaEvento.iterator(); iter.hasNext();) {
			Evento evento = iter.next();
			if (periodo.temTempo(evento)) {
				periodo.adicionarEvento(evento);
				iter.remove();
			}
		}
	}

	private static Evento parseInputLine(String linha) {
		if (linha.length() == 0) {
			return null;
		}

		Matcher match = LINHA_ARQUIVO_PATTERN.matcher(linha);
		if (match.find() == false) {
			logger.warn("Linha Invalida: " + linha);
			return null;
		}

		UnidadeTemporal tempo;
		if (match.group(UNIDADE_TEMPORAL_EVENTO).equalsIgnoreCase("min")) {
			tempo = UnidadeTemporal.MINUTOS;
		} else {
			tempo = UnidadeTemporal.LIGHTENING;
		}

		String name = match.group(NOME_EVENTO);
		String duracao = match.group(DURACAO_EVENTO);
		if (duracao == null) {
			duracao = "1";
		}
		int tempoDuracao = Integer.parseInt(duracao);

		Evento evento = new Evento(name, tempoDuracao, tempo);
		if (evento.getDuracaoEmMinutos() > DURACAO_MAXIMA_EVENTO) {
			logger.warn("Duracao do evento '" + name + "' e maior que o maximo permitido a um evento.");
			return null;
		}

		return evento;
	}
}
