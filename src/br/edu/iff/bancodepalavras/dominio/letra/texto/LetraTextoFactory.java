package br.edu.iff.bancodepalavras.dominio.letra.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactoryImpl;

public class LetraTextoFactory extends LetraFactoryImpl {
	
	private static LetraTextoFactory soleInstance;
	
	private LetraTextoFactory() {}
	
	public static LetraTextoFactory getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new LetraTextoFactory();
		}
		return soleInstance;
	}
		
	protected Letra criarLetra(char codigo) {
		return new LetraTexto(codigo);
	}
}
