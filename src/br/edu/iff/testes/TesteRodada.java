package br.edu.iff.testes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.exibir.Exibir;
import br.edu.iff.jogoforca.Aplicacao;
import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;
import br.edu.iff.repository.RepositoryException;  

public class TesteRodada {
	
	private Rodada setaNovaRodada(Aplicacao aplicacao, Jogador jogador) throws RepositoryException {
		ElementoGraficoFactory elementoGraficoFactory = aplicacao.getElementoGraficoFactory();
		
		RodadaAppService.createSoleInstance(
				aplicacao.getRodadaFactory(), 
				aplicacao.getRepositoryFactory().getRodadaRepository(), 
				aplicacao.getRepositoryFactory().getJogadorRepository());
		
		RodadaAppService rodadaAppService = RodadaAppService.getSoleInstance();
		
		Rodada rodada = rodadaAppService.novaRodada(jogador);
		
		rodada.setBonecoFactory(elementoGraficoFactory);
		
		return rodada;
	}
	
	public void testaRodada(Aplicacao aplicacao) throws RepositoryException, IOException {
		// ------- TESTES PARA CLASSE 'RODADA' -------
		Exibir exibir = new Exibir();
		boolean sair = false; 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	
		String numero = "0";
		String letra;
		
		Rodada rodada = null;
		
		PalavraAppService.createSoleInstance(
				aplicacao.getRepositoryFactory().getTemaRepository(),
				aplicacao.getRepositoryFactory().getPalavraRepository(),
				aplicacao.getPalavraFactory());
		
		PalavraAppService palavraAppService = PalavraAppService.getSoleInstance();
	
		while(!sair) {
			if(rodada != null) {
	        	exibir.mostrarTelaJogo(rodada);
		        numero = br.readLine();
	        }
			else {
				exibir.mostrarMenu();
				numero = br.readLine();
			}
			
	        switch (numero) {
	        	case "1": 
	        		System.out.print("Letra:");
			        letra = br.readLine().trim();
			        System.out.print("LETRA:" + letra);
			        rodada.tentar(letra.charAt(0));
			        System.out.println(rodada.getQtdeErros());
			        
			        if(rodada.encerrou()) {
			        	aplicacao.getRepositoryFactory().getJogadorRepository().atualizar(rodada.getJogador());
			        	exibir.mostrarTelaJogo(rodada);
			        	rodada = null;
			        }
			        
			        break;
				
	        	case "2": 
	        		String[] palavrasArriscadas = new String[rodada.getNumPalavras()];
	        		
	        		for(int i = 0; i < rodada.getNumPalavras(); i++) {
	        			System.out.print("Palavra:");
				        palavrasArriscadas[i] = br.readLine().trim();
	        		}
	        		rodada.arriscar(palavrasArriscadas);
	        		
	        		if(rodada.encerrou()) {
			        	aplicacao.getRepositoryFactory().getJogadorRepository().atualizar(rodada.getJogador());
			        	exibir.mostrarTelaJogo(rodada);
			        	rodada = null;
			        }
	        		
	        		break;
	        		
	        	case "3":
	        		System.out.print("Nome do jogador:");
			        String nomeJogador = br.readLine();
			        Jogador jogador = aplicacao.getJogadorFactory().getJogador(nomeJogador);
			        
			        aplicacao.getRepositoryFactory().getJogadorRepository().inserir(jogador);
	        		
	        		// nova rodada
	        		rodada = this.setaNovaRodada(
	        				aplicacao, 
	        				jogador);
        		break;
        		
	        	case "4":
	        		Jogador jogadorComMaiorPontuacao = aplicacao.getRepositoryFactory().getJogadorRepository().getTopJogador();
	        		System.out.println("Jogador - " + jogadorComMaiorPontuacao.getNome()
	        						+". Pontuação - " + jogadorComMaiorPontuacao.getPontuacao() + "\n");
	        		
        		break;
        		
	        	case "5":
	        		System.out.print("Novo tema:");
			        String tema = br.readLine();
			        aplicacao.getRepositoryFactory().getTemaRepository().inserir(aplicacao.getTemaFactory().getTema(tema));
        		break;
        		
	        	case "6":
	        		Tema[] temas = aplicacao.getRepositoryFactory().getTemaRepository().getTodos();
	        		System.out.print("Selecione o número do tema:\n");
	        		for(int i = 0; i < temas.length; i++) {
	        			System.out.print(temas[i].getId() + " - " + temas[i].getNome() + "\n");
	        		}
	        		int idTema = Integer.parseInt(br.readLine());
	        		
	        		System.out.print("Novo palavra:");
	        		String palavra = null;
			        palavra = br.readLine();
			        
			        palavraAppService.novaPalavra(palavra, idTema);
        		break;
				
	        	case "0":
	        		sair = true;
	        		break;
			} 
		}
	}
}
