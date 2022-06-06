package br.edu.iff.bancodepalavras.dominio.palavra.embdr;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.repository.RepositoryException;

public class BDRPalavraRepository implements PalavraRepository {


	private static BDRPalavraRepository soleInstance;
	
	private BDRPalavraRepository() {}
	
	public static BDRPalavraRepository getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new BDRPalavraRepository();
		}
		return soleInstance;
	}

	
	@Override
	public long getProximoId() {
		return 0;
	}

	@Override
	public Palavra getPalavra(String palavra) {
		return null;
	}

	@Override
	public Palavra[] getTodas() {
		return null;
	}

	@Override
	public Palavra[] getPorTema(Tema tema) {
		return null;
	}

	@Override
	public Palavra getPorId(long id) {
		return null;
	}

	@Override
	public void remover(Palavra palavra) throws RepositoryException {
	}

	@Override
	public void atualizar(Palavra palavra) throws RepositoryException {
	}

	@Override
	public void inserir(Palavra palavra) throws RepositoryException {	
	}
}
