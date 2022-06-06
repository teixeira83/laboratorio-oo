package br.edu.iff.jogoforca.dominio.rodada.sorteio;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactory;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;

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
		
		Tema tema = null;
		
		// seleção apenas de tema que possua ao menos uma palavra
		do{
			tema = temas[random.nextInt(temas.length)];
		}while(this.getPalavraRepository().getPorTema(tema).length == 0);
		
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