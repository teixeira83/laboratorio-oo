package br.edu.iff.bancodepalavras.dominio.palavra;
import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;

import java.util.ArrayList;
import java.lang.Character;

public class Palavra extends ObjetoDominioImpl {
	
	private static LetraFactory letraFactory;
	
	private Tema tema;
	private Letra encoberta;
	private Letra[] letras;
	
	public static void setLetraFactory(LetraFactory factory) {
		letraFactory = factory;
	}
	
	public static LetraFactory getLetraFactory() {
		return letraFactory;
	}
	
	private Palavra(long id, String palavra, Tema tema) {
		super(id);
		this.tema = tema;
		this.encoberta = letraFactory.getLetraEncoberta();
		this.encoberta.toString();
		this.letras = new Letra[palavra.length()];
		
		for(int c = 0; c < palavra.length(); c++) {
			this.letras[c] = letraFactory.getLetra(Character.toLowerCase(palavra.charAt(c)));
		}
	}
	
	public static Palavra criar(long id, String palavra, Tema tema) {
		if(letraFactory != null)
			return new Palavra(id, palavra, tema);
		else
			return null;
	}
	
	public static Palavra reconstituir(long id, String palavra, Tema tema) {
		return new Palavra(id, palavra, tema);
	}
	
	public int getTamanho() {
		return letras.length;
	}
	
	public boolean comparar(String palavra) {
		String p = "";
		for(Letra l : this.letras)
			p = p.concat(String.valueOf(l.getCodigo()));
		return p.toLowerCase().equals(palavra.toLowerCase());
	}
	
	public Tema getTema() {
		return this.tema;
	}
	
	public int[] tentar(char codigo) {
		ArrayList<Integer> posicoesList  = new ArrayList<Integer>();
		for(int c = 0; c < this.letras.length ; c++) {
			if (Character.toLowerCase(codigo) == this.letras[c].toString().toLowerCase().charAt(0)) {
				posicoesList.add(c);
			}
		}
		
		int[] posicoes = new int[posicoesList.size()];
		for (int c = 0; c < posicoesList.size() ; c++) {
			posicoes[c] = posicoesList.get(c);
		}
		return posicoes;
	}
	
	public void exibir(boolean[] posicoes, Object contexto) {
		for(int c = 0; c < posicoes.length ; c++) {
			if (posicoes[c]) {
				Letra letra = this.letras[c];
				letra.exibir(contexto);
			} else {
				this.encoberta.exibir(contexto);
			}
			
		}	
	}
	
	public void exibir(Object contexto) {
		for(int c = 0; c < this.letras.length ; c++) {
			Letra letra = this.letras[c];
			letra.exibir(contexto);
		}
	}
	
	public Letra getLetra(int posicao) {
		try {
			return this.letras[posicao];
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Index "+ posicao +" out of bounds for length " + getTamanho());
			return null;
		}
	}
	
	public Letra[] getLetras() {
		return this.letras;
	}
	
	public String toString() {
		char[] palavra = new char[this.letras.length];
		for (int c = 0 ; c < this.letras.length; c++)
			palavra[c] = this.letras[c].getCodigo();
		return String.copyValueOf(palavra);
	}
	
}
