package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

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
			try{
				palavraRepository.inserir(
						this.palavraFactory.getPalavra(palavra, 
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