package br.com.loja.model;

import br.com.loja.dao.ClientDAO;
import br.com.loja.dao.ProductOrderDAO;
import br.com.loja.exception.DAOException;

public class ProductOrderTest {

	public static void main(String[] args) {
		ProductOrder order = new ProductOrder();
		order.setClient(ClientDAO.getInstance().getById(new Long(6)));
		order.setValue(20);
		try {
			ProductOrderDAO.getInstance().persist(order);
			for (ProductOrder productOrder: ProductOrderDAO.getInstance().listAll()) {
				System.out.println(productOrder);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
}
