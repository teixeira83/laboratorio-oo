package br.edu.iff.jogoforca;

import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactoryImpl;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactory;
import br.edu.iff.jogoforca.dominio.rodada.sorteio.RodadaSorteioFactory;
import br.edu.iff.jogoforca.embdr.BDRRepositoryFactory;
import br.edu.iff.jogoforca.emmemoria.MemoriaRepositoryFactory;
import br.edu.iff.jogoforca.imagem.ElementoGraficoImagemFactory;
import br.edu.iff.jogoforca.texto.ElementoGraficoTextoFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactoryImpl;

public class Aplicacao {
	
	private final String[] TIPOS_REPOSITORY_FACTORY = { "memoria", "relacional" };
	private final String[] TIPOS_ELEMENTO_GRAFICO_FACTORY = { "texto", "imagem" };
	private final String[] TIPOS_RODADA_FACTORY = { "sorteio" };
	
    private static Aplicacao soleInstance;

	private String tipoRepositoryFactory = this.TIPOS_REPOSITORY_FACTORY[0];
    private String tipoElementoGraficoFactory = this.TIPOS_ELEMENTO_GRAFICO_FACTORY[0];
	private String tipoRodadaFactory = this.TIPOS_RODADA_FACTORY[0];
	
	private static JogadorFactory jogadorFactory;
	private static PalavraFactory palavraFactory;
	private static TemaFactory temaFactory;
	private static RodadaFactory rodadaFactory;
	private static ElementoGraficoFactory elementoGraficoFactory;
	private static RepositoryFactory repositoryFactory;
	
	public static Aplicacao getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new Aplicacao();
		}

		Aplicacao.soleInstance.configurar();
		return soleInstance;
	}
	
	private Aplicacao() {
		this.configurar();
	}
	
	public void configurar() {
		this.configurarElementoGrafico();
		
		if(this.tipoRepositoryFactory == "memoria") {
			this.configurarPersistenciaMemoria();
		}
		else if(this.tipoRepositoryFactory == "relacional") {
			this.configurarPersistenciaBDR();
		}
	}

	public String[] getTiposRepositoryFactory() {
		return this.TIPOS_REPOSITORY_FACTORY; 
	}

	public void setTipoRepositoryFactory(String tipo) {
		this.tipoRepositoryFactory = tipo;
		this.configurar();
	}
	
	public String[] getTiposElementoGraficoFactory() {
        return this.TIPOS_ELEMENTO_GRAFICO_FACTORY;	
	}
	
	public void setTipoElementoGraficoFactory(String tipo) {
        this.tipoElementoGraficoFactory = tipo;
		this.configurar();
	}
    
	public String[] getTiposRodadaFactory() {
        return this.TIPOS_RODADA_FACTORY;
	}
	
	public void setTipoRodadaFactory(String tipo) {
        this.tipoRodadaFactory = tipo;
		this.configurar();
	}
	
    public RepositoryFactory getRepositoryFactory() {
    	return repositoryFactory;
    }

    public ElementoGraficoFactory getElementoGraficoFactory() {
		return elementoGraficoFactory;
    }

	public RodadaFactory getRodadaFactory() {
		return rodadaFactory;
	}
	
	public TemaFactory getTemaFactory() {
		return temaFactory;
	}
	
	private void configurarPersistenciaMemoria() {
		MemoriaRepositoryFactory memoriaRepositoryFactory = MemoriaRepositoryFactory.getSoleInstance();
		
		JogadorFactoryImpl.createSoleInstance(
				memoriaRepositoryFactory.getJogadorRepository());
		
		PalavraFactoryImpl.createSoleInstance(
				memoriaRepositoryFactory.getPalavraRepository());
		
		TemaFactoryImpl.createSoleInstance(
				memoriaRepositoryFactory.getTemaRepository());
		
		
		if(this.tipoRodadaFactory == "sorteio") {
			RodadaSorteioFactory.createSoleInstance(
					memoriaRepositoryFactory.getRodadaRepository(),
					memoriaRepositoryFactory.getPalavraRepository(),
					memoriaRepositoryFactory.getTemaRepository()
			);	
		}
		
		jogadorFactory = JogadorFactoryImpl.getSoleInstance();
		palavraFactory = PalavraFactoryImpl.getSoleInstance();
		temaFactory = TemaFactoryImpl.getSoleInstance();
		rodadaFactory = RodadaSorteioFactory.getSoleInstance();
		repositoryFactory = memoriaRepositoryFactory;
	}
	
	private void configurarPersistenciaBDR() {
		BDRRepositoryFactory bdrRepositoryFactory = BDRRepositoryFactory.getSoleInstance();
		
		JogadorFactoryImpl.createSoleInstance(
				bdrRepositoryFactory.getJogadorRepository());
		
		PalavraFactoryImpl.createSoleInstance(
				bdrRepositoryFactory.getPalavraRepository());
		
		TemaFactoryImpl.createSoleInstance(
				bdrRepositoryFactory.getTemaRepository());
		
		if(this.tipoRodadaFactory == "sorteio") {
			RodadaSorteioFactory.createSoleInstance(
					bdrRepositoryFactory.getRodadaRepository(),
					bdrRepositoryFactory.getPalavraRepository(),
					bdrRepositoryFactory.getTemaRepository()
			);	
		}
		
		jogadorFactory = JogadorFactoryImpl.getSoleInstance();
		palavraFactory = PalavraFactoryImpl.getSoleInstance();
		temaFactory = TemaFactoryImpl.getSoleInstance();
		rodadaFactory = RodadaSorteioFactory.getSoleInstance();
		repositoryFactory = bdrRepositoryFactory;
	}
	
	private void configurarElementoGrafico() {
		if(this.tipoElementoGraficoFactory == "texto") {
			elementoGraficoFactory = ElementoGraficoTextoFactory.getSoleInstance();
		}
		else if(this.tipoElementoGraficoFactory == "imagem") {
			elementoGraficoFactory = ElementoGraficoImagemFactory.getSoleInstance();
		}
	}

	public PalavraFactory getPalavraFactory() {
		return palavraFactory;
	}
	
	public JogadorFactory getJogadorFactory() {
		return jogadorFactory;
	}
	
}
