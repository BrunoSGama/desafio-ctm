package br.com.home.ctm;

import static br.com.home.util.Constantes.NOVA_LINHA;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import br.com.home.ctm.track.Track;

public class Agenda {
    private List<Track> listaTracks = new ArrayList<Track>();

    public Agenda() {}

    public void adicionarTrack(Track track) {
    	listaTracks.add(track);
    }

    public String obterAgenda() {
        StringBuilder str = new StringBuilder();
        
        IntStream.range(0, listaTracks.size()).forEach(indice -> {
        	Track track = listaTracks.get(indice);
        	str.append("Track " + (indice + 1) + ":" + NOVA_LINHA);
            str.append(track.obterTracks());
            str.append(NOVA_LINHA);
        });
        
        return str.toString();
    }
}
