package br.edu.iff.jogoforca.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.imagem.LetraImagemFactory;
import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.imagem.BonecoImagemFactory;

public class ElementoGraficoImagemFactory implements ElementoGraficoFactory {
	
	private LetraImagemFactory letraFactory;
	private BonecoImagemFactory bonecoFactory;

	private static ElementoGraficoImagemFactory soleInstance;
	
	private ElementoGraficoImagemFactory(){
		letraFactory = LetraImagemFactory.getSoleInstance();
		bonecoFactory = BonecoImagemFactory.getSoleInstance();
	}
	
	public static ElementoGraficoImagemFactory getSoleInstance() {
		if (soleInstance == null) {
			soleInstance = new ElementoGraficoImagemFactory();
		}
		return soleInstance;
	}
	 
	
	@Override
	public Boneco getBoneco() {
		return bonecoFactory.getBoneco();
	}

	@Override
	public Letra getLetra(char codigo) {
		return letraFactory.getLetra(codigo);
	}

	@Override
	public Letra getLetraEncoberta() {
		return letraFactory.getLetraEncoberta();
	}

}
