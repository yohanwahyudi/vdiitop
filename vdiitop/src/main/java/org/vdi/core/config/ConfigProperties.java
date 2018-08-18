package org.vdi.core.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.vdi.core.constant.ConstantProperties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
//@ComponentScan(basePackages = "com.vdi")
@ComponentScan({"org.vdi.repository", "org.vdi.model"})
@EnableJpaRepositories(basePackages = { "org.vdi.repository"})
@EnableTransactionManagement
@PropertySources({
		@PropertySource("classpath:config.properties")
})
public class ConfigProperties {

	public ConfigProperties() {
		
	}
	
	/*
	 * Database Configuration
	 */
	@Bean(destroyMethod = "close")
	DataSource dataSource(Environment env) {
		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName(env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_DB_DRIVER_CLASS));
		dataSourceConfig.setJdbcUrl(env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_DB_URL));
		dataSourceConfig.setUsername(env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_DB_USER));
		dataSourceConfig.setPassword(env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_DB_PASSWORD));

		return new HikariDataSource(dataSourceConfig);
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
		
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(ConstantProperties.PROPERTY_NAME_ENTITY_PACKAGE);

		Properties jpaProperties = new Properties();
		
		jpaProperties.put(ConstantProperties.PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_HIBERNATE_DIALECT));
		jpaProperties.put(ConstantProperties.PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
		jpaProperties.put(ConstantProperties.PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
		jpaProperties.put(ConstantProperties.PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		jpaProperties.put(ConstantProperties.PROPERTY_NAME_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
		
		jpaProperties.put(ConstantProperties.PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE, env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE));
		jpaProperties.put(ConstantProperties.PROPERTY_NAME_HIBERNATE_JDBC_BATCH_VERSIONED_DATA, env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_HIBERNATE_JDBC_BATCH_VERSIONED_DATA));
		jpaProperties.put(ConstantProperties.PROPERTY_NAME_HIBERNATE_ORDER_INSERTS, env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_HIBERNATE_ORDER_INSERTS));
		jpaProperties.put(ConstantProperties.PROPERTY_NAME_HIBERNATE_ORDER_UPDATES, env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_HIBERNATE_ORDER_UPDATES));
		jpaProperties.put(ConstantProperties.PROPERTY_NAME_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE, env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
		jpaProperties.put(ConstantProperties.PROPERTY_NAME_HIBERNATE_CONNECTION_AUTOCOMMIT, env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_HIBERNATE_CONNECTION_AUTOCOMMIT));
		jpaProperties.put(ConstantProperties.PROPERTY_NAME_HIBERNATE_ID_NEW_GENERATOR_MAPPINGS, env.getRequiredProperty(ConstantProperties.PROPERTY_NAME_HIBERNATE_ID_NEW_GENERATOR_MAPPINGS));
		
		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}
	
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
}
