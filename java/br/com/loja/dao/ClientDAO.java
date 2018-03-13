package br.com.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.loja.model.Client;

public class ClientDAO {
	private static ClientDAO instance;
	protected EntityManager entityManager;
	
	public static ClientDAO getInstance() {
		if (instance == null) {
			instance = new ClientDAO();
		}
		return instance;
	}
	
	private ClientDAO() {
		entityManager = getEntityManager();
	}
	
	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("storePU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}
	
	private Client getById(final Long id) {
		return entityManager.find(Client.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Client> findAll() {
		return entityManager.createQuery("FROM " + Client.class.getName()).getResultList();
	}
	
	public void persist(Client client) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(client);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void merge(Client client) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(client);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void remove(Client client) {
		try {
			entityManager.getTransaction().begin();
			client = entityManager.find(Client.class, client.getId());
			entityManager.remove(client);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void removeById(final Long id) {
		try {
			Client client = getById(id);
			remove(client);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
