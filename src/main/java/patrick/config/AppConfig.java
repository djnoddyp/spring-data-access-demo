package patrick.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import org.h2.Driver;

@Configuration
public class AppConfig {

    // Configure an embedded H2 db
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        // not sure why this url works but it does
        dataSource.setUrl("jdbc:h2:./test");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

}
