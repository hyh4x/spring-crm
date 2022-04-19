package no.group.springdemo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan("no.group.springdemo")
@PropertySource({"classpath:persistence.properties",
		"classpath:security-persistence.properties"})
public class WebConfig implements WebMvcConfigurer{

	@Autowired
	Environment env;
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	@Bean
	public DataSource connectionDataSource() {
		ComboPooledDataSource connectionDataSource = new ComboPooledDataSource();
		
		// JDBC properties
		try {
			connectionDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("Accessing "+env.getProperty("jdbc.url")+" as "+env.getProperty("jdbc.user"));
		connectionDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		connectionDataSource.setUser(env.getProperty("jdbc.user"));
		connectionDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// Connection pool properties
		
		connectionDataSource.setInitialPoolSize
			(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		connectionDataSource.setMinPoolSize
			(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
		connectionDataSource.setMaxPoolSize
			(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
		connectionDataSource.setMaxIdleTime
			(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
		
		return connectionDataSource;
	}

	private Properties getHibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return props;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		// create session factories
		LocalSessionFactoryBean sessionFactory = new
		LocalSessionFactoryBean();
		// set the properties
		sessionFactory.setDataSource(connectionDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
	
	@Bean
	public DataSource securityDataSource() {
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		// JDBC properties
		try {
			securityDataSource.setDriverClass(env.getProperty("security.jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("Accessing "+env.getProperty("security.jdbc.url")+" as "+env.getProperty("security.jdbc.user"));
		securityDataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
		securityDataSource.setUser(env.getProperty("security.jdbc.user"));
		securityDataSource.setPassword(env.getProperty("security.jdbc.password"));
		
		// Connection pool properties
		
		securityDataSource.setInitialPoolSize
			(Integer.parseInt(env.getProperty("security.connection.pool.initialPoolSize")));
		securityDataSource.setMinPoolSize
			(Integer.parseInt(env.getProperty("security.connection.pool.minPoolSize")));
		securityDataSource.setMaxPoolSize
			(Integer.parseInt(env.getProperty("security.connection.pool.maxPoolSize")));
		securityDataSource.setMaxIdleTime
			(Integer.parseInt(env.getProperty("security.connection.pool.maxIdleTime")));
		
		return securityDataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean securitySessionFactory(){
		// create session factories
		LocalSessionFactoryBean sessionFactory = new
		LocalSessionFactoryBean();
		// set the properties
		sessionFactory.setDataSource(securityDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager securityTransactionManager(SessionFactory securitySessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(securitySessionFactory);
		return txManager;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/");
	}
}
