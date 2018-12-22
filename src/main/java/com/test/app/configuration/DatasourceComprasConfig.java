package com.test.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "comprasEntityManagerFactory",
        basePackages = {"com.test.app.dao.compras"})
public class DatasourceComprasConfig {

    private final Environment env;

    @Autowired
    public DatasourceComprasConfig(Environment env) {
        this.env = env;
    }

    @Bean(name = "comprasDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("compras.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("compras.datasource.url"));
        dataSource.setUsername(env.getProperty("compras.datasource.username"));
        dataSource.setPassword(env.getProperty("compras.datasource.password"));
        return dataSource;
    }

    @Bean(name = "comprasEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean comprasEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("comprasDataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("com.test.app.model")
                .persistenceUnit("compras")
                .build();
    }

    @Bean(name = "comprasTransactionManager")
    public PlatformTransactionManager comprasTransactionManager(@Qualifier("comprasEntityManagerFactory") EntityManagerFactory comprasEntityManagerFactory) {
        return new JpaTransactionManager(comprasEntityManagerFactory);
    }
}