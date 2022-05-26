package fabricas;

import dominio.Palavra;
import dominio.Tema;

public interface PalavraFactory {
	public Palavra getPalavra(String palavra, Tema tema);
}
