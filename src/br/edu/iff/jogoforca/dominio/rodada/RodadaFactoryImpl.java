package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.factory.EntityFactory;

public abstract class RodadaFactoryImpl extends EntityFactory implements RodadaFactory {

	private static PalavraRepository palavraRepository;
	private static TemaRepository temaRepository;
	private static RodadaRepository rodadaRepository;
	
	protected RodadaFactoryImpl(RodadaRepository repository, PalavraRepository palavraRepository, TemaRepository temaRepository){		
		super(repository);
		RodadaFactoryImpl.palavraRepository = palavraRepository;
		RodadaFactoryImpl.temaRepository = temaRepository;
		RodadaFactoryImpl.rodadaRepository = repository;
	}
	
	protected PalavraRepository getPalavraRepository() {
		return palavraRepository;
	}

	protected TemaRepository getTemaRepository() {
		return temaRepository;
	}
	
	protected RodadaRepository getRodadaRepository() {
		return rodadaRepository;
	}
}
