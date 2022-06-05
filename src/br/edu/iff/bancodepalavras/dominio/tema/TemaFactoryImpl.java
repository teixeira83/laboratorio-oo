package br.edu.iff.bancodepalavras.dominio.tema;

import br.edu.iff.factory.EntityFactory;

public class TemaFactoryImpl extends EntityFactory implements TemaFactory {

	private static TemaFactoryImpl soleInstance;
	
	private TemaFactoryImpl(TemaRepository repository) {
		super(repository);
	}

	public static TemaFactoryImpl getSoleInstance() {
		return soleInstance;
	}
	
	public static void createSoleInstance(TemaRepository repository){
		soleInstance = new TemaFactoryImpl(repository);
	}
	
	@Override
	public Tema getTema(String nome) {
		return Tema.criar(this.getTemaRepository().getProximoId(), nome);
	}

	private TemaRepository getTemaRepository() {
		return (TemaRepository) this.getRepository();
	}
	
}
