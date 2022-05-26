package repositorios;

import dominio.Rodada;
import dominio.Jogador;

public interface RodadaRepository extends Repository {
	public void remover(Rodada rodada) throws RepositoryException;
	public void atualizar(Rodada rodada) throws RepositoryException;
	public void inserir(Rodada rodada) throws RepositoryException;
	public Rodada[] getPorJogador(Jogador jogador);
	public Rodada getPorId(long id);
}
