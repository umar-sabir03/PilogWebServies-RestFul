package com.pilog.plontology.config.srs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(entityManagerFactoryRef = "apexsrsEntityManagerFactory", transactionManagerRef = "apexsrsTransactionManager", basePackages = "com.pilog.plontology.repository.apexsrs")
public class ApexSRSConfig {

        // Datasource
        @Bean
        @ConfigurationProperties(prefix = "apexsrs.datasource")
        public DataSource apexsrsDataSource() {
            return DataSourceBuilder.create().build();
        }

        // EntityManagerFactory
        @Bean(name="apexsrsEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean apexsrsEntityManagerFactory(EntityManagerFactoryBuilder emfb) {

            HashMap<String, Object> properties = new HashMap<>();
            properties.put("hibernate.hbm2ddl.auto", "update");
//		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
            properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.format_sql", "true");


            return emfb
                    .dataSource(apexsrsDataSource())  // Use the dataSource directly
                    .packages("com.pilog.plontology.model.apexsrs")
                    .properties(properties)
                    .persistenceUnit("APEX")
                    .build();

        }

        @Bean
        public PlatformTransactionManager apexsrsTransactionManager(
                @Qualifier("apexsrsEntityManagerFactory") EntityManagerFactory factory) {
            return new JpaTransactionManager(factory);
        }

    }


