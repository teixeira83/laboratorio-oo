package repositorios;

public class MemoriaRepositoryFactory implements RepositoryFactory {


	private static MemoriaRepositoryFactory soleInstance;
	
	private MemoriaRepositoryFactory() {}
	
	public static MemoriaRepositoryFactory getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new MemoriaRepositoryFactory();
		}
		return soleInstance;
	}

	@Override
	public JogadorRepository getJogadorRepository() {
		return MemoriaJogadorRepository.getSoleInstance();
	}

	@Override
	public RodadaRepository getRodadaRepository() {
		return MemoriaRodadaRepository.getSoleInstance();
	}

	@Override
	public TemaRepository getTemaRepository() {
		return MemoriaTemaRepository.getSoleInstance();
	}

	@Override
	public PalavraRepository getPalavraRepository() {
		return MemoriaPalavraRepository.getSoleInstance();
	}

}
