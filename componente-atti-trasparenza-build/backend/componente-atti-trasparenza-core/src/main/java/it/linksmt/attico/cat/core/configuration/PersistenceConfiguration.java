package it.linksmt.attico.cat.core.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "it.linksmt.attico.cat.core.persistence.repository" })
@EnableTransactionManagement
public class PersistenceConfiguration {

	private final Logger log = LoggerFactory.getLogger(PersistenceConfiguration.class.getName());

	@Bean(destroyMethod = "close")
	DataSource dataSource(Environment env) {

		log.debug("db.driver: {}, db.url: {}", env.getRequiredProperty("db.driver"), env.getRequiredProperty("db.url"));

		HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
		dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
		dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
		dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
		if (env.getRequiredProperty("db.maximumPoolSize") != null && !env.getRequiredProperty("db.maximumPoolSize").isEmpty()) {
			dataSourceConfig.setMaximumPoolSize(env.getRequiredProperty("db.maximumPoolSize", Integer.class));
		}
		if (env.getRequiredProperty("db.minimumIdle") != null && !env.getRequiredProperty("db.minimumIdle").isEmpty()) {
			dataSourceConfig.setMinimumIdle(env.getRequiredProperty("db.minimumIdle", Integer.class));
		}

		return new HikariDataSource(dataSourceConfig);
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {

		log.debug("hibernate.dialect: {}, hibernate.hbm2ddl.auto: {}, hibernate.ejb.naming_strategy: {}", env.getRequiredProperty("hibernate.dialect"),
				env.getRequiredProperty("hibernate.hbm2ddl.auto"), env.getRequiredProperty("hibernate.ejb.naming_strategy"));

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("it.linksmt.attico.cat.core.persistence.entity");

		Properties jpaProperties = new Properties();

		// Configures the used database dialect. This allows Hibernate to create SQL
		// that is optimized for the used database.
		jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));

		// Specifies the action that is invoked to the database when the Hibernate
		// SessionFactory is created or closed.
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));

		// Configures the naming strategy that is used when Hibernate creates
		// new database objects and schema elements
		jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));

		// If the value of this property is true, Hibernate writes all SQL
		// statements to the console.
		jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));

		// If the value of this property is true, Hibernate will format the SQL
		// that is written to the console.
		jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));

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