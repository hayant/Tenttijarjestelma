package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import entity.Opettaja;
import repository.OpettajaRepository;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

	private final OpettajaRepository repository;

	@Autowired
	public SpringDataJpaUserDetailsService(OpettajaRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Opettaja opettaja = this.repository.findByName(name);
		return new User(opettaja.getName(), opettaja.getPassword(),
				AuthorityUtils.createAuthorityList(opettaja.getRoles()));
	}

}
