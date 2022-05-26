package repositorios;

import dominio.Jogador;
import dominio.Rodada;

public class BDRRodadaRepository implements RodadaRepository {

	private static BDRRodadaRepository soleInstance;
	
	private BDRRodadaRepository() {}
	
	public static BDRRodadaRepository getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new BDRRodadaRepository();
		}
		return soleInstance;
	}
	
	@Override
	public void remover(Rodada rodada) throws RepositoryException {
		
	}

	@Override
	public void atualizar(Rodada rodada) throws RepositoryException {
		
	}

	@Override
	public void inserir(Rodada rodada) throws RepositoryException {
		
	}

	@Override
	public Rodada[] getPorJogador(Jogador jogador) {
		return null;
	}

	@Override
	public Rodada getPorId(long id) {
		return null;
	}

	@Override
	public long getProximoId() {
		return 0;
	}
}
