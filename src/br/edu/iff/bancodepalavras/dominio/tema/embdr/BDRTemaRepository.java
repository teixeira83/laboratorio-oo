package br.edu.iff.bancodepalavras.dominio.tema.embdr;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

public class BDRTemaRepository implements TemaRepository {

	private static BDRTemaRepository soleInstance;
	
	private BDRTemaRepository() {}
	
	public static BDRTemaRepository getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new BDRTemaRepository();
		}
		return soleInstance;
	}

	
	@Override
	public void remover(Tema tema) throws RepositoryException {
		
	}

	@Override
	public void atualizar(Tema tema) throws RepositoryException {
		
	}

	@Override
	public void inserir(Tema tema) throws RepositoryException {
		
	}

	@Override
	public Tema[] getTodos() {
		return null;
	}

	@Override
	public Tema getPorNome(String nome) {
		return null;
	}

	@Override
	public Tema getPorId(long id) {
		return null;
	}

	@Override
	public long getProximoId() {
		return 0;
	}
}
