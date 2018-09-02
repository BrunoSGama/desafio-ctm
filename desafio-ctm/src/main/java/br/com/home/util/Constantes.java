package br.com.home.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

import br.com.home.ctm.enums.UnidadeTemporal;

public final class Constantes {
    private Constantes() {}

    public static final String NOVA_LINHA = System.getProperty("line.separator");
    
    public static final Pattern LINHA_ARQUIVO_PATTERN = Pattern.compile("^(.+)\\s(\\d+)?\\s?((min)|(lightning))$");
    public static final int NOME_EVENTO = 1;
    public static final int DURACAO_EVENTO = 2;
    public static final int UNIDADE_TEMPORAL_EVENTO = 3;

    public static final int DURACAO_PERIODO_MANHA = 180;
    public static final int DURACAO_ALMOCO = 60;
    public static final int DURACAO_PERIODO_TARDE = 240;

    public static final int INICIO_PERIODO_MANHA = 9 * 60;
    public static final int INICIO_PERIODO_ALMOCO = INICIO_PERIODO_MANHA + DURACAO_PERIODO_MANHA;
    public static final int INICIO_PERIODO_TARDE = INICIO_PERIODO_ALMOCO + DURACAO_ALMOCO;

    public static final int DURACAO_MAXIMA_EVENTO = Collections.max(Arrays.asList(DURACAO_PERIODO_MANHA, DURACAO_ALMOCO, DURACAO_PERIODO_TARDE));

    public static final UnidadeTemporal UNIDADE_TEMPORAL_PERIODO_ALMOCO = UnidadeTemporal.MINUTOS;
    public static final String ALMOCO = "Lunch";
    public static final String NETWORKING_EVENTO = "Networking Event";
    public static final int DURACAO_NETWORKING = 60;
    public static final UnidadeTemporal UNIDADE_TEMPORAL_PERIODO_NETWORKING = UnidadeTemporal.MINUTOS;
    public static final int INICIO_MINIMO_NETWORKING = (12 * 60) + (4 * 60);
}
