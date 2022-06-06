package br.edu.iff.jogoforca.embdr;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.palavra.embdr.BDRPalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.bancodepalavras.dominio.tema.embdr.BDRTemaRepository;
import br.edu.iff.jogoforca.RepositoryFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.jogoforca.dominio.jogador.embdr.BDRJogadorRepository;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.jogoforca.dominio.rodada.embdr.BDRRodadaRepository;

public class BDRRepositoryFactory implements RepositoryFactory {

	private static BDRRepositoryFactory soleInstance;
	
	private BDRRepositoryFactory() {}
	
	public static BDRRepositoryFactory getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new BDRRepositoryFactory();
		}
		return soleInstance;
	}
	
	@Override
	public JogadorRepository getJogadorRepository() {
		return BDRJogadorRepository.getSoleInstance();
	}

	@Override
	public RodadaRepository getRodadaRepository() {
		return BDRRodadaRepository.getSoleInstance();
	}

	@Override
	public TemaRepository getTemaRepository() {
		return BDRTemaRepository.getSoleInstance();
	}

	@Override
	public PalavraRepository getPalavraRepository() {
		return BDRPalavraRepository.getSoleInstance();
	}

}
