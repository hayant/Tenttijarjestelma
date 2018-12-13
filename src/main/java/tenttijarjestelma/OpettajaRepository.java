package tenttijarjestelma;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "opettajat", path = "opettajat")
public interface OpettajaRepository extends PagingAndSortingRepository<Opettaja, Long> {
	
	Opettaja save(Opettaja manager);
	Opettaja findByEtunimi(String name);
	Opettaja findBySukunimi(String name);
	Opettaja findByName(String name);
}
