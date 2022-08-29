package ru.isands.reportsservice.configuration.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@EnableJpaRepositories(
        entityManagerFactoryRef = ServiceRegistrationDataBaseConfig.ENTITY_MANAGER_FACTORY,
        transactionManagerRef = ServiceRegistrationDataBaseConfig.TRANSACTION_MANAGER,
        basePackages = ServiceRegistrationDataBaseConfig.JPA_REPOSITORY_PACKAGE
)
@Configuration
public class ServiceRegistrationDataBaseConfig {

    public static final String PROPERTY_PREFIX = "application.service-registration.datasource";
    public static final String JPA_REPOSITORY_PACKAGE = "ru.isands.reportsservice.repository.servregdb";
    public static final String ENTITY_PACKAGE = "ru.isands.reportsservice.entity.servregdb";
    public static final String ENTITY_MANAGER_FACTORY = "servregEntityManagerFactory";
    public static final String DATA_SOURCE = "servregDataSource";
    public static final String DATABASE_PROPERTY = "servregDatabaseProperty";
    public static final String TRANSACTION_MANAGER = "servregTransactionManager";

    @Bean(DATABASE_PROPERTY)
    @ConfigurationProperties(prefix = PROPERTY_PREFIX)
    public DatabaseProperties databaseProperties() {
        return new DatabaseProperties();
    }

    @Bean(DATA_SOURCE)
    public DataSource dataSource(
            @Qualifier(DATABASE_PROPERTY) DatabaseProperties databaseProperties
    ) {
        return DataSourceBuilder
                .create()
                .username(databaseProperties.getUsername())
                .password(databaseProperties.getPassword())
                .url(databaseProperties.getUrl())
                .driverClassName(databaseProperties().getClassDriver())
                .build();

    }

    @Bean(ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource);
        entityManager.setPersistenceUnitName(ENTITY_MANAGER_FACTORY);
        entityManager.setPackagesToScan(ENTITY_PACKAGE);
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.validation.mode", "none");
        properties.put("hibernate.hbm2ddl.auto", "update");

        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }

    @Bean(TRANSACTION_MANAGER)
    public PlatformTransactionManager sqlSessionTemplate(
            @Qualifier(ENTITY_MANAGER_FACTORY) LocalContainerEntityManagerFactoryBean entityManager,
            @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setEntityManagerFactory(entityManager.getObject());

        return transactionManager;
    }
}
