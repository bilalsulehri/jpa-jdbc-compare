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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
    basePackages = {"com.example.compare.pg.repository"},
    entityManagerFactoryRef = "customerEntityManagerFactory",
    transactionManagerRef = "customerTransactionManager"
)
class CustomerDatabaseConfiguration {
    @Autowired
    private Environment env;


    @Bean(name = "customerDataSource")
    @ConfigurationProperties(prefix = "spring")
    public DataSource customerDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty("spring.datasource.jdbc-url"));
        config.setUsername(env.getProperty("spring.datasource.username"));
        config.setPassword(env.getProperty("spring.datasource.password"));
        config.setPoolName("customer_datasource");
        HikariDataSource ds = new HikariDataSource(config);

        ds.setMaximumPoolSize(Integer.parseInt(env.getProperty("spring.datasource.max-active")));
        ds.setMinimumIdle(Integer.parseInt(env.getProperty("spring.datasource.max-active")));
        return ds;
    }

    @PersistenceContext(unitName = "customer")
    @Bean(name = "customerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean consumptionEntityManager()  {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource( customerDataSource());
        em.setPackagesToScan(new String[]{"com.example.compare.pg.model"});
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter( vendorAdapter);
        HashMap<String, String> properties = new HashMap();
        properties.put("hibernate.dialect",env.getProperty("jpa.database-platform"));
        em.setPersistenceUnitName("customer");
        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean(name = "customerTransactionManager")
    public PlatformTransactionManager consumptionTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory (consumptionEntityManager().getObject());
        return transactionManager;
    }

    @Bean(name = "CustomerTemplate")
    JdbcTemplate createConsumptionTemplate() {
        return new JdbcTemplate(customerDataSource());
    }

    @Bean(name= "CustomerNamedTemplate")
    NamedParameterJdbcTemplate createParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(customerDataSource());
    }
}
