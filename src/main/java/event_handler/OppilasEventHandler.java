package event_handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import entity.Opettaja;
import entity.Oppilas;
import repository.OpettajaRepository;

@Component
@RepositoryEventHandler(Oppilas.class)
public class OppilasEventHandler {

	private final OpettajaRepository opettajaRepository;

	@Autowired
	public OppilasEventHandler(OpettajaRepository opettajaRepository) {
		this.opettajaRepository = opettajaRepository;
	}

	@HandleBeforeCreate
	@HandleBeforeSave
	public void applyUserInformationUsingSecurityContext(Oppilas oppilas) {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Opettaja opettaja = this.opettajaRepository.findByName(name);

		oppilas.setOpe(opettaja);
	}
}
