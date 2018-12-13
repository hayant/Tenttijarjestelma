package tenttijarjestelma;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "vastausvaihtoehdot", path = "vastausvaihtoehdot")
public interface VastausvaihtoehtoRepository extends PagingAndSortingRepository<Vastausvaihtoehto, Long> {

}
