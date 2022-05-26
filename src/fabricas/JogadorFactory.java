package fabricas;

import dominio.Jogador;

public interface JogadorFactory {
	public Jogador getJogador(String nome);
}
