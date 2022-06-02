package testes;

import dominio.Jogador;
import dominio.Rodada;
import fabricas.ElementoGraficoFactory;
import jogo.Aplicacao;
import jogo.Exibir;
import repositorios.RepositoryException;
import servicos.RodadaAppService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  

public class TesteRodada {
	
	private Rodada setaDadosRodada(Aplicacao aplicacao, Jogador jogador) throws RepositoryException {
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
		
		Jogador jogador = aplicacao.getJogadorFactory().getJogador("Davi");	
		
		Rodada rodada = this.setaDadosRodada(aplicacao, jogador);
		
		String letra;
		
		while(!sair) {
			String numero = "0";
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
			
			exibir.mostrarTelaJogo(rodada);
	         
	        numero = br.readLine();
	         
	        switch (numero) {
	        	case "1":
	        		aplicacao.getRepositoryFactory().getRodadaRepository().inserir(rodada);
	        		//aplicacao.getRepositoryFactory().getJogadorRepository().atualizar(jogador);
	        		// nova rodada
	        		rodada = this.setaDadosRodada(aplicacao, jogador);
        		break;
	        	case "2": 
	        		System.out.print("Letra:");
			        letra = br.readLine();
			        System.out.print("LETRA:" + letra);
			        rodada.tentar(letra.charAt(0));
			        System.out.println(rodada.getQtdeErros());
			        
			        break;
				
	        	case "3": 
	        		String[] palavrasArriscadas = new String[rodada.getNumPalavras()];
	        		
	        		for(int i = 0; i < rodada.getNumPalavras(); i++) {
	        			System.out.print("Palavra:");
				        palavrasArriscadas[i] = br.readLine();
	        		}
	        		rodada.arriscar(palavrasArriscadas);
	        		
	        		break;
				
	        	case "0":
	        		sair = true;
	        		break;
			} 
		}
		
		aplicacao.getRepositoryFactory().getJogadorRepository().inserir(jogador);
	}
}
