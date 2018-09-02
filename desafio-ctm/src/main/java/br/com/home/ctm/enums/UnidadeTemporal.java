package br.com.home.ctm.enums;

public enum UnidadeTemporal {
    MINUTOS(1, "min"), LIGHTENING(5, "lightning");

    private int tempo;
    private String representacao;

    private UnidadeTemporal(int tempo, String representacao) {
        this.tempo = tempo;
        this.representacao = representacao;
    }

    public int emMinutos(int duracao) {
        return tempo * duracao;
    }

    public String obterUnidadeTemporal() {
        return representacao;
    }
}
