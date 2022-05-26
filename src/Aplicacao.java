import fabricas.JogadorFactory;
import fabricas.JogadorFactoryImpl;
import fabricas.PalavraFactory;
import fabricas.PalavraFactoryImpl;
import repositorios.RepositoryFactory;
public class Aplicacao {
	
	private final String[] TIPOS_REPOSITORY_FACTORY = { "memoria", "relacional" };
	private final String[] TIPOS_ELEMENTO_GRAFICO_FACTORY = { "texto", "imagem" };
	private final String[] TIPOS_RODADA_FACTORY = { "sorteio" };
	
    private static Aplicacao soleInstance;

	private String tipoRepositoryFactory = this.TIPOS_REPOSITORY_FACTORY[0];
    private String tipoElementoGraficoFactory = this.TIPOS_ELEMENTO_GRAFICO_FACTORY[0];
	private String tipoRodadaFactory = this.TIPOS_RODADA_FACTORY[0];
	
	public static Aplicacao getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new Aplicacao();
		}

		Aplicacao.soleInstance.configurar();
		return soleInstance;
	}
	
	private Aplicacao() {}
	
	public void configurar() {
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
		RepositoryFactory factory = null;
		String tipo = this.tipoRepositoryFactory;
		
		if (tipo.equals("memoria")) {
			// factory = MemoriaRepositoryFactory.getSoleInstance();
		}
		
		if (tipo.equals("relacional")) {
			// factory = BDRRepositoryFactory.getSoleInstance();
		}
		
		return factory;
    }

    // public ElementoGraficoFactory getElementoGraficoFactory() {
    // }

	// public RodadaFactory getRodadaFactory() {
	// 	RodadaFactory factory = null;
	// 	if (this.tipoRodadaFactory.equals("sorteio")) {
	// 		factory = RodadaSorteioFactory.getSoleInstance();
	// 	};
	// 	return factory;
	// }
	
	// public TemaFactory getTemaFactory() {
	// }
	
	public PalavraFactory getPalavraFactory() {
		return PalavraFactoryImpl.getSoleInstance();
	}
	
	public JogadorFactory getJogadorFactory() {
		return JogadorFactoryImpl.getSoleInstance();
	}
	
}
