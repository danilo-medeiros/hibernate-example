package br.com.loja.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.loja.exception.DAOException;


public class GenericDAO<T, I extends Serializable> {

	private Class<T> persistedClass;
	
	private EntityManager entityManager;
	
	protected GenericDAO(Class<T> persistedClass) {
		this.persistedClass = persistedClass;
		entityManager = getEntityManager();
	}
	
	protected EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("storePU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}
	
	public T getById(final Long id) {
		return entityManager.find(persistedClass, id);
	}
	
	public boolean remove(T entity) throws DAOException {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}
	}
	
	public boolean removeById(final Long id) throws DAOException {
		try {
			T entity = this.getById(id);
			if (entity != null)
				return remove(entity);
			return false;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}
	}
	
	public T persist(T entity) throws DAOException {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.flush();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new DAOException(e);
		}
		return entity;
	}
	
	public T merge(T entity) throws DAOException {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.flush();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback(); 
			throw new DAOException(e);
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		return entityManager.createQuery("FROM "+ persistedClass.getName()).getResultList();
	}
	
	
}
