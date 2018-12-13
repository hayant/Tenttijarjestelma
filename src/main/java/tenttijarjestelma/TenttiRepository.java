package tenttijarjestelma;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tentit", path = "tentit")
public interface TenttiRepository extends PagingAndSortingRepository<Tentti, Long> {

	List<Tentti> findByNimi(String nimi);
}