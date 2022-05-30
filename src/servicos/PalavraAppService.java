package servicos;

import dominio.Palavra;
import fabricas.PalavraFactory;
import repositorios.PalavraRepository;
import repositorios.RepositoryException;
import repositorios.TemaRepository;

public class PalavraAppService {
	private PalavraRepository palavraRepository;
	private PalavraFactory palavraFactory;
	private TemaRepository temaRepository;
	
	private static PalavraAppService soleInstance;
	
	public static void createSoleInstance(TemaRepository temaRepository, 
							       		  PalavraRepository palavraRepository, 
							       		  PalavraFactory palavraFactory) {
		
		soleInstance = new PalavraAppService(temaRepository, 
												  palavraRepository, 
												  palavraFactory);
	}
	
	public static PalavraAppService getSoleInstance() {
		return soleInstance;
	}
	
	public boolean novaPalavra(String palavra, long idTema) {
		if(this.palavraRepository.getPalavra(palavra) != null) {
			return true;
		}
		else {
			// insere nova palavra no repositório
			try{
				palavraRepository.inserir(Palavra.criar(this.palavraRepository.getProximoId(), 
														palavra, 
														this.temaRepository.getPorId(idTema))
				);
				
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
