package guru.springframework.sfgpetclinic.config;

import guru.springframework.sfgpetclinic.services.ConstructorGreetingService;
import guru.springframework.sfgpetclinic.services.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingServiceConfiguration {

    @Bean
    GreetingService greetingService() {
        return new ConstructorGreetingService();
    }
}
