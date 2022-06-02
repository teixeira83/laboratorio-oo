package fabricas;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import dominio.Jogador;
import dominio.Palavra;
import dominio.Rodada;
import dominio.Tema;
import repositorios.PalavraRepository;
import repositorios.RodadaRepository;
import repositorios.TemaRepository;

public class RodadaSorteioFactory extends RodadaFactoryImpl implements RodadaFactory {

	private static RodadaSorteioFactory soleInstance;
	
	private RodadaSorteioFactory(RodadaRepository repository, PalavraRepository palavraRepository, TemaRepository temaRepository){
		super(repository, palavraRepository, temaRepository);
	}
	
	public static RodadaSorteioFactory getSoleInstance() {
		return soleInstance;
	}
	
	public static void createSoleInstance(RodadaRepository repository, PalavraRepository palavraRepository, TemaRepository temaRepository){
		soleInstance = new RodadaSorteioFactory(repository, palavraRepository, temaRepository);
	}

	@Override
	public Rodada getRodada(Jogador jogador) {
		Random random = new Random();
		
		Tema[] temas = this.getTemaRepository().getTodos();
		
		Tema tema = temas[random.nextInt(temas.length)];
		
		Palavra[] palavrasDoTema = this.getPalavraRepository().getPorTema(tema);
	
		int quantPalavras = random.nextInt(Rodada.getMaxPalavras()) + 1;
		
		// caso a quantidade de palavras salvas for menor que o número de palavras escolhido
		if(palavrasDoTema.length < quantPalavras) {
			quantPalavras = palavrasDoTema.length;
		}
		
		HashSet<Palavra> escolhidas = new HashSet<Palavra>();
		do {
			Palavra palavraSorteada = palavrasDoTema[random.nextInt(palavrasDoTema.length)];
			
			if(!escolhidas.contains(palavraSorteada)){
				escolhidas.add(palavraSorteada);
			}
			
		}while(escolhidas.size() < quantPalavras);
		
		Palavra[] palavras =  new Palavra[escolhidas.size()];
		int p = 0;
		Iterator<Palavra> it = escolhidas.iterator();
		while(it.hasNext()) {
			palavras[p] = it.next();
			p++;
		}
		
		return Rodada.criar(this.getRodadaRepository().getProximoId() , palavras, jogador);
	}
}
