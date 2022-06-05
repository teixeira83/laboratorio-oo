package br.edu.iff.bancodepalavras.dominio.letra;

import java.util.HashMap;

public abstract class LetraFactoryImpl implements LetraFactory {

	protected HashMap<Character, Letra> pool = new HashMap<Character, Letra>();
	protected Letra encoberta;
	
	protected Letra criarLetra(char codigo) {
		return null;
	}

	@Override
	public final Letra getLetra(char codigo) {
		if (!this.pool.containsKey(codigo)) {
			this.pool.put(codigo, this.criarLetra(codigo));
		}
		return this.pool.get(codigo);
	}
	
	@Override
	public final Letra getLetraEncoberta() {
		if (encoberta == null) {
			char codigo = "*".charAt(0);
			this.encoberta = this.criarLetra(codigo);
		}
		return this.encoberta;
	}
	
}
