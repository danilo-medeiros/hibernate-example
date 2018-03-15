package br.com.loja.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;

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
	
	public Client getById(final Long id) {
		return entityManager.find(Client.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Client> findAll() {
		return entityManager.createQuery("FROM " + Client.class.getName()).getResultList();
	}
	
	public Client persist(Client client) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(client);
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				ConstraintViolationException exp = (ConstraintViolationException) e.getCause();
				if (exp.getConstraintName().equals("client_email_key")) {
					throw new PersistenceException("Conflito: E-mail já cadastrado.");
				}
				throw new PersistenceException("Ocorreu um conflito de dados.");
			}
			throw new PersistenceException("Erro ao inserir.");
		} catch (RuntimeException e) {
			throw e;
		} finally {
			if (entityManager.getTransaction().isActive())
				entityManager.getTransaction().rollback();
		}
		return client;
	}
	
	public Client merge(Client client) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(client);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return client;
	}
	
	public boolean remove(Client client) {
		try {
			entityManager.getTransaction().begin();
			client = entityManager.find(Client.class, client.getId());
			entityManager.remove(client);
			entityManager.getTransaction().commit();
		} catch (NullPointerException e) {
			throw new NullPointerException("Registro não existe");
		} finally {
			if (entityManager.getTransaction().isActive()) 
				entityManager.getTransaction().rollback();
		}
		return true;
	}
	
	public boolean removeById(final Long id) throws RuntimeException {
		try {
			Client client = getById(id);
			return remove(client);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
}
