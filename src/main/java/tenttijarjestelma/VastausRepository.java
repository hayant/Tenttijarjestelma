package tenttijarjestelma;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "vastaukset", path = "vastaukset")
public interface VastausRepository extends PagingAndSortingRepository<Vastaus, Long> {

	List<Vastaus> findByOppilas(Oppilas oppilas);
	List<Vastaus> findByOppilasId(Long id);
	List<Vastaus> findByMenikoOikein(Boolean arvo);
	List<Vastaus> findByOppilasIdAndVastausvaihtoehtoId(Long oppilasId, Long vastausvaihtoehtoId);
	// List<Vastaus> findByOppilasIdAndKokoKysymysOikein(Long oppilasId, Boolean arvo);
	List<Vastaus> findByOppilasIdAndVastausvaihtoehtoIdAndTenttiId(Long oppilasId, Long vastausvaihtoehtoId, Long TenttiId);
	// List<Vastaus> findByOppilasIdAndTenttiIdAndKokoKysymysOikein(Long oppilasId, Long tenttiId, Boolean arvo);
	List<Vastaus> findByVastausvaihtoehtoId(Long id);
	List<Vastaus> findByOppilasAndVastausvaihtoehto(Oppilas oppilas, Vastausvaihtoehto vastausvaihtoehto);


}
