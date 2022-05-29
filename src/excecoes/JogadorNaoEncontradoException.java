package excecoes;

public class JogadorNaoEncontradoException extends java.lang.Exception{
	private static final long serialVersionUID = 1L;
	
	private String jogador;

	public JogadorNaoEncontradoException(String jogador) {
		super();
		this.jogador = jogador;
	}
	
	public String getJogador() {
		return this.jogador;
	}
}
