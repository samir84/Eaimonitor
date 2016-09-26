package com.hs.eai.monitor.spring;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableTransactionManagement
@PropertySource({ "file:henryschein/EaiMonitorMVC.properties" })
//@PropertySource("file:C:/glassfish4/glassfish/domains/domain1/config/henryschein/EaiMonitorMVC.properties")
@ComponentScan("com.hs.eai.monitor")
public class HibernateConfiguration {

	private static final String DATABASE_DRIVER = "eaimonitor.jdbc.driver";
	private static final String DATABASE_URL = "eaimonitor.jdbc.url";
	private static final String DATABASE_USERNAME = "eaimonitor.jdbc.username";
	private static final String DATABASE_PASSWORD = "eaimonitor.jdbc.password";

	private static final String HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String HIBERNATE_DEFAULT_SCHEMA = "hibernate.default_schema";
	private static final String HIBERNATE_HBM2DLL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	private static final String HIBERNATE_SEARCH_DEFAULT_INDEX_BASE = "hibernate.search.default.indexBase";
	private static final String HIBERNATE_SEARCH_DEFAULT_DIRECTORY_PROVIDER ="hibernate.search.default.directory_provider";
	
	
	
	
	@Resource
	Environment env;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getRequiredProperty(DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(DATABASE_PASSWORD));

		return dataSource;
	}

	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { env.getRequiredProperty(ENTITYMANAGER_PACKAGES_TO_SCAN) });
       
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put(HIBERNATE_DIALECT, env.getRequiredProperty(HIBERNATE_DIALECT));
		properties.put(HIBERNATE_SHOW_SQL, env.getRequiredProperty(HIBERNATE_SHOW_SQL));
		properties.put(HIBERNATE_DEFAULT_SCHEMA,env.getRequiredProperty(HIBERNATE_DEFAULT_SCHEMA));
		properties.put(HIBERNATE_HBM2DLL_AUTO, env.getRequiredProperty(HIBERNATE_HBM2DLL_AUTO));
		
		//properties.put(CURRENT_SESSION_CONTEXT_CLASS, env.getRequiredProperty(CURRENT_SESSION_CONTEXT_CLASS));
		return properties;
	}

	@Bean
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
	
	
	
}
