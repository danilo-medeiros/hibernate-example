package br.com.loja.dao;

import javax.persistence.EntityManager;
import br.com.loja.model.Client;

public class ClientDAO extends GenericDAO<Client, Long> {
	private static ClientDAO instance;
	protected EntityManager entityManager;
	
	public static ClientDAO getInstance() {
		if (instance == null) {
			instance = new ClientDAO();
		}
		return instance;
	}
	
	private ClientDAO() {
		super(Client.class);
	}
	
	
}
