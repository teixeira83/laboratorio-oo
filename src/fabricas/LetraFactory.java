package fabricas;

import dominio.Letra;

public interface LetraFactory {
	public Letra getLetra(char codigo);
	public Letra getLetraEncoberta();
}
