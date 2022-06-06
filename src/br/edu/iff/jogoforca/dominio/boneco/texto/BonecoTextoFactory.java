package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;

public class BonecoTextoFactory implements BonecoFactory {

	private static BonecoTextoFactory soleInstance;
	
	private BonecoTextoFactory() {}
	
	public static BonecoTextoFactory getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new BonecoTextoFactory();
		}
		return soleInstance;
	}

	@Override
	public Boneco getBoneco() {
		return BonecoTexto.getSoleInstance();
	}
}
