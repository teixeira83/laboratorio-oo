package dominio;

import jogo.Exibir;

public class LetraTexto extends Letra {

	public LetraTexto(char codigo) {
		super(codigo);
	}

	@Override
	public void exibir(Object contexto) {
		Exibir exibir = (Exibir)contexto;
		exibir.adicionar(this.toString().toUpperCase());
	}
}
