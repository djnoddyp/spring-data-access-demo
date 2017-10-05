package patrick.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("/app.properties")
public class AppConfig {

    @Autowired
    Environment env;

    // Configure an embedded H2 db
    // Note: DriverManagerDataSource is intended for testing only,
    // will perform poorly in a prod env
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        // not sure why this url works but it does
        dataSource.setUrl("jdbc:h2:./test");
        dataSource.setUsername("${jdbc.username}");
        dataSource.setPassword("${jdbc.password}");
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

}
