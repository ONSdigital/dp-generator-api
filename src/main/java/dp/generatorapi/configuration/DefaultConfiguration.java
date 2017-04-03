package dp.generatorapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DefaultConfiguration {

    @Value("#{systemEnvironment['PORT']?:8092}")
    private int port;

    @Value("#{systemEnvironment['DB_ACCESS']?:'jdbc:postgresql://localhost/dp'}")
    private String url;

    @Value("#{systemEnvironment['DB_USER']?:'dp'}")
    private String user;

    @Value("#{systemEnvironment['DB_PWD']?:'dp'}")
    private String password;

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            container.setPort(port);
        });
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .driverClassName("org.postgresql.Driver")
                .username(user)
                .password(password)
                .url(url)
                .build();
    }
}
