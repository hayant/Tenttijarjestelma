package tenttijarjestelma;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tenttisuoritukset", path = "tenttisuoritukset")
public interface TenttisuoritusRepository extends PagingAndSortingRepository<Tenttisuoritus, Long> {
	
	Optional<Tenttisuoritus> findByOppilas_idAndTentti_id(Long oppilas_id, Long tentti_id);
	List<Tenttisuoritus> findByoppilas_idAndTentti_id(Long oppilas_id, Long tentti_id);
	List<Tenttisuoritus> findByOppilas(Oppilas oppilas);
	List<Tenttisuoritus> findByTentti(Tentti tentti);

}