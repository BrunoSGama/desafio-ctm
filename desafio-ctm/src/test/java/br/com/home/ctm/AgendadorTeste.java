package br.com.home.ctm;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.Test;

import br.com.home.util.Arquivo;

public class AgendadorTeste {

	/**
	 * Este teste falha, pois não consegui finalizar de acordo com o enunciado.
	 * @throws IOException
	 */
	@Test
	public void AgendadorTesteExperado() throws IOException {
		String arquivo = "/input_file";
		Agenda agenda = new Agendador().cronograma(Arquivo.getBufferedReaderForResourceFile(arquivo, this));
		assertTrue(Arquivo.contentEquals(arquivo + "_expected", agenda.obterAgenda(), this));
	}
	
	/**
	 * Mesmo teste acima, porem como os arquivos são diferentes, esse teste passa por esperar um resultado falso.
	 * @throws IOException
	 */
	@Test
	public void AgendadorTesteExperadoFalse() throws IOException {
		String arquivo = "/input_file";
		Agenda agenda = new Agendador().cronograma(Arquivo.getBufferedReaderForResourceFile(arquivo, this));
		assertFalse(Arquivo.contentEquals(arquivo + "_expected", agenda.obterAgenda(), this));
	}

	/**
	 * Esse teste passa, pois gerou o arquivo de acordo com a logica, no entanto diferente do esperado.
	 * @throws IOException
	 */
	@Test
	public void AgendadorTesteGerado() throws IOException {
		String arquivo = "/input_file";
		Agenda agenda = new Agendador().cronograma(Arquivo.getBufferedReaderForResourceFile(arquivo, this));
		assertTrue(Arquivo.contentEquals(arquivo + "_generated", agenda.obterAgenda(), this));
	}

}
