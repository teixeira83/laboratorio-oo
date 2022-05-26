package fabricas;

import dominio.Letra;

public class LetraImagemFactory extends LetraFactoryImpl {

	private static LetraImagemFactory soleInstance;
	
	private LetraImagemFactory() {}
	
	public static LetraImagemFactory getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new LetraImagemFactory();
		}
		return soleInstance;
	}

	@Override
	protected Letra criarLetra(char codigo) {
		return super.criarLetra(codigo);
	}
}
