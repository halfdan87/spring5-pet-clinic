package guru.springframework.sfgpetclinic.config;

import guru.springframework.sfgpetclinic.services.PetServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PetServiceConfiguration {

    @Bean
    @Profile({"default", "map"})
    PetServiceFactory petServiceFactory() {
        return new PetServiceFactory();
    }
}
