package event_handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration{
     
    public RepositoryConfiguration(){
        super();
    }
 
    @Bean
    VastausEventHandler vastausEventHandler() {
        return new VastausEventHandler();
    }
 
  
}