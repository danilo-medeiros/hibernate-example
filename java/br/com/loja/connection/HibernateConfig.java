package br.com.loja.connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.loja.model.Client;

public class HibernateConfig {
	private static Configuration configuration;
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static String url = "jdbc:postgresql://localhost:5432/store";
	private static String passw = "postgres";
	private static String user = "postgres";
	
	private synchronized static final SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				configuration = new Configuration();
				//setSettingsContext();
				
				configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
	    		configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
	    		configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
	            configuration.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");
			
	            configuration.setProperty("hibernate.c3p0.min_size", "0");
	            configuration.setProperty("hibernate.c3p0.max_size", "1");
	            configuration.setProperty("hibernate.c3p0.max_statements", "1");
	            configuration.setProperty("hibernate.c3p0.maxIdleTime", "1");
	            configuration.setProperty("hibernate.c3p0.acquireIncrement", "1");
	            configuration.setProperty("hibernate.c3p0.initialPoolSize", "1");
	            configuration.setProperty("hibernate.c3p0.maxIdleTimeExcessConnections", "1");
	           
	    		configuration.setProperty("hibernate.connection.url", url);
			 	configuration.setProperty("hibernate.connection.username", user);
			 	configuration.setProperty("hibernate.connection.password", passw);
			 	
			 	configuration.addAnnotatedClass(Client.class);

			 	return configuration.buildSessionFactory();
			 	
			} else {
				return sessionFactory;
			}
		} catch (Exception e) {
			System.err.println("Initial creation of SessionFactory failed. " + e);
			throw new ExceptionInInitializerError();
		}
	}
	
	public synchronized static final Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	/*
	public synchronized static final void setSettingsContext() {
		ReadPropertyFile properties = new ReadPropertyFile();
		url = properties.getContextProperty("db1_url");
		user = properties.getContextProperty("db1_username");
		passw = properties.getContextProperty("db1_password");
	}
	*/
}
