package br.com.home.ctm.track;

import java.util.ArrayList;
import java.util.List;

import br.com.home.ctm.periodo.Periodo;

public class Track {
    private List<Periodo> listaPeriodos = new ArrayList<Periodo>();

    public Track() {}

    public void adicionarPeriodo(Periodo periodo) {
        this.listaPeriodos.add(periodo);
    }

	public String obterTracks() {
        StringBuilder str = new StringBuilder();
        
        listaPeriodos.stream().forEach(periodo -> {
        	str.append(periodo.obterPeriodos());
        });
        return str.toString();
    }
}
