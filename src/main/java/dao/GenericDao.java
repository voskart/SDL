package dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Abstract Dao class with two Generics, C is the Class of the Entity, that the instance of this
 * abstract Dao is accessing, and T is the Class of the identifier (e.g. Long) which is used to
 * identify the instances of Class C.
 * @author Julian Dobmann <julian.dobmann@fu-berlin.de>
 */
public abstract class GenericDao <C, T> {
	
	private Class <C> instanceClass;

	@PersistenceContext(unitName = "sdl-persist")
	private EntityManager entityManager;

	

	
	/**
	 * protected getter for EntityManager since concrete Subclasses do not inherit access to
	 * private members.
	 */
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	public Class<C> getInstanceClass() {
		return this.instanceClass;
	}
	
	public void setInstanceClass(Class<C> instanceClass) {
		this.instanceClass = instanceClass;
	}
	
	public C findById(T identifier) throws EntityNotFoundException {
		C entity = entityManager.find(instanceClass, identifier);
		if (null != entity) {
			return entity;
		} else {
			throw new EntityNotFoundException();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<C> findAll() {
		Query query = entityManager.createQuery("FROM " + getInstanceClass().getName());
		return query.getResultList();
	}
	
	public void persist(C instance) {
		entityManager.persist(instance);
	}
	
	public void merge(C instance) {
		entityManager.merge(instance);
	}
	
	/**
	 * Method for removing persistent Entities. To remove a detached Entity e either
	 * do a findById(e.getId()) to retrieve the persistent entity or call remove(e.getId()).
	 * @param instance
	 */
	public void remove(C instance) {
		entityManager.remove(instance);
	}
	
	public void removeById(T identifier) throws EntityNotFoundException {
		entityManager.remove(findById(identifier));
	}

}
