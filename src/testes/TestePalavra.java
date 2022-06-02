package testes;

import dominio.Palavra;
import dominio.Tema;
import jogo.Aplicacao;
import repositorios.RepositoryException;

public class TestePalavra {
	public void testaPalavra(Aplicacao aplicacao) throws RepositoryException {
		// ------- TESTES PARA CLASSE 'PALAVRA' -------
		
    	Palavra.setLetraFactory(aplicacao.getElementoGraficoFactory());
    	
    	Tema tema = Tema.criar(1, "Nomes");
    	
    	aplicacao.getRepositoryFactory().getTemaRepository().inserir(tema);
    	
    	Palavra p1 = Palavra.criar(
    			aplicacao.getRepositoryFactory().getPalavraRepository().getProximoId(), 
    			"Davi", tema);
    	
    	aplicacao.getRepositoryFactory().getPalavraRepository().inserir(p1);
    	
    	Palavra p2 = Palavra.criar(
    			aplicacao.getRepositoryFactory().getPalavraRepository().getProximoId(), 
    			"Silas", tema);
    	
    	aplicacao.getRepositoryFactory().getPalavraRepository().inserir(p2);
    	
    	Palavra p3 = Palavra.criar(
    			aplicacao.getRepositoryFactory().getPalavraRepository().getProximoId(), 
    			"Marcos", tema);
    	
    	aplicacao.getRepositoryFactory().getPalavraRepository().inserir(p3);
    	
    	Palavra p4 = Palavra.criar(
    			aplicacao.getRepositoryFactory().getPalavraRepository().getProximoId(), 
    			"João", tema);
    	
    	aplicacao.getRepositoryFactory().getPalavraRepository().inserir(p4);
    	
    	Palavra p5 = Palavra.criar(
    			aplicacao.getRepositoryFactory().getPalavraRepository().getProximoId(), 
    			"João", tema);
    	
    	aplicacao.getRepositoryFactory().getPalavraRepository().inserir(p5);
    	
    	aplicacao.getRepositoryFactory().getPalavraRepository().remover(p4);;
	}
}
