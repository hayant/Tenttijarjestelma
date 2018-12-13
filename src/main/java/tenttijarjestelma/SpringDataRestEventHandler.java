/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tenttijarjestelma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Component
@RepositoryEventHandler(Oppilas.class)
public class SpringDataRestEventHandler {

	private final OpettajaRepository opettajaRepository;

	@Autowired
	public SpringDataRestEventHandler(OpettajaRepository opettajaRepository) {
		this.opettajaRepository = opettajaRepository;
	}

	@HandleBeforeCreate
	@HandleBeforeSave
	public void applyUserInformationUsingSecurityContext(Oppilas oppilas) {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Opettaja opettaja = this.opettajaRepository.findByName(name);
		//if (opettaja == null) {
		//	Opettaja newOpettaja = new Opettaja();
		//	newOpettaja.setName(name);
		//	newOpettaja.setRoles(new String[]{"ROLE_OPETTAJA"});
		//	opettaja = this.opettajaRepository.save(newOpettaja);
		//}
		oppilas.setOpe(opettaja);
	}
}
// end::code[]
