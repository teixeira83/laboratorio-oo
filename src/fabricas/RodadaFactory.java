package fabricas;

import dominio.Jogador;
import dominio.Rodada;

public interface RodadaFactory {

	public Rodada getRodada(Jogador jogador);
}
