package fabricas;

import dominio.Boneco;
import dominio.BonecoImagem;

public class BonecoImagemFactory implements BonecoFactory {

	private static BonecoImagemFactory soleInstance;
	
	private BonecoImagemFactory() {}
	
	public static BonecoImagemFactory getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new BonecoImagemFactory();
		}
		return soleInstance;
	}
	
	@Override
	public Boneco getBoneco() {
		return BonecoImagem.getSoleInstance();
	}

	
	
}
