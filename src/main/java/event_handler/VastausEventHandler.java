package event_handler;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import entity.Oppilas;
import entity.Tentti;
import entity.Vastaus;
import entity.Vastausvaihtoehto;
import repository.OppilasRepository;
import repository.TenttiRepository;
import repository.VastausvaihtoehtoRepository;

@RepositoryEventHandler(Vastaus.class)
public class VastausEventHandler {
	private static final Logger log = LoggerFactory.getLogger(VastausEventHandler.class);

	@Autowired
	private VastausvaihtoehtoRepository vastausvaihtoehdot;

	@Autowired
	private OppilasRepository oppilaat;

	@Autowired
	private TenttiRepository tentit;

	@HandleBeforeCreate
	public void handleVastausBeforeCreate(Vastaus vastaus) {
		log.info("Inside Vastaus Before Create....");
	}

	@HandleAfterCreate
	public void handleVastausAfterCreate(Vastaus vastaus) {
		log.info("Inside Vastaus After Create ....");

		Optional<Oppilas> oppilas = oppilaat.findById(vastaus.getOppilas_id());
		vastaus.setOppilas(oppilas.get());

		Optional<Tentti> tentti = tentit.findById(vastaus.getTentti_id());
		vastaus.setTentti(tentti.get());
		
		Optional<Vastausvaihtoehto> vastausvaihtoehto = vastausvaihtoehdot.findById(vastaus.getVastausvaihtoehto_id());
		vastaus.setVastausvaihtoehto(vastausvaihtoehto.get());

		// Tarkastetaan vastauksen oikeellisuus ennen lisäystä
		if(vastaus.getVastausvaihtoehto().getOikeaVastaus() == vastaus.getOppilaanVastaus()) {
			vastaus.setMenikoOikein(true);
		} else {
			vastaus.setMenikoOikein(false);
		}
	}

	@HandleBeforeDelete
	public void handleVastausBeforeDelete(Vastaus vastaus) {
		log.info("Inside Vastaus Before Delete ....");
	}

	@HandleAfterDelete
	public void handleVastausAfterDelete(Vastaus vastaus) {
		log.info("Inside Vastaus After Delete ....");
	}
}