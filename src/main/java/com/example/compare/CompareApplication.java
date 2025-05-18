package com.example.compare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.HashMap;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"com.example.compare.pg.repository"}/*,
//		entityManagerFactoryRef = "mainEntityManager",
//		transactionManagerRef = "mainTransactionManager"*/)

public class CompareApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(CompareApplication.class, args);

	}

/*	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}*/


/*	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource());
		return txManager;
	}*/

/*
	@Primary
	@Bean(name = "mainTransactionManager")
	public PlatformTransactionManager mainTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory (mainEntityManager().getObject());
		return transactionManager;
	}
*/

/*	@Bean(name ="MainTemplate")
	JdbcTemplate createMainTemplate()  {
		return new JdbcTemplate(dataSource());
	}*/

	/*@PersistenceContext(unitName = "main")
	@Bean(name = "mainEntityManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean mainEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[]{"com.example.compare"
				}
		);
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter( vendorAdapter);
		HashMap properties = new HashMap<String,String>();
		properties.put("hibernate.dialect",env.getProperty("main.jpa.database-platform"));
		em.setPersistenceUnitName("main");
		em.setJpaPropertyMap(properties);
		return em;
	}*/
}
