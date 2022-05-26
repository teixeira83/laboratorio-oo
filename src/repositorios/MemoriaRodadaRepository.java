package repositorios;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import dominio.Jogador;
import dominio.Rodada;

public class MemoriaRodadaRepository implements RodadaRepository {

	private static MemoriaRodadaRepository soleInstance;
	private HashMap<Long, Rodada> pool = new HashMap<Long, Rodada>();
	
	private MemoriaRodadaRepository() {}
	
	public static MemoriaRodadaRepository getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new MemoriaRodadaRepository();
		}
		return soleInstance;
	}

	@Override
	public void inserir(Rodada rodada) throws RepositoryException {
		if (this.pool.containsKey(rodada.getId())){
			throw new RepositoryException();
		}
		this.pool.put(rodada.getId(), rodada);
	}
	
	@Override
	public void remover(Rodada rodada) throws RepositoryException {
		if (!this.pool.containsKey(rodada.getId())){
			throw new RepositoryException();
		}
		this.pool.remove(rodada.getId());
	}

	@Override
	public void atualizar(Rodada rodada) throws RepositoryException {
		this.remover(rodada);
		this.inserir(rodada);
		
	}

	@Override
	public Rodada[] getPorJogador(Jogador jogador) {
		if(this.pool.size() < 1)
			return new Rodada[0];
		
		HashSet<Rodada> rodadasDoJogador = new HashSet<Rodada>();		
		Iterator<Rodada> rodadas = this.pool.values().iterator();
		while(rodadas.hasNext()) {
			Rodada rodada = rodadas.next();
			if (rodada.getJogador().equals(jogador)) {
				rodadasDoJogador.add(rodada);
			}
		}
		return (Rodada[])rodadasDoJogador.toArray(); 
	}

	@Override
	public Rodada getPorId(long id) {
		return this.pool.get(id);
	}

	@Override
	public long getProximoId() {
		Long max = (long) 1;
		
		if(this.pool.size() < 1)
			return max;		
		
		Iterator<Rodada> rodadas = this.pool.values().iterator();
		while(rodadas.hasNext()) {
			Long id = rodadas.next().getId(); 
			if ( id > max) {
				max = id;
			}
		}
		return max + 1;
	}

}
