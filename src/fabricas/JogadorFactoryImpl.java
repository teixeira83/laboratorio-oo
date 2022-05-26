package fabricas;

import dominio.Jogador;
import repositorios.JogadorRepository;
import repositorios.RepositoryException;

public class JogadorFactoryImpl extends EntityFactory implements JogadorFactory {

	private static JogadorFactoryImpl soleInstance;
	
	private JogadorFactoryImpl(JogadorRepository repository) {
		super(repository);
	}

	public static JogadorFactoryImpl getSoleInstance() {
		return soleInstance;
	}
	
	public static void createSoleInstance(JogadorRepository repository){
		soleInstance = new JogadorFactoryImpl(repository);
	}
	
	@Override
	public Jogador getJogador(String nome) {
		JogadorRepository repo = this.getJogadorRepository();
		Jogador jogador = repo.getPorNome(nome);
		if (jogador == null) {
			long novo_id = repo.getProximoId();
			jogador = Jogador.criar(novo_id, nome, 0);
			try {
				this.getJogadorRepository().inserir(jogador);
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
		return jogador;
	}

	private JogadorRepository getJogadorRepository() {
		return (JogadorRepository) this.getRepository();
	}


}
