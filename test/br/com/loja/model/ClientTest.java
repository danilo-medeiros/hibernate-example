package br.com.loja.model;

import br.com.loja.dao.ClientDAO;

public class ClientTest {
	
	public static void main(String[] args) {
		Client client = new Client();
		client.setName("Danilo dos Santos Medeiros");
		client.setEmail("danilomedeiros03@gmail.com");
		System.out.println("Nome do Cliente: " + client.getName());
		System.out.println("E-mail do cliente: " + client.getEmail());
		
		ClientDAO.getInstance().persist(client);
		
		System.out.println("Ok!");
		
	}
	
}
