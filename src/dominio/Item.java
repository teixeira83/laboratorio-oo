package dominio;

import java.util.HashSet;
import java.util.Iterator;

public class Item extends ObjetoDominioImpl {

	private boolean[] posicoesDescobertas;
	private String palavraArriscada = null;
	private Palavra palavra;
	
	private Item(long id, Palavra palavra) {
		super(id);
		this.palavra = palavra;
		this.posicoesDescobertas = new boolean[this.palavra.getTamanho()];
		for(int p = 0; p < this.palavra.getTamanho(); p++) {
			this.posicoesDescobertas[p] = false;
		}
	}
	
    private Item(long id, Palavra palavra, boolean[] posicoesDescobertas, String palavraArriscada) {
		super(id);
		this.palavra = palavra;
		this.posicoesDescobertas = posicoesDescobertas;
		this.palavraArriscada = palavraArriscada;
	}
    
    static Item criar(long id, Palavra palavra) {
    	return new Item(id, palavra);
    }
    
    public static Item reconstituir(long id, Palavra palavra, boolean[] posicoesDescobertas, String palavraArriscada) {
    	return new Item(id, palavra, posicoesDescobertas, palavraArriscada);
    }
	
	public Palavra getPalavra() {
		return this.palavra;
	}

	public String getPalavraArriscada() {
		return this.palavraArriscada;
	}
	
	void arriscar(String palavra) {
		if(!this.arriscou()) { 
			this.palavraArriscada = palavra.toLowerCase();
		}
	}

	public boolean arriscou() {
		return this.palavraArriscada != null;
	}
	
	public void exibir(Object contexto) {
		this.palavra.exibir(posicoesDescobertas, contexto);
	}
	
	public boolean acertou() {
		return (this.palavraArriscada != null) && (this.palavra.comparar(this.palavraArriscada));
	}

	public boolean descobriu() {
		return this.acertou() || this.getLetrasEncobertas().length == 0;
	}
	
	public int calcularPontosLetrasEncobertas(int valorPorLetraEncoberta) {
		return valorPorLetraEncoberta * this.qtdeLetrasEncobertas();
	}
	
	boolean tentar(char codigo) {
		int[] acertos = this.palavra.tentar(codigo);
		for(int c = 0; c < acertos.length; c++) {
			this.posicoesDescobertas[acertos[c]] = true;
		}
		return acertos.length > 0;
	}

	public int qtdeLetrasEncobertas() {
		int qtd = 0;
		for(int c = 0; c < this.posicoesDescobertas.length; c++)
			if (!this.posicoesDescobertas[c])
				qtd++;
		return qtd;
	}
	
	public Letra[] getLetrasEncobertas(){
		return this.getLetrasPorStatus(false);
	}
	
	public Letra[] getLetrasDescobertas() {
		return this.getLetrasPorStatus(true);
	}

	
	private Letra[] getLetrasPorStatus(Boolean status) {
		HashSet<Letra> escolhidas = new HashSet<Letra>();
		for(int c = 0; c < this.palavra.getTamanho(); c++) {
			if(this.posicoesDescobertas[c] == status) {
				escolhidas.add(this.palavra.getLetra(c));
			}
		}	
		Letra[] letrasEscolhidas = new Letra[escolhidas.size()];
		Iterator<Letra> it = escolhidas.iterator();
		int c = 0;
		while(it.hasNext()) {
			letrasEscolhidas[c] = it.next();
			c++;
		}
		
		return letrasEscolhidas;
	}

}
