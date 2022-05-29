package servicos;

import dominio.Jogador;
import dominio.Rodada;
import excecoes.JogadorNaoEncontradoException;
import fabricas.RodadaFactory;
import repositorios.JogadorRepository;
import repositorios.RepositoryException;
import repositorios.RodadaRepository;

public class RodadaAppService{
	public RodadaFactory rodadaFactory;
	public RodadaRepository rodadaRepository;
	public JogadorRepository jogadorRepositorio;
	
	private static RodadaAppService soleInstance;
	
	public static void createSoleInstance(RodadaFactory rodadaFactory, 
								   RodadaRepository rodadaRepository,
								   JogadorRepository jogadorRepository) {
		
		soleInstance = new RodadaAppService(rodadaFactory, 
											rodadaRepository, 
											jogadorRepository);
	}
	
	public static RodadaAppService getSoleInstance() {
		return soleInstance;
	}

	private RodadaAppService(RodadaFactory rodadaFactory, 
							RodadaRepository rodadaRepository,
							JogadorRepository jogadorRepositorio) {
		
		super();
		this.rodadaFactory = rodadaFactory;
		this.rodadaRepository = rodadaRepository;
		this.jogadorRepositorio = jogadorRepositorio;
	}
	
	public Rodada novaRodada(long idJogador) throws JogadorNaoEncontradoException {
		try {
			return rodadaFactory.getRodada(jogadorRepositorio.getPorId(idJogador));
			
			
		}
		catch(Exception e) {
			throw new JogadorNaoEncontradoException("JogadorID - " + idJogador);
		}
	}
	
	public Rodada novaRodada(String nomeJogador) throws JogadorNaoEncontradoException {
		try {
			return rodadaFactory.getRodada(jogadorRepositorio.getPorNome(nomeJogador));
			
		}
		catch(Exception e) {
			throw new JogadorNaoEncontradoException(nomeJogador);
		}
	}
	
	public Rodada novaRodada(Jogador jogador) {
		return rodadaFactory.getRodada(jogador);
	}
	
	public boolean salvarRodada(Rodada rodada) {
		try {
			this.rodadaRepository.inserir(rodada);
			return true;
		}
		catch(RepositoryException e) {
			return false;
		}
	}
}
