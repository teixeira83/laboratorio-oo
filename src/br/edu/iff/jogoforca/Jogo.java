package br.edu.iff.jogoforca;

import br.edu.iff.testes.TestePalavra;
import br.edu.iff.testes.TesteRodada;

public class Jogo {
    public static void main(String[] args) throws Exception {
    	Aplicacao aplicacao = Aplicacao.getSoleInstance();
    	
    	TestePalavra testePalavra = new TestePalavra();
    	testePalavra.testaPalavra(aplicacao);
    	
    	TesteRodada testeRodada = new TesteRodada();
    	testeRodada.testaRodada(aplicacao);
    }
}
