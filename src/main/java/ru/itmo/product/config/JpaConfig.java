package ru.itmo.product.config;

import com.github.jsixface.YamlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"ru.itmo.product"})
@EnableTransactionManagement
@ComponentScan({ "ru.itmo.product" })
@PropertySource(value= {"classpath:application.yml"})
public class JpaConfig {
    @Autowired
    Environment env;

    public JpaConfig() {
        super();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "ru.itmo.product" });

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Optional.ofNullable(System.getenv("DB_DRIVER")).orElse("org.postgresql.Driver"));
        dataSource.setUrl(Optional.ofNullable(System.getenv("DB_URL")).orElse("jdbc:postgresql://localhost:5432/postgres"));
        dataSource.setUsername(Optional.ofNullable(System.getenv("DB_USER")).orElse("postgres"));
        dataSource.setPassword(Optional.ofNullable(System.getenv("DB_PASS")).orElse("896699"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect",
                Optional.ofNullable(System.getenv("SQL_DIALECT")).orElse("org.hibernate.dialect.PostgreSQL10Dialect"));
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
        hibernateProperties.setProperty("hibernate.show_sql",
                Optional.ofNullable(System.getenv("SQL_SHOW")).orElse("false"));
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto",
                Optional.ofNullable(System.getenv("SQL_DLL")).orElse("update"));


        return hibernateProperties;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
