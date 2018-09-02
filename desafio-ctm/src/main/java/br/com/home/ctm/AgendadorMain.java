package br.com.home.ctm;

import java.io.BufferedReader;
import java.io.File;

import br.com.home.util.Arquivo;
import br.com.home.util.Logger;

public class AgendadorMain {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger();

		if (args.length < 1) {
			logger.fatal("arquivo com eventos deve ser passado como parametro");
			System.exit(1);
		}

		File inputFile = new File(args[0]);
		try {
			BufferedReader reader = Arquivo.getBufferedReaderForResourceFile(inputFile);
			Agenda agenda = new Agendador().cronograma(reader);
			logger.info(agenda.obterAgenda());
		} catch (Exception e) {
			logger.fatal("Erro ao ler o arquivo: " + inputFile.getAbsolutePath());
			System.exit(1);
		}
	}
}
