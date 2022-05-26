package repositorios;

import dominio.Tema;

public interface TemaRepository extends Repository {
	public void remover(Tema tema) throws RepositoryException;
	public void atualizar(Tema tema) throws RepositoryException;
	public void inserir(Tema tema) throws RepositoryException;
	public Tema[] getTodos();
	public Tema getPorNome(String nome);
	public Tema getPorId(long id);
}
