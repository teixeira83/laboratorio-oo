package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.factory.EntityFactory;

public class PalavraFactoryImpl extends EntityFactory implements PalavraFactory {

	private static PalavraFactoryImpl soleInstance;
	
	private PalavraFactoryImpl(PalavraRepository repository) {
		super(repository);
	}

	public static PalavraFactoryImpl getSoleInstance() {
		return soleInstance;
	}
	
	public static void createSoleInstance(PalavraRepository repository){
		soleInstance = new PalavraFactoryImpl(repository);
	}
	
	@Override
	public Palavra getPalavra(String palavra, Tema tema) {
		return Palavra.criar(this.getPalavraRepository().getProximoId(), palavra, tema);
	}

	private PalavraRepository getPalavraRepository() {
		return (PalavraRepository) this.getRepository();
	}

}	
