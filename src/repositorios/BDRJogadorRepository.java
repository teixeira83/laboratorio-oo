package repositorios;

import dominio.Jogador;

public class BDRJogadorRepository implements JogadorRepository {


	private static BDRJogadorRepository soleInstance;
	
	private BDRJogadorRepository() {}
	
	public static BDRJogadorRepository getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new BDRJogadorRepository();
		}
		return soleInstance;
	}

	
	@Override
	public void remover(Jogador jogador) throws RepositoryException {
		
	}

	@Override
	public void atualizar(Jogador jogador) throws RepositoryException {
		
	}

	@Override
	public void inserir(Jogador jogador) throws RepositoryException {
	
	}

	@Override
	public Jogador getPorNome(String nome) {
		return null;
	}

	@Override
	public Jogador getPorId(long id) {
		return null;
	}

	@Override
	public long getProximoId() {
		return 0;
	}
}
