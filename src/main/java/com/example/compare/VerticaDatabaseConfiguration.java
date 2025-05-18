package com.example.compare;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.HashMap;

/**
 * General configuration for Historian.
 */
@Configuration
@EnableJpaRepositories(
    basePackages = {"com.example.compare.vertica.repository","com.example.compare.vertica.model"},
    entityManagerFactoryRef = "verticaEntityManagerFactory",
    transactionManagerRef = "verticaTransactionManager"
)
class VerticaDatabaseConfiguration {
    @Autowired
    private Environment env;


    @Bean(name = "verticaDataSource")
    @ConfigurationProperties(prefix = "spring.vertica")
    public DataSource verticaDataSource() {
        com.vertica.jdbc.DataSource ds=new com.vertica.jdbc.DataSource();
        // HikariConfig config = new HikariConfig();
        ds.setURL(env.getProperty("spring.vertica.datasource.jdbc-url"));
        ds.setUserID(env.getProperty("spring.vertica.datasource.username"));
        ds.setPassword("");
        //config.setPoolName("vertica_datasource");
        // HikariDataSource ds = new HikariDataSource(config);
        //ds.setDataSourceClassName(env.getProperty("spring.vertica.datasource.driver-class-name"));
        //ds.setMaximumPoolSize(Integer.parseInt(env.getProperty("spring.datasource.max-active")));
        //ds.setMinimumIdle(Integer.parseInt(env.getProperty("spring.datasource.max-active")));
        return ds;
    }

    @PersistenceContext(unitName = "vertica")
    @Bean(name = "verticaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean verticaEntityManager()  {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource( verticaDataSource());
        em.setPackagesToScan(new String[]{"com.example.compare.vertica.model","com.example.compare.vertica.repository"});
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter( vendorAdapter);
        HashMap<String, String> properties = new HashMap();
        properties.put("hibernate.dialect","org.hibernate.dialect.VerticaDialect");
        em.setPersistenceUnitName("vertica");
        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean(name = "verticaTransactionManager")
    public PlatformTransactionManager verticaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory (verticaEntityManager().getObject());
        return transactionManager;
    }

    @Bean(name = "VerticaTemplate")
    JdbcTemplate createVerticaTemplate() {
        return new JdbcTemplate(verticaDataSource());
    }
}
