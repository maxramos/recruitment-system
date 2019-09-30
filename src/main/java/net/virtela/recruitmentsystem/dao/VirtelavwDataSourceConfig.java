package net.virtela.recruitmentsystem.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "virtelavwEntityManagerFactory",
		transactionManagerRef = "virtelavwTransactionManager")
public class VirtelavwDataSourceConfig {

	@Autowired
	private DataSource virtelavwDataSource;

//	@Value("${recsys.datasource.jndi-name.virtelavw}")
//	private String virtelavwJndiName; //Used when using multiple datasources.

//	@Bean(destroyMethod = "")
//	public DataSource virtelavwDataSource() {
//		//Used when using multiple datasources.
//		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//		return dataSourceLookup.getDataSource(virtelavwJndiName);
//	}

	@Bean
	public LocalContainerEntityManagerFactoryBean virtelavwEntityManagerFactory(EntityManagerFactoryBuilder factoryBuilder) {
		return factoryBuilder
//				.dataSource(virtelavwDataSource())
				.dataSource(virtelavwDataSource)
				.persistenceUnit("virtelavw")
				.packages("net.virtela.recruitmentsystem.model")
				.properties(getEntityManagerFactoryProperties())
				.build();
	}

	@Bean
	public PlatformTransactionManager virtelavwTransactionManager(@Qualifier("virtelavwEntityManagerFactory") EntityManagerFactory factory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(factory);
		return transactionManager;
	}

	private Map<String, Object> getEntityManagerFactoryProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.jdbc.batch_size", 100);
		properties.put("hibernate.order_inserts", true);
		properties.put("hibernate.order_updates", true);
//		properties.put("hibernate.transaction.jta.platform", "org.hibernate.engine.transaction.jta.platform.internal.JBossAppServerJtaPlatform"); // fix for Wildfly JTA issue, comment out if using tomcat.
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		return properties;
	}

}
