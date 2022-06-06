package br.edu.iff.bancodepalavras.dominio.letra.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.exibir.Exibir;

public class LetraTexto extends Letra {

	public LetraTexto(char codigo) {
		super(codigo);
	}

	@Override
	public void exibir(Object contexto) {
		Exibir exibir = (Exibir)contexto;
		exibir.adicionar(Character.toString(this.getCodigo()).toUpperCase());
	}
}
