package fabricas;

import repositorios.PalavraRepository;
import repositorios.RodadaRepository;
import repositorios.TemaRepository;

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
