package jogo;

import testes.TestePalavra;
import testes.TesteRodada;

public class Jogo {
    public static void main(String[] args) throws Exception {
    	Aplicacao aplicacao = Aplicacao.getSoleInstance();
    	
    	TestePalavra testePalavra = new TestePalavra();
    	testePalavra.testaPalavra(aplicacao);
    	
    	TesteRodada testeRodada = new TesteRodada();
    	testeRodada.testaRodada(aplicacao);
    }
}
