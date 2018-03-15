package br.com.loja.model;

import java.util.List;

import br.com.loja.dao.ClientDAO;

public class ClientTest {
	
	public static void main(String[] args) {
		insert();
	}
	
	static void insert() {
		Client client = new Client();
		client.setName("Bruce Dickinson");
		client.setEmail("bruce@gmail.com");
		try {
			client = ClientDAO.getInstance().persist(client);
			System.out.println("Cliente inserido: " + client.toString());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	static void delete() {
		try {
			ClientDAO.getInstance().removeById(new Long(4));
			System.out.println("Cliente removido com sucesso");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	static void update() {
		Client client = ClientDAO.getInstance().getById(new Long(4));
		client.setEmail("JOAQUINA@HOTMAIL.COM");
		client = ClientDAO.getInstance().merge(client);
		System.out.println(client.toString());
	}
	
	static void list() {
		List<Client> clients = ClientDAO.getInstance().findAll();
		
		for (Client client: clients) {
			System.out.println(client.getName() + ", " + client.getEmail());
		}
	}
	
}
