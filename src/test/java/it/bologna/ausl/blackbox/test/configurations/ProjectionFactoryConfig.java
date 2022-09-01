package it.bologna.ausl.blackbox.test.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

/**
 *
 * @author gdm
 */
@Configuration
public class ProjectionFactoryConfig {
    
    @Bean
    public SpelAwareProxyProjectionFactory projectionFactory() {
        return new SpelAwareProxyProjectionFactory();
    }
}