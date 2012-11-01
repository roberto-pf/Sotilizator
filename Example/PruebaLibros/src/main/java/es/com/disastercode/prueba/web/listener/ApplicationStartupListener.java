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
 * Listener que carga la configuración necesaria para la inicialización de la
 * aplicación.
 * 
 */
public class ApplicationStartupListener implements ServletContextListener {
	
	/**
	 * Método de finalización del contexto de la aplicación. Es invocado al
	 * finalizar la aplicación, al realizar un undeploy.
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		//TODO pendiente
	}

	/**
	 * Método de inicialización del contexto de la aplicación. Es invocado al
	 * arrancar la aplicación, al realizar un deploy.
	 */
	public void contextInitialized(ServletContextEvent sce) {
		//TODO pendiente
	}
	
}


