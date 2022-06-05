package br.edu.iff.jogoforca.dominio.boneco.imagem;

import java.io.PrintStream;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;

public class BonecoImagem implements Boneco {

	private static Boneco soleInstance;
	
	private BonecoImagem() {}

	public static Boneco getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new BonecoImagem();
		}
		return soleInstance;
	}
	
	@Override
	public void exibir(Object contexto, int partes) {
		String saida = new String();
		
        //refatorar
		if (partes == 1) {
			saida = "CABECA";
		} else if (partes == 2) {
			saida = "CABECA, OLHO ESQ";
		} else if (partes == 3) {
			saida = "CABECA, OLHO ESQ, OLHO DIR";
		} else if (partes == 4) {
			saida = "CABECA, OLHO ESQ, OLHO DIR, NARIZ";
		} else if (partes == 5) {
			saida = "CABECA, OLHO ESQ, OLHO DIR, NARIZ, BOCA";
		} else if (partes == 6) {
			saida = "CABECA, OLHO ESQ, OLHO DIR, NARIZ, BOCA, TRONCO";
		} else if (partes == 7) {
			saida = "CABECA, OLHO ESQ, OLHO DIR, NARIZ, BOCA, TRONCO, BRACO ESQ";
		} else if (partes == 8) {
			saida = "CABECA, OLHO ESQ, OLHO DIR, NARIZ, BOCA, TRONCO, BRACO ESQ, BRACO DIR";
		} else if (partes == 9) {
			saida = "CABECA, OLHO ESQ, OLHO DIR, NARIZ, BOCA, TRONCO, BRACO ESQ, BRACO DIR, PERNA ESQ";
		} else if (partes == 10) {
			saida = "CABECA, OLHO ESQ, OLHO DIR, NARIZ, BOCA, TRONCO, BRACO ESQ, BRACO DIR, PERNA ESQ, PERNA DIR";
		}
		
		((PrintStream) contexto).println(saida);
	}
	
}

