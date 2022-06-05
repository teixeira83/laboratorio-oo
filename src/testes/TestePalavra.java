package testes;

import dominio.Palavra;
import dominio.Tema;
import jogo.Aplicacao;
import repositorios.RepositoryException;

public class TestePalavra {
	public void testaPalavra(Aplicacao aplicacao) throws RepositoryException {
		// ------- TESTES PARA CLASSE 'PALAVRA' -------
		
    	Palavra.setLetraFactory(aplicacao.getElementoGraficoFactory());
    	
    	aplicacao.getRepositoryFactory().getTemaRepository().inserir(
    			aplicacao.getTemaFactory().getTema("Nomes"));
    	
    	aplicacao.getRepositoryFactory().getTemaRepository().inserir(
    			aplicacao.getTemaFactory().getTema("Comidas"));
    	
    	Palavra p1 = Palavra.criar(
    			aplicacao.getRepositoryFactory().getPalavraRepository().getProximoId(), 
    			"Davi", aplicacao.getRepositoryFactory().getTemaRepository().getPorNome("Nomes"));
    	
    	aplicacao.getRepositoryFactory().getPalavraRepository().inserir(p1);
    	
    	Palavra p2 = Palavra.criar(
    			aplicacao.getRepositoryFactory().getPalavraRepository().getProximoId(), 
    			"Silas", aplicacao.getRepositoryFactory().getTemaRepository().getPorNome("Nomes"));
    	
    	aplicacao.getRepositoryFactory().getPalavraRepository().inserir(p2);
    	
    	Palavra p3 = Palavra.criar(
    			aplicacao.getRepositoryFactory().getPalavraRepository().getProximoId(), 
    			"Marcos", aplicacao.getRepositoryFactory().getTemaRepository().getPorNome("Nomes"));
    	
    	aplicacao.getRepositoryFactory().getPalavraRepository().inserir(p3);
    	
    	Palavra p4 = Palavra.criar(
    			aplicacao.getRepositoryFactory().getPalavraRepository().getProximoId(), 
    			"João", aplicacao.getRepositoryFactory().getTemaRepository().getPorNome("Nomes"));
    	
    	aplicacao.getRepositoryFactory().getPalavraRepository().inserir(p4);
    	
    	Palavra p5 = Palavra.criar(
    			aplicacao.getRepositoryFactory().getPalavraRepository().getProximoId(), 
    			"João", aplicacao.getRepositoryFactory().getTemaRepository().getPorNome("Nomes"));
    	
    	aplicacao.getRepositoryFactory().getPalavraRepository().inserir(p5);
    	
    	aplicacao.getRepositoryFactory().getPalavraRepository().remover(p4);;
	}
}
