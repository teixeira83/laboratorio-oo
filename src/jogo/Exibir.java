package jogo;

import java.util.ArrayList;

import dominio.Letra;
import dominio.Palavra;
import dominio.Rodada;

public class Exibir {
	
	private ArrayList<String> buffer = new ArrayList<String>();
	
	public void adicionar(String s) {
		this.buffer.add(s);
	};
	
	private void renderizar() {
		String saida = new String();
		for(String s : this.buffer)
			saida = saida.concat(s);
		System.out.println(saida);
		this.buffer.clear();
	}
	
	public void exibirTudo(Rodada r) {
		Palavra[] ps = r.getPalavras();
		for(Palavra p : ps) {
			p.exibir(this);
			this.adicionar(" ");
		}
		this.renderizar();
	}
	
	public void renderizarBoneco(Rodada r) {
		r.exibirBoneco(this);
		this.renderizar();
	}
    
	public void renderizarPalavras(Rodada r){
		Palavra[] ps = r.getPalavras();
		int[] tamanhos = new int[ps.length];
		int soma = 0;
		int t = 0;
		for(Palavra p : ps) {
			tamanhos[t] = p.getTamanho() + soma;
			soma += p.getTamanho();
			t++;
		}
		
		r.exibirItens(this);
		t = 0;
		String saida = "";
		for(int s = 0; s < this.buffer.size(); s++) {
			saida = saida.concat(this.buffer.get(s));
			if( s + 1 == tamanhos[t]) {
				saida = saida.concat(" ");
				t++;
			}
		}

		System.out.println(saida);
		this.buffer.clear();
	}
	
    public void mostrarTelaJogo(Rodada r) {
    	String status = "Em execucao";
    	String pontos = "";
    	String comando = "\nDigite: \n"
    			+ "2 - Tentar uma letra\n"
    			+ "3 - Arriscar palavras\n"
    			+ "0 - Sair\n"
    			+ "\nNúmero: ";
    	
    	if(r.encerrou()) {
    		status = "Encerrado";
    		pontos = " = "+ r.getJogador().getPontuacao() + " Pontos";
    		comando = "\nDigite para 1 novo jogo ou digite 0 para sair:";
    	}
    	String nome_jogador = r.getJogador().getNome() + pontos;
    	String tema = r.getTema().getNome();
    	Letra[] erradas = r.getErradas();
    	String erradasStr = " => ";
    	for(Letra l : erradas)
    		erradasStr = erradasStr + l.getCodigo() + " ";
    	
       	System.out.println("\n\nJogo da Forca");
    	System.out.println("=============\n");
    	System.out.println("Jogador: "+nome_jogador);
    	System.out.println("Status do Jogo: "+status+"\n");
    	System.out.println("Boneco:");
    	if(r.getQtdeErros() > 0) {
    		this.renderizarBoneco(r);
    	}
    	System.out.println("\nErros: "+r.getQtdeErros()+"/"+Rodada.getMaxErros()+erradasStr);
    	System.out.println("\nTema: "+tema);
    	this.renderizarPalavras(r);
    	System.out.println(comando);  	
    }
}
