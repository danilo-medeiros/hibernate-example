package br.com.loja.dao;

import br.com.loja.model.ProductOrder;

public class ProductOrderDAO extends GenericDAO<ProductOrder, Long>{
	
	private static ProductOrderDAO instance;
	
	private ProductOrderDAO() {
		super(ProductOrder.class);
	}
	
	public static ProductOrderDAO getInstance() {
		if (instance == null) {
			instance = new ProductOrderDAO();
		}
		return instance;
	}
	
}
