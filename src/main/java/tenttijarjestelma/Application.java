package tenttijarjestelma;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import entity.Opettaja;
import entity.Oppilas;
import repository.OpettajaRepository;
import repository.OppilasRepository;
import repository.TenttiRepository;
import repository.TenttikysymysRepository;
import repository.TulosRepository;
import repository.VastausRepository;
import repository.VastausvaihtoehtoRepository;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EntityScan("entity")
@EnableJpaRepositories("repository")
public class Application {

	@Autowired
	private Testimetodit testimetodit;
	
	@Autowired
	private Tarkastus tarkastus;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(TenttiRepository tentit, TenttikysymysRepository tenttikysymykset,
			OppilasRepository opiskelijat, VastausRepository vastaukset,
			VastausvaihtoehtoRepository vastausvaihtoehdot, OpettajaRepository opettajat,
			TulosRepository tulokset) {
		return (args) -> {

			Opettaja opettaja1 = opettajat.save(new Opettaja("Ossi", "Opettaja", "ossope", "Ope",
					"ROLE_OPETTAJA"));
			
			Opettaja opettaja2 = opettajat.save(new Opettaja("Kimmo", "Opettaja", "kimmope", "Kope",
					"ROLE_OPETTAJA"));
			

			SecurityContextHolder.getContext().setAuthentication(
					new UsernamePasswordAuthenticationToken("ossope", "Ope",
						AuthorityUtils.createAuthorityList("ROLE_OPETTAJA")));
			testimetodit.luoTestidata();
			
			testimetodit.testaaHakua();
			
			tarkastus.arvioiKorjaamattomatVastaukset();
			
			// tarkastus.arvioiTehtavat();
			
			Optional<Oppilas> jonne = opiskelijat.findById(19L);
			
			//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			//String principalName = authentication.getName();
			//System.out.println(principalName);

			Iterable<Oppilas> haetutOpiskelijat = opiskelijat.findAll();
			List<Oppilas> jonnet = new ArrayList<>();
			for(Oppilas opiskelija : haetutOpiskelijat) {
				jonnet.add(opiskelija);
			}

		SecurityContextHolder.clearContext();
		};
	}
}

