package br.com.loja.model;

import java.util.List;


import br.com.loja.dao.ClientDAO;
import br.com.loja.dao.GenericDAO;
import br.com.loja.exception.DAOException;

public class ClientTest {
	
	public static void main(String[] args) {
		list();
	}
	
	static void insert() {
		Client client = new Client();
		client.setName("Thiago");
		client.setEmail("thiago@gmail.com");
		try {
			client = ClientDAO.getInstance().persist(client);
			System.out.println("Cliente inserido: " + client.toString());
		} catch (DAOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	static void delete() {
		try {
			ClientDAO.getInstance().removeById(new Long(6));
			System.out.println("Cliente removido com sucesso");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	static void update() {
		Client client = ClientDAO.getInstance().getById(new Long(6));
		if (client == null) {
			System.out.println("Cliente não existe");
		} else {
			client.setEmail("bruce@gmail.com");
			try {
				client = ClientDAO.getInstance().merge(client);
				System.out.println("Cliente atualizado: " + client.toString());
			} catch (DAOException e) {
				System.err.println(e.getMessage());
			}
			
		}
	}
	
	static void list() {
		List<Client> clients = ClientDAO.getInstance().listAll();
		
		for (Client client: clients) {
			System.out.println(client.toString());
		}
	}
	
}
