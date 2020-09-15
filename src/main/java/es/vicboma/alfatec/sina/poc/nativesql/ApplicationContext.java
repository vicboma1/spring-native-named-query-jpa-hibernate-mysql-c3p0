package es.vicboma.alfatec.sina.poc.nativesql;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import es.vicboma.alfatec.sina.poc.nativesql.dao.UserDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/***
 * @author vicboma1
 */
@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("es.vicboma.alfatec.sina.poc")
@EnableTransactionManagement
public class ApplicationContext {

    final private static Logger logger = LoggerFactory.getLogger(ApplicationContext.class);
    private ComboPooledDataSource ds;
    private JpaTransactionManager tm;

    public ApplicationContext() {
      logger.info("Constructor");
      ds = new ComboPooledDataSource();
      tm = new JpaTransactionManager();
    }

    @PostConstruct
    public void postConstruct() {
      logger.info("postConstruct");
    }

    @PreDestroy
    public void preDestroy() {
    logger.info("preDestroy");
  }

    @Bean
    protected DataSource dataSource(Environment env) {
      //Lazy evaluation
      if(ds.getDriverClass() != null)
        return ds;

      try {
          ds.setDriverClass(env.getRequiredProperty("jdbc.driverClassName"));
      } catch (IllegalStateException | PropertyVetoException ex) {
          throw new RuntimeException("error while setting the driver class name in the datasource", ex);
      }
      ds.setJdbcUrl(env.getRequiredProperty("jdbc.url"));
      ds.setUser(env.getRequiredProperty("jdbc.username"));
      ds.setPassword(env.getRequiredProperty("jdbc.password"));
      ds.setAcquireIncrement(env.getRequiredProperty("c3p0.acquire_increment", Integer.class));
      ds.setMinPoolSize(env.getRequiredProperty("c3p0.min_size", Integer.class));
      ds.setMaxPoolSize(env.getRequiredProperty("c3p0.max_size", Integer.class));
      ds.setMaxIdleTime(env.getRequiredProperty("c3p0.max_idle_time", Integer.class));
      ds.setUnreturnedConnectionTimeout(env.getRequiredProperty("c3p0.unreturned_connection_timeout", Integer.class));

      return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        return new LocalContainerEntityManagerFactoryBean() {
          {
            //setPersistenceXmlLocation("classpath*:META-INF/persistence.xml");
            //setPersistenceUnitName("hibernatePersistenceUnit");
            setPackagesToScan(env.getRequiredProperty("project.entity_packet"));
            setDataSource(dataSource);
            setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            setJpaProperties(new Properties() {
              {
                put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
                put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
                put("hibernate.connection.autocommit", env.getRequiredProperty("hibernate.connection.autocommit"));
                put("hibernate.cache.use_second_level_cache", env.getRequiredProperty("hibernate.cache"));
                put("hibernate.cache.use_query_cache", env.getRequiredProperty("hibernate.query.cache"));
                put("hibernate.cache.provider_class", "org.hibernate.cache.EhCacheProvider");
                put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
                put("hibernate.generate_statistics", env.getRequiredProperty("hibernate.statistics"));
                put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
                put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
                put("hibernate.jdbc.lob.non_contextual_creation", true);
                //Bulk process
                put("hibernate.jdbc.batch_size", env.getRequiredProperty("hibernate.jdbc.batch_size"));
                put("hibernate.order_inserts", env.getRequiredProperty("hibernate.order_inserts"));
                put("hibernate.order_updates", env.getRequiredProperty("hibernate.order_updates"));
                put("hibernate.jdbc.batch_versioned_data", env.getRequiredProperty("hibernate.jdbc.batch_versioned_data"));
                put("hibernate.jdbc.default_batch_fetch_size", env.getRequiredProperty("hibernate.jdbc.default_batch_fetch_size"));
              }
            });
          }
        };
    }
    
    @Bean
    protected JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        tm.setEntityManagerFactory(entityManagerFactory);
        return tm;
    }
}
