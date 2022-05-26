package fabricas;

import repositorios.Repository;

public abstract class EntityFactory {

	private Repository repository;
	
	protected EntityFactory(Repository repository) {
		this.repository = repository;
	}
	
	protected Repository getRepository() {
		return this.repository;
	}
	
	protected long getProximoId() {
		return this.repository.getProximoId();
	}
}
