package pl.com.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import pl.com.configuration.properties.StorageProperties;

@Configuration
public class AppConfiguration {

    @Bean
    public StorageProperties storageProperties() {
        return new StorageProperties();
    }
}