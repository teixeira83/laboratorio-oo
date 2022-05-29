package servicos;

import fabricas.PalavraFactory;
import repositorios.PalavraRepository;
import repositorios.RepositoryException;
import repositorios.TemaRepository;

public class PalavraAppService {
	private PalavraRepository palavraRepository;
	private PalavraFactory palavraFactory;
	private TemaRepository temaRepository;
	
	private PalavraAppService soleInstance;
	
	public void createSoleInstance(TemaRepository temaRepository, 
							       PalavraRepository palavraRepository, 
							       PalavraFactory palavraFactory) {
		
		this.soleInstance = new PalavraAppService(temaRepository, 
												  palavraRepository, 
												  palavraFactory);
	}
	
	public PalavraAppService getSoleInstance() {
		return this.soleInstance;
	}
	
	public boolean novaPalavra(String palavra, long idTema) {
		if(this.palavraRepository.getPalavra(palavra) != null) {
			return true;
		}
		else {
			// insere nova palavra no repositório
			try{
				palavraRepository.inserir(this.palavraFactory.getPalavra(
					palavra, this.temaRepository.getPorId(idTema)));
				return true;
			}
			catch (RepositoryException e) {
				return false;
			}
		}
	}

	private PalavraAppService(TemaRepository temaRepository, 
						     PalavraRepository palavraRepository, 
						     PalavraFactory palavraFactory) {
		
		super();
		this.palavraRepository = palavraRepository;
		this.palavraFactory = palavraFactory;
		this.temaRepository = temaRepository;
	}
}
