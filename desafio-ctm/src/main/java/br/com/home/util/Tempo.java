package br.com.home.util;

public final class Tempo {
	private Tempo() {
	}

	public static String obterHora(int minutos) {
		int minutosMaximoSuportado = (12 * 60 + 12 * 60) - 1;

		if (minutos > minutosMaximoSuportado) {
			throw new IllegalArgumentException(
					"Tempo em minutos não pode ser maior que " + minutosMaximoSuportado + " minutos.");
		}

		int horas = minutos / 60;
		String horasAExibir = String.valueOf(horas);
		if (horas > 12) {
			horasAExibir = String.valueOf(horas - 12);
		}
		if (horasAExibir.length() == 1) {
			horasAExibir = "0" + horasAExibir;
		}

		minutos = minutos - (horas * 60);
		String minutosAExibir = "";
		if (minutos == 0) {
			minutosAExibir = "00";
		} else if (minutos < 10) {
			minutosAExibir = "0" + minutos;
		} else {
			minutosAExibir = "" + minutos;
		}

		String periodoDodia = "";
		if (horas < 12) {
			periodoDodia = " AM";
			if (horasAExibir.equals("00")) {
				horasAExibir = "12";
			}
		} else {
			periodoDodia = " PM";
		}

		return horasAExibir + ":" + minutosAExibir + periodoDodia;
	}
}
