package fabricas;

import dominio.Boneco;
import dominio.Letra;

public class ElementoGraficoTextoFactory implements ElementoGraficoFactory {

	private LetraTextoFactory letraFactory;
	private BonecoTextoFactory bonecoFactory;
	
	private static ElementoGraficoTextoFactory soleInstance;
	
	private ElementoGraficoTextoFactory(){
		letraFactory = LetraTextoFactory.getSoleInstance();
		bonecoFactory = BonecoTextoFactory.getSoleInstance();
	}
	
	public static ElementoGraficoTextoFactory getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new ElementoGraficoTextoFactory();
		}
		return soleInstance;
	}
	
	
	@Override
	public Boneco getBoneco() {
		return bonecoFactory.getBoneco();
	}

	@Override
	public Letra getLetra(char codigo) {
		return letraFactory.getLetra(codigo);
	}

	@Override
	public Letra getLetraEncoberta() {
		return letraFactory.getLetraEncoberta();
	}
}
