package br.edu.iff.jogoforca.dominio.boneco.texto;

import java.util.HashMap;

import br.edu.iff.exibir.Exibir;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;

public class BonecoTexto implements Boneco {

	private static Boneco soleInstance;
	private static HashMap<Integer, String> partesBoneco = new HashMap<Integer, String>()
	{
		private static final long serialVersionUID = 1L;
	{
	    put(1, "CABECA");
	    put(2, "CABECA, OLHO ESQ");
	    put(3, "CABECA, OLHO ESQ, OLHO DIR");
	    put(4, "CABECA, OLHO ESQ, OLHO DIR, NARIZ");
	    put(5, "CABECA, OLHO ESQ, OLHO DIR, NARIZ, BOCA");
	    put(6, "CABECA, OLHO ESQ, OLHO DIR, NARIZ, BOCA, TRONCO");
	    put(7, "CABECA, OLHO ESQ, OLHO DIR, NARIZ, BOCA, TRONCO, BRACO ESQ");
	    put(8, "CABECA, OLHO ESQ, OLHO DIR, NARIZ, BOCA, TRONCO, BRACO ESQ, BRACO DIR");
	    put(9, "CABECA, OLHO ESQ, OLHO DIR, NARIZ, BOCA, TRONCO, BRACO ESQ, BRACO DIR, PERNA ESQ");
	    put(10, "CABECA, OLHO ESQ, OLHO DIR, NARIZ, BOCA, TRONCO, BRACO ESQ, BRACO DIR, PERNA ESQ, PERNA DIR");
	}};
	
	private BonecoTexto() {}

	public static Boneco getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new BonecoTexto();
		}
		return soleInstance;
	}
	
	@Override
	public void exibir(Object contexto, int partes) {
		String saida = partesBoneco.get(partes);
		
		Exibir exibir = (Exibir)contexto;
		exibir.adicionar(saida);
	}
}
