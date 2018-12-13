package tenttijarjestelma;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler(Vastaus.class) 
public class VastausEventHandler {
	private static final Logger log = LoggerFactory.getLogger(VastausEventHandler.class);
	VastausvaihtoehtoRepository vastausvaihtoehdot;
	VastausRepository vastaukset;
     
    @HandleBeforeCreate
    public void handleVastausBeforeCreate(Vastaus vastaus){
        log.info("Inside Vastaus Before Create....");
    }
 
    // Voisi tehdä näin, mitä hyvää, mitä huonoa
    @HandleAfterSave
    public void handleVastausAfterSave(Vastaus vastaus){
        log.info("Inside Vastaus After Create ....");
    
    }
 
    @HandleBeforeDelete
    public void handleVastausBeforeDelete(Vastaus vastaus){
        log.info("Inside Vastaus Before Delete ....");
    }
 
    @HandleAfterDelete
    public void handleVastausAfterDelete(Vastaus vastaus){
        log.info("Inside Vastaus After Delete ....");
    }
}