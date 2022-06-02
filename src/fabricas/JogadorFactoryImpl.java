package fabricas;

import dominio.Jogador;
import repositorios.JogadorRepository;

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
		return Jogador.criar(this.getJogadorRepository().getProximoId(), nome, 0);
	}

	private JogadorRepository getJogadorRepository() {
		return (JogadorRepository) this.getRepository();
	}


}
