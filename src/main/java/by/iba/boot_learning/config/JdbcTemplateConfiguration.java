package by.iba.boot_learning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfiguration {
    @Autowired
    DataSource dataSource;

    @Bean
    public JdbcTemplate configJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

}
