package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.Friends;
import com.niit.model.UserInfo;

@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class DataBaseConfiguration {

	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("colldb");
		dataSource.setPassword("root");
		return dataSource;
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {

		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.hbmddl2.auto", "update");
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

		LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(getDataSource());
		sessionFactoryBuilder.addProperties(hibernateProperties);
		
		sessionFactoryBuilder.addAnnotatedClass(Blog.class);
		sessionFactoryBuilder.addAnnotatedClass(BlogComment.class);
		sessionFactoryBuilder.addAnnotatedClass(UserInfo.class);
		sessionFactoryBuilder.addAnnotatedClass(Friends.class);
	

		SessionFactory sessionFactory = sessionFactoryBuilder.buildSessionFactory();
		System.out.println("***This object is SessionFactory******");
		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory) {
		System.out.println("***This object is Hibernate******");
		return new HibernateTransactionManager(sessionFactory);
	}
}
