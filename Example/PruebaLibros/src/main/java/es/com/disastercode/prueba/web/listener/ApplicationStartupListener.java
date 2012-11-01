package es.com.disastercode.prueba.web.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Listener que carga la configuraci�n necesaria para la inicializaci�n de la
 * aplicaci�n.
 * 
 */
public class ApplicationStartupListener implements ServletContextListener {
	
	/**
	 * M�todo de finalizaci�n del contexto de la aplicaci�n. Es invocado al
	 * finalizar la aplicaci�n, al realizar un undeploy.
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		//TODO pendiente
	}

	/**
	 * M�todo de inicializaci�n del contexto de la aplicaci�n. Es invocado al
	 * arrancar la aplicaci�n, al realizar un deploy.
	 */
	public void contextInitialized(ServletContextEvent sce) {
		//TODO pendiente
	}
	
}


