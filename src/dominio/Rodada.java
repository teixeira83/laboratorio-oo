package dominio;

import java.util.HashSet;

import fabricas.BonecoFactory;

public class Rodada extends ObjetoDominioImpl {
	private static int maxPalavras = 3;
	private static int maxErros = 10;
	private static int pontosQuandoDescobreTodasAsPalavras = 100; 
	private static int pontosPorLetraEncoberta = 15;
	
	private Jogador jogador;
	private Item[] itens;
	private Letra[] erradas;
	
	private static BonecoFactory bonecoFactory;
	
	public static int getMaxPalavras() {
		return maxPalavras;
	}
	
	public static void setMaxPalavras(int max) {
		maxPalavras = max;
	}
	
	public static int getMaxErros() {
		return maxErros;
	}
	
	public static void setMaxErros(int max) {
		maxErros = max;
	}
	
	public static int pontosQuandoDescobreTodasAsPalavras() {
		return pontosQuandoDescobreTodasAsPalavras;
	}
	
	public static void pontosQuandoDescobreTodasAsPalavras(int pontos) {
		pontosQuandoDescobreTodasAsPalavras = pontos;
	}
	
	public static int pontosPorLetraEncoberta() {
		return pontosPorLetraEncoberta;
	}
	
	public static void pontosPorLetraEncoberta(int pontos) {
		pontosPorLetraEncoberta = pontos;
	}
	
	public static void setBonecoFactory(BonecoFactory factory) {
		bonecoFactory = factory;
	}
	
	public static BonecoFactory getBonecoFactory() {
		return bonecoFactory;
	}
	
	public static Rodada criar(long id, Palavra[] palavras, Jogador jogador) {
		return new Rodada(id, palavras, jogador);
	}
	
	public static Rodada reconstituir(long id, Item[] itens, Letra[] erradas, Jogador jogador) {
		return new Rodada(id, itens, erradas, jogador);
	}
	
	private Rodada(long id, Palavra[] palavras, Jogador jogador) {
		super(id);
		this.jogador = jogador;
		int qtdPalavras = palavras.length;
		System.out.println(qtdPalavras);
		this.itens = new Item[qtdPalavras];
		for(int c = 0; c < qtdPalavras; c++) {
			this.itens[c] = Item.criar(id, palavras[c]);
		}
		this.erradas = new Letra[0];
	}
	
	private Rodada(long id, Item[] itens, Letra[] erradas, Jogador jogador) {
		super(id);
		this.itens = itens;
		this.erradas = erradas;
		this.jogador = jogador;
	}
	
	public int getNumPalavras() {
		return itens.length;
	}
	
	public Palavra[] getPalavras() {
		Palavra[] palavras = new Palavra[this.itens.length];
		for(int c = 0; c < palavras.length; c++) {
			palavras[c] = this.itens[c].getPalavra();
		}
		return palavras;
	}
	
	public Tema getTema() {
		return this.itens[0].getPalavra().getTema();
	}
	
	public Jogador getJogador() {
		return this.jogador;
	}
	
	public void exibirItens(Object contexto) {
		for(Item i : this.itens)
			i.exibir(contexto);	
	}

	public void exibirBoneco(Object contexto) {
		bonecoFactory.getBoneco().exibir(contexto, this.getQtdeErros());
	}

	public void exibirPalavras(Object contexto) {
		for(Item i : this.itens) {
			Palavra p = i.getPalavra();
			boolean[] posicoes = new boolean[p.getTamanho()]; 
			for(int b = 0; b < p.getTamanho(); b++)
				posicoes[b] = true;
			p.exibir(posicoes, contexto);
		}
	}
	
	public void exibirLetrasErradas(Object contexto) {
		//falta preencher
	}
	
	public void arriscar(String[] palavras) {
		if (!this.encerrou()) {
			for(int i = 0; i < this.itens.length; i++) {
				this.itens[i].arriscar(palavras[i]);
			}
			
			if(this.encerrou())
				this.jogador.setPontuacao(this.jogador.getPontuacao() + this.calcularPontos());
		}
		else {
			throw new RuntimeException();
		}
		
	}
		
	public int getQtdeAcertos() {
		int acertos = 0;
		for(Item i : this.itens)
			acertos += i.getLetrasDescobertas().length;
		return acertos;
	}
	
	public int getQtdeErros() {
		return this.erradas.length;
	}
	
	public int getQtdeTentativas() {
		return this.getTentativas().length;
	}
	
	public int getQtdeTentativaRestantes() {
		return maxErros - this.getQtdeErros();
	}
	
	public boolean arriscou() {
		return this.itens[0].arriscou();
	}
	
	public boolean descobriu() {
		for(Item i : this.itens)
			if (!i.descobriu())
				return false;
		return true;
	}
	
	public boolean encerrou() {
		return this.descobriu() || this.arriscou() || this.erradas.length == maxErros;
	}
	
	public int calcularPontos() {
		if(!this.descobriu())
			return 0;
		
		int qtdLetrasEncobertas = 0;
		for(Item i : this.itens)
			qtdLetrasEncobertas += i.qtdeLetrasEncobertas();

		return pontosQuandoDescobreTodasAsPalavras + (pontosPorLetraEncoberta * qtdLetrasEncobertas);
		
	}
	
	public Letra[] getErradas() {
		return this.erradas;
	}

	public Letra[] getCertas() {
		HashSet<Letra> certas = new HashSet<Letra>();
		for(Item i: this.itens) {
			for(Letra l : i.getLetrasDescobertas()) {
				certas.add(l);
			}
		}
		return (Letra[])certas.toArray(new Letra[0]);
	}

	public Letra[] getTentativas() {
		HashSet<Letra> tentativas = new HashSet<Letra>();
		for(Letra l : this.getCertas())
			tentativas.add(l);
		
		for(Letra l : this.getErradas())
			tentativas.add(l);
		
		Letra[] letras = new Letra[tentativas.size()];
		int q = 0;
		for(Letra l : tentativas) {
			letras[q] = l;
			q++;
		}
		
		return letras;
	}
	
	public void tentar(char codigo) {
		if(!this.encerrou()) {
			Boolean errou = true;
			if (!this.encerrou())
				for(Item i : this.itens)
					if(i.tentar(codigo))
						errou = false;
			if(errou) {
				Letra[] novas = new Letra[this.erradas.length + 1];
				for(int l = 0; l < this.erradas.length ; l++)
					novas[l] = this.erradas[l];
				Letra nova = Palavra.getLetraFactory().getLetra(codigo);
				novas[novas.length - 1] = nova;
				this.erradas = novas;
			}
			
			if(this.encerrou()) 
				this.jogador.setPontuacao(this.jogador.getPontuacao() + this.calcularPontos());
		}
		else {
			throw new RuntimeException();
		}
	}

}
