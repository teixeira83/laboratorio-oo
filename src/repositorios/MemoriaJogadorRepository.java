package repositorios;

import java.util.HashMap;
import java.util.Iterator;

import dominio.Jogador;


public class MemoriaJogadorRepository implements JogadorRepository {

	private static MemoriaJogadorRepository soleInstance;
	private HashMap<Long, Jogador> pool= new HashMap<Long, Jogador>();
	
	private MemoriaJogadorRepository() {}
	
	public static MemoriaJogadorRepository getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new MemoriaJogadorRepository();
		}
		return soleInstance;
	}

	@Override
	public void inserir(Jogador jogador) throws RepositoryException {
		if (this.pool.containsKey(jogador.getId())) {
			throw new RepositoryException();
		}
		this.pool.put(jogador.getId(), jogador);
	}

	
	@Override
	public void remover(Jogador jogador) throws RepositoryException {
		if (!this.pool.containsKey(jogador.getId())) {
			throw new RepositoryException();
		}
		this.pool.remove(jogador.getId());
	}

	@Override
	public void atualizar(Jogador jogador) throws RepositoryException {
		this.remover(jogador);
		this.inserir(jogador);
	}

	@Override
	public Jogador getPorNome(String nome) {
		if(this.pool.size() < 1)
			return null;
		
		Iterator<Jogador> jogadores = this.pool.values().iterator();
		while(jogadores.hasNext()) {
			Jogador jogador = jogadores.next();
			if (jogador.getNome().equals(nome)) {
				return jogador;
			}
		}
		return null;
	}

	@Override
	public Jogador getPorId(long id) {
		return this.pool.get(id);
	}

	@Override
	public long getProximoId() {
		Long max = (long) 1;
		
		if(this.pool.size() < 1)
			return max;
		
		Iterator<Jogador> jogadores = this.pool.values().iterator();
		while(jogadores.hasNext()) {
			Long id = jogadores.next().getId();
			if ( id > max) {
				max = id;
			}
		}
		return max + 1;
	}

	@Override
	public Jogador getTopJogador() {
		int maiorPontuacao = 0;
		Jogador jogadorComMaiorPontuacao = null;
		
		for(Jogador j : this.pool.values()) {
			if(j.getPontuacao() > maiorPontuacao) {
				jogadorComMaiorPontuacao = j;
				maiorPontuacao = j.getPontuacao();
			}
		}
		
		return jogadorComMaiorPontuacao;
	}
}
