package com.pilog.plontology.config.pprm;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "pprmEntityManagerFactory", transactionManagerRef = "pprmTransactionManager", basePackages = "com.pilog.plontology.repository.pprm")
public class PPRMConfig {

	// Datasource
	@Primary
	@Bean
	@ConfigurationProperties(prefix = "pprm.datasource")
	public DataSource pprmDataSource() {
		return DataSourceBuilder.create().build();
	}

	// EntityManagerFactory
	@Bean(name="pprmEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean pprmEntityManagerFactory(EntityManagerFactoryBuilder emfb) {

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");


		return emfb
				.dataSource(pprmDataSource())  // Use the dataSource directly
				.packages("com.pilog.plontology.model.pprm")
				.properties(properties)
				.persistenceUnit("PDBVISIONSSD")  // Update persistence unit name
				.build();

	}

	// TransactionManagement
	@Bean
	@Primary
	public PlatformTransactionManager pprmTransactionManager(
			@Qualifier("pprmEntityManagerFactory") EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}

}
