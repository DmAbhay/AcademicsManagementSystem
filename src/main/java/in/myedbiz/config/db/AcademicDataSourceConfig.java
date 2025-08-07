package in.myedbiz.config.db;



import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "in.myedbiz.repository", // change to your repository package
        entityManagerFactoryRef = "academicEntityManagerFactory",
        transactionManagerRef = "academicTransactionManager"
)
public class AcademicDataSourceConfig {

    @Bean(name = "academicDataSource")
    public DataSource academicDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/academics")
                .username("root")
                .password("abc@123")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean(name = "academicEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean academicEntityManagerFactory(
            @Qualifier("academicDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("in.myedbiz.entity"); // change to your entity package
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }

    @Bean(name = "academicTransactionManager")
    public JpaTransactionManager academicTransactionManager(
            @Qualifier("academicEntityManagerFactory") LocalContainerEntityManagerFactoryBean factoryBean) {
        return new JpaTransactionManager(factoryBean.getObject());
    }

    @Bean(name = "academicJdbcTemplate")
    public JdbcTemplate academicJdbcTemplate(@Qualifier("academicDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "academicNamedJdbcTemplate")
    public NamedParameterJdbcTemplate academicNamedJdbcTemplate(
            @Qualifier("academicJdbcTemplate") JdbcTemplate jdbcTemplate) {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Bean(name = "academicSimpleJdbcCall")
    public SimpleJdbcCall academicSimpleJdbcCall(@Qualifier("academicJdbcTemplate") JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcCall(jdbcTemplate);
    }

    private Properties jpaProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "none"); // ← Prevents table creation
        return props;
    }
}

